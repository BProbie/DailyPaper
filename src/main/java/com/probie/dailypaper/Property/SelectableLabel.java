package com.probie.dailypaper.Property;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.probie.dailypaper.Property.Interface.ISelectableLabel;

public class SelectableLabel extends Label implements ISelectableLabel {

    public SelectableLabel() {
        addSelectEventHandler();
    }

    public SelectableLabel(String text) {
        super(text);
        addSelectEventHandler();
    }

    @Override
    public void addSelectEventHandler() {
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, (mouseEvent) -> {

        });
    }

}