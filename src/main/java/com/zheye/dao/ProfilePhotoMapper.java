package com.zheye.dao;

import com.zheye.bean.ProfilePhoto;

public interface ProfilePhotoMapper {

    // 新增头像
    void insert(ProfilePhoto record);

    // 删除某用户的头像信息
    void deleteByKey(String userid);

    // 修改某用户头像信息
    void updateByKey(ProfilePhoto record);

    // 按userid查询用户信息
    ProfilePhoto selectProfilePhotoByKey(String userid);
}