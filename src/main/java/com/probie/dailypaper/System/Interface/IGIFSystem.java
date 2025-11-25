package com.probie.dailypaper.System.Interface;

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
     * @param fullGIFPath 完整本地 GIF 文件路径
     * @return BufferedImage 数组
     * */
    default BufferedImage[] turnGIFToBufferedImages(String fullGIFPath) {
        ArrayList<BufferedImage> bufferedImageArrayList = new ArrayList<>();
        try {
            /// 初始化流
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(new File(fullGIFPath));
            ImageReader imageReader = ImageIO.getImageReadersBySuffix("gif").next();
            imageReader.setInput(imageInputStream, false);

            ///  获取 GIF 信息
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
                            disposalMethod = 0;
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
                            } catch (NumberFormatException e) {
                                disposalMethod = 0;
                            }
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
            try {
                imageInputStream.close();
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return bufferedImageArrayList.toArray(new BufferedImage[]{});
    }

}