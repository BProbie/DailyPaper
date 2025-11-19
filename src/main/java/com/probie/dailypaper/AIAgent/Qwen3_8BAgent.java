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
import com.probie.dailypaper.AIAgent.Interface.IQwen3_8B;

@Data
public class Qwen3_8BAgent extends AIAgent implements IQwen3_8B {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Qwen3_8BAgent INSTANCE;

    /**
     * 请求连接参数
     * */
    private Supplier<Integer> connectTimeout = DailyPaper.getInstance().getConnectTimeout();
    private Supplier<Integer> readTimeout = DailyPaper.getInstance().getReadTimeout();
    private Supplier<Integer> writeTimeout = DailyPaper.getInstance().getWriteTimeout();

    @Override
    protected void init() {
        setAPIKey(getAPIKey(DailyPaper.getInstance().getQwen3_8BAPIKey()));
        setAPIUrl(DailyPaper.getInstance().getQwen3_8BAPIUrl());
        setAPIModel(DailyPaper.getInstance().Qwen3_8BModel);
    }

    @Override
    public String turnTextToText(String text) {
        /// 设置请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(getWriteTimeout(), TimeUnit.SECONDS)
                .build();

        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", getAPIModel());

        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", text);
        messages.add(userMessage);
        requestBodyJson.put("messages", messages);

        RequestBody requestBody = RequestBody.create(
                requestBodyJson.toString(),
                okhttp3.MediaType.parse("application/json; charset=utf-8")
        );

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
    public synchronized static Qwen3_8BAgent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Qwen3_8BAgent();
            INSTANCE.init();
        }
        return INSTANCE;
    }

    /**
     * 获取动态参数的静态值
     * */
    public int getConnectTimeout() {
        return connectTimeout.get();
    }

    public int getReadTimeout() {
        return readTimeout.get();
    }

    public int getWriteTimeout() {
        return writeTimeout.get();
    }

}