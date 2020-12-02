package com.zheye.dao;

import com.zheye.bean.Album;
import com.zheye.bean.impl.AlbumImpl;

import java.util.List;

public interface AlbumMapper {

    // 创建相册
    void insert(Album record);

    // 删除相册
    void deleteByKey(String fid);

    // 编辑相册
    void updateNameByKey(Album album);

    // 按fid（相册id）查询相册信息
    Album selectAlbumByKey(String fid);

    // 查询某用户的相册分类信息
    List<AlbumImpl> selectAlbumImplByUserid(String userid);

    // 按userid和name（相册名）查询相册信息
    List<Album> selectAlbumByUN(Album album);
}