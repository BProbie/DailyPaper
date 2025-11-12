package com.probie.dailypaper.System.Interface;

import java.awt.*;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;

public interface IImageSystem {

    /**
     * 将网址指向的网页图片转换成BufferedImage实例化对象
     * @param url url
     * @return bufferedImage
     * */
    default BufferedImage turnUrlToBufferedImage(String url) {
        try {
            return ImageIO.read(URI.create(url).toURL());
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * 更改BufferedImage实例化对象的大小
     * @param bufferedImage BufferedImage实例化对象
     * @param width 宽度
     * @param height 高度
     * @return 更改后的BufferedImage实例化对象
     * */
    default BufferedImage setBufferedImageSize(BufferedImage bufferedImage, int width, int height) {
        BufferedImage fitBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = fitBufferedImage.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
        return fitBufferedImage;
    }

    /**
     * 将BufferedImage实例化对象转换成本地文件
     * @param bufferedImage BufferedImage实例化对象
     * @param localFilePath 本地文件路径
     * @param localFileName 本地文件名称
     * @return 是否转换成功
     * */
    default boolean turnBufferedImageToLocalFile(BufferedImage bufferedImage, String localFilePath, String localFileName) {
        return turnBufferedImageToLocalFile(bufferedImage, localFilePath+"\\"+localFileName);
    }

    /**
     * 将BufferedImage实例化对象转换成本地文件
     * @param bufferedImage BufferedImage实例化对象
     * @param fullFilePath 完整本地路径
     * @return 是否转换成功
     * */
    default boolean turnBufferedImageToLocalFile(BufferedImage bufferedImage, String fullFilePath) {
        try {
            File localFile = new File(fullFilePath);
            ImageIO.write(bufferedImage, fullFilePath.contains(".") ? fullFilePath.substring(fullFilePath.lastIndexOf(".")+1).toLowerCase() : "png", localFile);
            return localFile.exists();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * 本地文件转换成BufferedImage实例化对象
     * @param fullFilePath 完整本地文件路径
     * @return BufferedImage实例化对象
     * */
    default BufferedImage turnLocalFileToBufferedImage(String fullFilePath) {
        try {
            return ImageIO.read(new File(fullFilePath));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * JavaAWT的BufferedImage 转 JavaFX的Image
     * @param bufferedImage bufferedImage
     * @return JavaFX的Image实例化对象
     * */
    default Image turnBufferedImageToFXImage(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            return SwingFXUtils.toFXImage(bufferedImage, null);
        }
        return null;
    }

}