package com.zheye.service;

import com.zheye.bean.Admin;
import com.zheye.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	AdminMapper adminMapper;

	/**
	 * 管理员登录查询
	 * @param admin
	 * @return
	 */
	public Admin getAdmin(Admin admin) {

		return adminMapper.selectByAdmin(admin);
	}
}
