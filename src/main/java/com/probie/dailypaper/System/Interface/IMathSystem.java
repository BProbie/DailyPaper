package com.probie.dailypaper.System.Interface;

import java.awt.*;

public interface IMathSystem {

    /**
     * 规范化Dimension的大小
     * @param dimension Dimension实例化对象
     * @return 规范的Dimension实例化对象
     * */
    default Dimension getFitDimension(Dimension dimension) {
        double height = dimension.getHeight();
        double width = dimension.getWidth();
        dimension.setSize(1024, ((int) Math.floor(1024*(height/width))));
        return dimension;
    }

}