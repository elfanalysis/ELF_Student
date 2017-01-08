package com.elf.elfstudent.model;

/**
 * Created by ELF on 05-01-2017.
 */
public class Feed {
    private boolean isImage;
    private String ImageUrl;
    private String text;

    public Feed(boolean isImage, String imageUrl, String text) {
        this.isImage = isImage;
        ImageUrl = imageUrl;
        this.text = text;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
