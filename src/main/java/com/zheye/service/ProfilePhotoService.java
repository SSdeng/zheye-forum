package com.zheye.service;

import com.zheye.bean.ProfilePhoto;
import com.zheye.dao.ProfilePhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilePhotoService {

	@Autowired
	ProfilePhotoMapper profilePhotoMapper;

	/**
	 * 上传用户头像（插入）（profilePhoto）
	 *
	 * @param profilePhoto
	 */
	public void setProfilePhoto(ProfilePhoto profilePhoto) {

		profilePhotoMapper.insert(profilePhoto);
	}

	/**
	 * 删除用户对应的头像信息
	 * @param userid
	 */
	public void deleteProfilePhoto(String userid) {

		profilePhotoMapper.deleteByKey(userid);
	}

	/**
	 * 按userid修改用户头像信息（profilePhoto）
	 * @param profilePhoto
	 */
	public void updateProfilePhoto(ProfilePhoto profilePhoto) {

		profilePhotoMapper.updateByKey(profilePhoto);
	}

	/**
	 * 按userid查询用户头像信息（profilePhoto）
	 * @param userid
	 * @return
	 */
	public ProfilePhoto getProfilePhoto(String userid) {

		return profilePhotoMapper.selectProfilePhotoByKey(userid);
	}
}
