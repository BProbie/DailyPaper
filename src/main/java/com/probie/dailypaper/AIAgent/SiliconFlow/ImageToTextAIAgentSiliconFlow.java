package com.probie.dailypaper.AIAgent.SiliconFlow;

import lombok.Data;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.probie.dailypaper.System.PictureSystem;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.AIAgent.Interface.SiliconFlow.IImageToTextAIAgentSiliconFlow;

@Data
public class ImageToTextAIAgentSiliconFlow extends AIAgentSiliconFlow implements IImageToTextAIAgentSiliconFlow {

    /**
     * 构造函数
     * */
    public ImageToTextAIAgentSiliconFlow() {
        init();
    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ImageToTextAIAgentSiliconFlow INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static ImageToTextAIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageToTextAIAgentSiliconFlow();
        }
        return INSTANCE;
    }

    @Override
    protected void init() {
        setAPIKey(getAPIKey(DailyPaper.getInstance().getAPIKeySiliconFlow()));
        setAPIUrl(DailyPaper.getInstance().getAPIUrlImageToTextSiliconFlow());
        setAPIModel(DailyPaper.getInstance().getAPIModelImageToTextSiliconFlow());
    }

    @Override
    public String[] turnImageToText(String fullFilePath, String prompt) {
        /// 设置请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Integer.parseInt(String.valueOf(getConnectTimeout().get())), TimeUnit.SECONDS)
                .readTimeout(Integer.parseInt(String.valueOf(getReadTimeout().get())), TimeUnit.SECONDS)
                .writeTimeout(Integer.parseInt(String.valueOf(getWriteTimeout().get())), TimeUnit.SECONDS)
                .build();

        /// 设置请求体 Json
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", String.valueOf(getAPIModel().get()));
        requestBodyJson.put("stream", false);
        requestBodyJson.put("max_tokens", DailyPaper.getInstance().getSpawnMaxTokens().get());

        /// 设置请求体 Json 参数
        JSONArray content = new JSONArray();

        /// 文本
        JSONObject text = new JSONObject();
        text.put("type", "text");
        text.put("text", prompt);
        content.add(text);

        /// 图片
        JSONObject image = new JSONObject();
        JSONObject imageUrl = new JSONObject();
        String imageData = PictureSystem.getInstance().turnLocalFileToBase64Data(fullFilePath);
        image.put("type", "image_url");
        imageUrl.put("url", imageData);
        image.put("image_url", imageUrl);
        content.add(image);

        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", content);
        messages.add(userMessage);
        requestBodyJson.put("messages", messages);

        /// 设置请求体
        RequestBody requestBody = RequestBody.create(
                requestBodyJson.toJSONString(),
                okhttp3.MediaType.parse("application/json; charset=utf-8")
        );

        /// 构造请求
        Request request = new Request.Builder()
                .url(String.valueOf(getAPIUrl().get()))
                .addHeader("Authorization", "Bearer " + String.valueOf(getAPIKey().get()))
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        /// 发送请求
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();
                if (Boolean.parseBoolean(String.valueOf(DailyPaper.getInstance().getDebug().get()))) {
                    System.out.println("ImageToText" + "\n" + responseBody);
                }
                if (response.isSuccessful()) {
                    /// 解析响应并返回结果
                    JSONObject responseBodyJson = JSONObject.parseObject(responseBody);
                    JSONObject message = responseBodyJson.getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message");
                    String[] result = new String[2];
                    result[0] = message.getString("content");
                    result[1] = message.getString("reasoning_content");
                    return result;
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return null;
    }

}