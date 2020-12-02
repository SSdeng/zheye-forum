package com.zheye.service;

import com.zheye.bean.Photo;
import com.zheye.dao.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

	@Autowired
	PhotoMapper photoMapper;
	
	/**
	 * 上传照片
	 * @param photo
	 */
	public void setPhoto(Photo photo) {

		photoMapper.insert(photo);
	}

	/**
	 * 删除某一张照片
	 * @param xid
	 */
	public void deletePhoto(String xid) {

		photoMapper.deleteByKey(xid);
	}

	/**
	 * 删除相册对应的照片
	 * @param fid
	 */
	public void deletePhotoFid(String fid) {

		photoMapper.deleteByFid(fid);
	}

	/**
	 * 获取相册分类下的对应的照片
	 * @param photo
	 * @return
	 */
	public List<Photo> getPhoto(Photo photo) {

		return photoMapper.selectPhotoByFU(photo);
	}

	/**
	 * 按xid查询照片信息
	 * @param xid
	 */
	public Photo getPhotoXid(String xid) {

		return photoMapper.selectPhotoByKey(xid);
	}

}
