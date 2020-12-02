package com.zheye.service;

import com.zheye.bean.Album;
import com.zheye.bean.impl.AlbumImpl;
import com.zheye.dao.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumMapper albumMapper;

    /**
     * 创建相册
     *
     * @param album
     */
    public void setAlbum(Album album) {

        albumMapper.insert(album);
    }

    /**
     * 删除相册
     *
     * @param fid
     */
    public void deleteAlbum(String fid) {

        albumMapper.deleteByKey(fid);
    }

    /**
     * 编辑相册
     *
     * @param album
     */
    public void updateName(Album album) {
        albumMapper.updateNameByKey(album);
    }

    /**
     * 获取相册分类信息(按userid)
     *
     * @param userid
     */
    public List<AlbumImpl> getAlbum(String userid) {

        return albumMapper.selectAlbumImplByUserid(userid);
    }

    /**
     * 按fid（相册id）查询相册信息
     *
     * @param fid
     */
    public Album selectByPrimaryKey(String fid) {
        return albumMapper.selectAlbumByKey(fid);
    }

    /**
     * 按userid和name（相册名）查询相册信息
     *
     * @param album
     * @return
     */
    public List<Album> selectByName(Album album) {
		return albumMapper.selectAlbumByUN(album);
	}
}
