package com.probie.dailypaper.System.Interface;

import java.io.File;
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface IPictureSystem {

    /**
     * 将本地文件转成 Base64 数据信息
     * @param filePath 本地文件路径
     * @param fileName 本地文件名称
     * @return Base64 数据信息
     * */
    default String turnLocalFileToBase64Data(String filePath, String fileName) {
        return turnLocalFileToBase64Data(filePath + File.separator + fileName);
    }

    /**
     * 将本地文件转成 Base64 数据信息
     * @param fullFilePath 完整本地文件路径
     * @return Base64 数据信息
     * */
    default String turnLocalFileToBase64Data(String fullFilePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(fullFilePath));
            String dataPrefix = "data:image/png;base64,";
            if (fullFilePath.toLowerCase().endsWith(".jpg") ||  fullFilePath.toLowerCase().endsWith(".jpeg")) {
                dataPrefix = "data:image/jpeg;base64,";
            }
            return dataPrefix + Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}