package com.probie.dailypaper.System.Interface;

import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.DailyPaper.DailyPaperStyle;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.metadata.IIOMetadataNode;

public interface IGIFSystem {

    /**
     * 逐帧切割 GIF 动图
     * @param fullGIFFilePath GIF 完整文件路径
     * @return BufferedImage 数组
     * */
    default BufferedImage[] turnGIFToBufferedImages(String fullGIFFilePath) {
        return turnGIFToBufferedImages(new File(fullGIFFilePath));
    }

    /**
     * 逐帧切割 GIF 动图
     * @param gifFile GIF 文件对象
     * @return BufferedImage 数组
     * */
    default BufferedImage[] turnGIFToBufferedImages(File gifFile) {
        ArrayList<BufferedImage> bufferedImageArrayList = new ArrayList<>();
        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(gifFile)) {

            /// 初始化流
            ImageReader imageReader = ImageIO.getImageReadersBySuffix("gif").next();
            imageReader.setInput(imageInputStream, false);

            /// 获取 GIF 信息
            int count = imageReader.getNumImages(true);
            int width = imageReader.getWidth(0);
            int height = imageReader.getHeight(0);

            /// 初始化绘图工具
            BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = canvas.createGraphics();
            graphics2D.setComposite(AlphaComposite.SrcOver);

            /// 逐帧遍历
            for (int i = 0; i < count; i++) {
                BufferedImage frame;
                IIOMetadata metadata;
                frame = imageReader.read(i);
                metadata = imageReader.getImageMetadata(i);

                /// 解析帧位置
                IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree("javax_imageio_gif_image_1.0");
                IIOMetadataNode imgDesc = (IIOMetadataNode) root.getElementsByTagName("ImageDescriptor").item(0);
                int frameX = Integer.parseInt(imgDesc.getAttribute("imageLeftPosition"));
                int frameY = Integer.parseInt(imgDesc.getAttribute("imageTopPosition"));

                /// 解析处置方法
                int disposalMethod = 0;
                IIOMetadataNode graphicCtrl = (IIOMetadataNode) root.getElementsByTagName("GraphicControlExtension").item(0);
                if (graphicCtrl != null) {
                    String disposalAttr = graphicCtrl.getAttribute("disposalMethod");

                    /// 映射字符串枚举到数字
                    switch (disposalAttr) {
                        case "doNotDispose":
                            break;
                        case "restoreToBackground":
                            disposalMethod = 2;
                            break;
                        case "restoreToPrevious":
                            disposalMethod = 3;
                            break;
                        default:
                            try {
                                disposalMethod = Integer.parseInt(disposalAttr);
                            } catch (NumberFormatException ignored) {}
                    }
                }

                /// 处理画布
                if (disposalMethod == 2) {
                    graphics2D.setComposite(AlphaComposite.Clear);
                    graphics2D.fillRect(frameX, frameY, frame.getWidth(), frame.getHeight());
                    graphics2D.setComposite(AlphaComposite.SrcOver);
                }

                /// 绘制当前帧到画布
                graphics2D.drawImage(frame, frameX, frameY, null);

                /// 保存完整帧
                BufferedImage completeFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                completeFrame.getGraphics().drawImage(canvas, 0, 0, null);
                bufferedImageArrayList.add(completeFrame);
            }

            /// 释放资源
            graphics2D.dispose();
            imageReader.dispose();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return bufferedImageArrayList.toArray(new BufferedImage[]{});
    }

    /**
     * 逐帧切割 GIF 动图
     * @param fullGIFFilePath GIF 文件对象
     * @return JavaFX 的 Image 数组
     * */
    default Image[] turnGIFToFXImages(String fullGIFFilePath) {
        BufferedImage[] bufferedImages = turnGIFToBufferedImages(fullGIFFilePath);
        Image[] images = new Image[bufferedImages.length];
        for (int i = 0; i < bufferedImages.length; i++) {
            images[i] = DailyPaper.getInstance().getImageSystem().turnBufferedImageToFXImage(bufferedImages[i]);
        }
        return images;
    }

    /**
     * 逐帧切割 GIF 动图
     * @param gifFile GIF 文件对象
     * @return JavaFX 的 Image 数组
     * */
    default Image[] turnGIFToFXImages(File gifFile) {
        BufferedImage[] bufferedImages = turnGIFToBufferedImages(gifFile);
        Image[] images = new Image[bufferedImages.length];
        for (int i = 0; i < bufferedImages.length; i++) {
            images[i] = DailyPaper.getInstance().getImageSystem().turnBufferedImageToFXImage(bufferedImages[i]);
        }
        return images;
    }

    /**
     * 获取 GIF 动图每张图片播放的时间
     * @param fullGIFFilePath GIF 完整文件路径
     * @return 播放时间数组(单位为 ms 即千分之一秒)
     * */
    default Integer[] getGIFPlaySpeed(String fullGIFFilePath) {
        return getGIFPlaySpeed(new File(fullGIFFilePath));
    }

    /**
     * 获取 GIF 动图每张图片播放的时间
     * @param gifFile GIF 文件对象
     * @return 播放时间数组(单位为 ms 即千分之一秒)
     * */
    default Integer[] getGIFPlaySpeed(File gifFile) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(gifFile)) {

            /// 初始化流
            ImageReader imageReader = ImageIO.getImageReadersBySuffix("gif").next();
            imageReader.setInput(imageInputStream, false);

            /// 获取 GIF 信息
            int count = imageReader.getNumImages(true);

            /// 逐帧遍历
            for (int frameIndex = 0; frameIndex < count; frameIndex++) {

                /// 当前帧的元数据
                IIOMetadata metadata = imageReader.getImageMetadata(frameIndex);

                ///验证元数据格式是否支持
                if (metadata.isStandardMetadataFormatSupported()) {

                    /// 解析元数据树
                    IIOMetadataNode rootNode = (IIOMetadataNode) metadata.getAsTree("javax_imageio_gif_image_1.0");
                    IIOMetadataNode controlNode = (IIOMetadataNode) rootNode.getElementsByTagName("GraphicControlExtension").item(0);

                    /// 提取延迟时间: GIF原生单位是1/100秒,换算为ms需×10
                    int delay100Sec = Integer.parseInt(controlNode.getAttribute("delayTime"));
                    int delayMs = delay100Sec * 10;

                    /// 处理默认延迟
                    integerArrayList.add(delayMs == 0 ? 100 : delayMs);
                } else {

                    /// 格式不支持时,添加默认延迟100ms
                    integerArrayList.add(100);
                }
            }

            /// 释放资源
            imageReader.dispose();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return integerArrayList.toArray(new Integer[]{});
    }

}