package com.zheye.bean.impl;

import com.zheye.bean.PhotoPro;

public class PhotoProImpl extends PhotoPro {
    // 图片名
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "PhotoProImpl{" +
                "photo='" + photo + '\'' +
                '}';
    }
}
