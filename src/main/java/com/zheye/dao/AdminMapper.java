package com.zheye.dao;

import com.zheye.bean.Admin;

public interface AdminMapper {

    // 管理员登录查询
    Admin selectByAdmin(Admin admin);
}