package com.probie.dailypaper.System.Interface;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Files;

public interface IFileSystem {

    /**
     * 读取本地文件内容
     * @param path 本地文件路径
     * @return 本地文件内容
     * */
    default String readLocalFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * 读取远程文件内容
     * @param path 远程文件路径
     * @return 远程文件内容
     * */
    default String readRemoteFile(String path) throws URISyntaxException, IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URI(path).toURL().openConnection().getInputStream()));
            String value = "";
            String temp;
            while ((temp = bufferedReader.readLine())!=null) value += temp + "\n";
            return value.substring(0, value.lastIndexOf("\n"));
        } catch (URISyntaxException | IOException sslHandshakeException) {
            throw new RuntimeException(sslHandshakeException);
        }
    }

    /**
     * 下载网络文件到本地
     * @param urlPath 网络文件网址
     * @param localFilePath 本地文件路径
     * @param localFileName 本地文件名称
     * @return 是否下载成功
     * */
    default boolean download(String urlPath, String localFilePath, String localFileName) {
        try {
            return download(urlPath, localFilePath+File.separator+localFileName);
        } catch (URISyntaxException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * 下载网络文件到本地
     * @param urlPath 网络文件网址
     * @param fullFilePath 完整本地路径
     * @return 是否下载成功
     * */
    default boolean download(String urlPath, String fullFilePath) throws URISyntaxException, IOException {
        URL url = new URI(urlPath).toURL();
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(1000*30);
        connection.setReadTimeout(1000*60);
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(fullFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return new File(fullFilePath).exists();
    }

}