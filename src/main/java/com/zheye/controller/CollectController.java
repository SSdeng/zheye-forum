package com.zheye.controller;

import com.zheye.bean.Collect;
import com.zheye.code.ReturnT;
import com.zheye.service.ArticleService;
import com.zheye.service.CollectService;
import com.zheye.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 收藏控制类
 *
 * @author dengzhijian
 */
@RequestMapping("/zheye-forum/collect")
@Controller
public class CollectController {
	@Autowired
	CollectService collectService;
	@Autowired
	ArticleService articleService;

	/**
	 * 添加收藏
	 * @param collect
	 * @return
	 */
	@PostMapping("/setCollect")
	@ResponseBody
	public ReturnT<?> setCollect(Collect collect) {
		try {
			collect.setSid(UUIDUtil.getRandomUUID());
			collectService.setCollect(collect);
			return ReturnT.success("收藏成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("收藏失败");
		}
	}

	/**
	 * 删除收藏（按sid）
	 * @param sid
	 * @return
	 */
	@DeleteMapping("/deleteCollect/{sid}")
	@ResponseBody
	public ReturnT<?> deleteCollect(@PathVariable String sid) {
		try {
			collectService.deleteCollect(sid);
			return ReturnT.success("取消收藏成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("取消收藏失败");
		}
	}

	/**
	 * 删除收藏（按userid和fid）
	 * @param fid
	 * @return
	 */
	@DeleteMapping("/deleteCollectUseridAndFid/{fid}")
	@ResponseBody
	public ReturnT<?> deleteCollectUseridAndFid(@PathVariable String fid, HttpSession session) {
		try {
			Collect collect = new Collect();
			collect.setFid(fid);
			collect.setUserid((String) session.getAttribute("userid"));
			//取消收藏
			collectService.deleteCollectUseridAndFid(collect);
			return ReturnT.success("取消收藏成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("取消收藏失败");
		}
	}

	/**
	 * 按userid和fid获取收藏信息
	 * @param fid
	 * @param session
	 * @return
	 */
	@GetMapping("/getCollectFid/{fid}")
	@ResponseBody
	public ReturnT<?> getCollectFid(@PathVariable String fid, HttpSession session){
		Map<String, Object> map = new HashMap<>();
		try {
			Collect collect = new Collect();
			collect.setFid(fid);
			collect.setUserid((String) session.getAttribute("userid"));
			map.put("collect",collectService.getCollectFid(collect));
			return new ReturnT<>(HttpStatus.OK, "获取收藏数据成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("获取收藏数据失败");
		}
	}
}
