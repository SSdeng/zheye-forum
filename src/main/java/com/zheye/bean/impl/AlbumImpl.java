package com.zheye.bean.impl;

import com.zheye.bean.Album;

public class AlbumImpl extends Album {
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
        return "AlbumImpl{" +
                "photo='" + photo + '\'' +
                '}';
    }
}
