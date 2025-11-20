package com.probie.dailypaper.AIAgent;

import lombok.Data;
import java.io.File;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import java.io.IOException;
import okhttp3.OkHttpClient;
import java.util.function.Supplier;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.probie.encryption.Encryption;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.AIAgent.Interface.ITextToTextAIAgentSiliconFlow;

@Data
public class TextToTextAIAgentSiliconFlow extends AIAgent implements ITextToTextAIAgentSiliconFlow {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TextToTextAIAgentSiliconFlow INSTANCE;

    @Override
    protected void init() {
        setAPIKey(getAPIKey(DailyPaper.getInstance().getAPIKeySiliconFlow()));
        setAPIUrl(DailyPaper.getInstance().getAPIUrlTextToTextSiliconFlow());
        setAPIModel(DailyPaper.getInstance().getAPIModelTextToTextSiliconFlow());
    }

    @Override
    public String turnTextToText(String prompt) {
        /// 设置请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(getConnectTimeout().get(), TimeUnit.SECONDS)
                .readTimeout(getReadTimeout().get(), TimeUnit.SECONDS)
                .writeTimeout(getWriteTimeout().get(), TimeUnit.SECONDS)
                .build();

        /// 设置请求体
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", getAPIModel());

        /// 设置请求体参数
        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);
        requestBodyJson.put("messages", messages);

        /// 设置请求头
        RequestBody requestBody = RequestBody.create(
                requestBodyJson.toString(),
                okhttp3.MediaType.parse("application/json; charset=utf-8")
        );

        /// 构造请求
        Request request = new Request.Builder()
                .url(getAPIUrl())
                .addHeader("Authorization", "Bearer " + getAPIKey())
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        /// 发送请求
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();
                if (response.isSuccessful()) {
                    /// 解析响应并返回结果
                    JSONObject responseBodyJson = JSONObject.parseObject(responseBody);
                    return responseBodyJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return null;
    }

    @Override
    protected Supplier<String> getAPIKey(Supplier<String> APIKey) {
        if (!Encryption.getInstance().isDebug()) {
            if (Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().connect(ClassLoader.getSystemResourceAsStream(new File(Encryption.getFilePath()).getName()))) {
                Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().setIsAutoCommit(false);
                return () -> Encryption.getInstance().getDecrypterFactory().getMapDecrypter().decryptByMap(APIKey.get());
            }
        }
        System.exit(0);
        return null;
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static TextToTextAIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextToTextAIAgentSiliconFlow();
            INSTANCE.init();
        }
        return INSTANCE;
    }

}