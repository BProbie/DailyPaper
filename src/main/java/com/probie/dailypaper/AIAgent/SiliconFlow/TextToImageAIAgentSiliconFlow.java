package com.probie.dailypaper.AIAgent.SiliconFlow;

import lombok.Data;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import java.io.IOException;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import javafx.beans.property.SimpleObjectProperty;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.AIAgent.Interface.SiliconFlow.ITextToImageAIAgentSiliconFlow;

@Data
public class TextToImageAIAgentSiliconFlow extends AIAgentSiliconFlow implements ITextToImageAIAgentSiliconFlow {

    /**
     * 构造函数
     * */
    public TextToImageAIAgentSiliconFlow() {
        init();
    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TextToImageAIAgentSiliconFlow INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static TextToImageAIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextToImageAIAgentSiliconFlow();
        }
        return INSTANCE;
    }

    /**
     * 图片生成参数
     * */
    private SimpleObjectProperty<Object> imageSize = DailyPaper.getInstance().getSpawnImageSize();
    private SimpleObjectProperty<Object> imageCount = DailyPaper.getInstance().getSpawnImageCount();

    @Override
    protected void init() {
        setAPIKey(getAPIKey(DailyPaper.getInstance().getAPIKeySiliconFlow()));
        setAPIUrl(DailyPaper.getInstance().getAPIUrlTextToImageSiliconFlow());
        setAPIModel(DailyPaper.getInstance().getAPIModelTextToImageSiliconFlow());
    }

    @Override
    public String[] turnTextToImage(String prompt) {
        /// 设置请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Integer.parseInt(String.valueOf(getConnectTimeout().get())), TimeUnit.SECONDS)
                .readTimeout(Integer.parseInt(String.valueOf(getReadTimeout().get())), TimeUnit.SECONDS)
                .writeTimeout(Integer.parseInt(String.valueOf(getWriteTimeout().get())), TimeUnit.SECONDS)
                .build();

        /// 设置请求体 Json
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", getAPIModel().get().toString());
        requestBodyJson.put("prompt", prompt);
        requestBodyJson.put("image_size", getImageSize().get());
        requestBodyJson.put("batch_size", getImageCount().get());

        /// 设置请求体
        RequestBody requestBody = RequestBody.create(
                requestBodyJson.toString(),
                okhttp3.MediaType.parse("application/json; charset=utf-8")
        );

        /// 构造请求
        Request request = new Request.Builder()
                .url(getAPIUrl().get().toString())
                .addHeader("Authorization", "Bearer " + getAPIKey().get().toString())
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        /// 发送请求
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();
                if (Boolean.parseBoolean(String.valueOf(DailyPaper.getInstance().getDebug().get()))) {
                    System.out.println("TextToImage" + "\n" + responseBody);
                }
                if (response.isSuccessful()) {
                    /// 解析响应并返回结果
                    JSONObject responseBodyJson = JSONObject.parseObject(responseBody);
                    JSONArray images = responseBodyJson.getJSONArray("images");
                    String[] result = new String[images.size()];
                    for (int i = 0; i < result.length; i++) {
                        result[i] = images.getJSONObject(i).getString("url");
                    }
                    return result;
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return null;
    }

}
