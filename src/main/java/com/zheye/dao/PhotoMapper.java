package com.zheye.dao;

import com.zheye.bean.Photo;

import java.util.List;

public interface PhotoMapper {

    // 上传照片
    void insert(Photo record);

    // 删除某一张照片
    void deleteByKey(String xid);

    // 删除相册对应的照片
    void deleteByFid(String fid);

    // 按xid查询照片信息
    Photo selectPhotoByKey(String xid);

    // 获取相册分类下的对应的照片
	List<Photo> selectPhotoByFU(Photo photo);
}