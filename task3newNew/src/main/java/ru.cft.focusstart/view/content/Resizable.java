package ru.cft.focusstart.view.content;

import ru.cft.focusstart.view.iconService.ImageIconCustomer;

import javax.swing.*;
import java.awt.*;

public interface Resizable {

    Integer getImageIconSize();

    void setImageIconSize(Integer ImageIconSize);

    ImageIcon getImageIcon();

    void setImageIcon(ImageIcon imageIcon);

    void setIcon(Icon defaultIcon);

    default void setSizeAndResize(Integer size) {
        setImageIconSize(size);
        resizeIcon();
    }

    default void setImageIconAndResize(ImageIcon imageIcon) {
        setImageIcon(imageIcon);
        resizeIcon();
    }

    default void resizeIcon() {
        setIcon((ImageIconCustomer.resize(getImageIcon(), new Dimension(getImageIconSize(), getImageIconSize()))));
    }
}
