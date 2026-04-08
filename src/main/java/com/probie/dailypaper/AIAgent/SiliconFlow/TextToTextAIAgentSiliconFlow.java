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
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.AIAgent.Interface.SiliconFlow.ITextToTextAIAgentSiliconFlow;

@Data
public class TextToTextAIAgentSiliconFlow extends AIAgentSiliconFlow implements ITextToTextAIAgentSiliconFlow {

    /**
     * 构造函数
     * */
    public TextToTextAIAgentSiliconFlow() {
        init();
    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TextToTextAIAgentSiliconFlow INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static TextToTextAIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextToTextAIAgentSiliconFlow();
        }
        return INSTANCE;
    }

    @Override
    protected void init() {
        setAPIKey(getAPIKey(DailyPaper.getInstance().getAPIKeySiliconFlow()));
        setAPIUrl(DailyPaper.getInstance().getAPIUrlTextToTextSiliconFlow());
        setAPIModel(DailyPaper.getInstance().getAPIModelTextToTextSiliconFlow());
    }

    @Override
    public String[] turnTextToText(String prompt) {
        /// 设置请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Integer.parseInt(String.valueOf(getConnectTimeout().get())), TimeUnit.SECONDS)
                .readTimeout(Integer.parseInt(String.valueOf(getReadTimeout().get())), TimeUnit.SECONDS)
                .writeTimeout(Integer.parseInt(String.valueOf(getWriteTimeout().get())), TimeUnit.SECONDS)
                .build();

        /// 设置请求体 Json
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", String.valueOf(getAPIModel().get()));

        /// 设置请求体 Json 参数
        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);
        requestBodyJson.put("messages", messages);

        /// 设置请求体
        RequestBody requestBody = RequestBody.create(
                String.valueOf(requestBodyJson),
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
                    System.out.println("TextToText" + "\n" + responseBody);
                }
                if (response.isSuccessful()) {
                    /// 解析响应并返回结果
                    JSONObject responseBodyJson = JSONObject.parseObject(responseBody);
                    String[] strings = new String[2];
                    JSONObject messageJson = responseBodyJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message");
                    strings[0] = messageJson.getString("content");
                    strings[1] = messageJson.getString("reasoning_content");
                    return strings;
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return null;
    }

}