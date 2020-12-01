package com.zheye.service;

import com.zheye.bean.Via;
import com.zheye.dao.ViaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaService {

	@Autowired
	ViaMapper viaMapper;

	/**
	 * 上传用户头像（插入）（via）
	 *
	 * @param via
	 */
	public void setVia(Via via) {

		viaMapper.insert(via);
	}

	/**
	 * 删除用户对应的头像信息
	 * @param userid
	 */
	public void deleteVia(String userid) {

		viaMapper.deleteByKey(userid);
	}

	/**
	 * 按userid修改用户头像信息（via）
	 * @param via
	 */
	public void updateVia(Via via) {

		viaMapper.updateByKey(via);
	}

	/**
	 * 按userid查询用户头像信息（via）
	 * @param userid
	 * @return
	 */
	public Via getVia(String userid) {

		return viaMapper.selectViaByKey(userid);
	}
}
