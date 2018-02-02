package com.demo.core.properties;

public class ImageValidateCodeProperties extends SmsValidateCodeProperties {

    private int width = 67;

    private int height = 23;

    public ImageValidateCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
