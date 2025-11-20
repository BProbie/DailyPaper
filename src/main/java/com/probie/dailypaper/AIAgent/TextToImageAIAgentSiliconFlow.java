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
import com.probie.dailypaper.AIAgent.Interface.ITextToImageAIAgentSiliconFlow;

@Data
public class TextToImageAIAgentSiliconFlow extends AIAgent implements ITextToImageAIAgentSiliconFlow {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TextToImageAIAgentSiliconFlow INSTANCE;

    /**
     * 图片生成参数
     * */
    private Supplier<String> imageSize = DailyPaper.getInstance().getImageSize();
    private Supplier<Integer> imageCount = DailyPaper.getInstance().getImageCount();

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
                .connectTimeout(getConnectTimeout().get(), TimeUnit.SECONDS)
                .readTimeout(getReadTimeout().get(), TimeUnit.SECONDS)
                .writeTimeout(getWriteTimeout().get(), TimeUnit.SECONDS)
                .build();

        /// 设置请求体
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("model", getAPIModel());
        requestBodyJson.put("prompt", prompt);
        requestBodyJson.put("image_size", getImageSize().get());
        requestBodyJson.put("batch_size", getImageCount().get());

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
                    JSONArray images = responseBodyJson.getJSONArray("images");
                    String[] strings = new String[images.size()];
                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = images.getJSONObject(i).getString("url");
                    }
                    return strings;
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return null;
    }

    @Override
    public Supplier<String> getAPIKey(Supplier<String> APIKey) {
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
    public synchronized static TextToImageAIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextToImageAIAgentSiliconFlow();
            INSTANCE.init();
        }
        return INSTANCE;
    }

}
