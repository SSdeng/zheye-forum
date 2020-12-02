package com.zheye.controller;

import com.zheye.bean.Enjoy;
import com.zheye.code.ReturnT;
import com.zheye.service.ArticleService;
import com.zheye.service.EnjoyService;
import com.zheye.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 点赞控制类
 *
 * @author dengzhijian
 */
@RequestMapping("/zheye-forum/enjoy")
@Controller
public class EnjoyController {
	@Autowired
	EnjoyService enjoyService;
	@Autowired
	ArticleService articleService;

	/**
	 * 添加点赞
	 * @param enjoy
	 * @return
	 */
	@PostMapping("/setEnjoy")
	@ResponseBody
	public ReturnT<?> setEnjoy(Enjoy enjoy) {
		try {
			enjoy.setEid(UUIDUtil.getRandomUUID());
			enjoyService.setEnjoy(enjoy);
			return ReturnT.success("点赞成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("点赞失败");
		}
	}

	/**
	 * 删除点赞（按eid）
	 * @param eid
	 * @return
	 */
	@DeleteMapping("/deleteEnjoy/{eid}")
	@ResponseBody
	public ReturnT<?> deleteEnjoy(@PathVariable String eid) {
		try {
			enjoyService.deleteEnjoy(eid);
			return ReturnT.success("取消点赞成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("取消点赞失败");
		}
	}

	/**
	 * 删除点赞（按userid和fid）
	 * @param fid
	 * @return
	 */
	@DeleteMapping("/deleteEnjoyUseridAndFid/{fid}")
	@ResponseBody
	public ReturnT<?> deleteEnjoyUseridAndFid(@PathVariable String fid, HttpSession session) {
		try {
			Enjoy enjoy = new Enjoy();
			enjoy.setFid(fid);
			enjoy.setUserid((String) session.getAttribute("userid"));
			//取消点赞
			enjoyService.deleteEnjoyUseridAndFid(enjoy);
			return ReturnT.success("取消点赞成功");
		} catch (Exception e){
			e.printStackTrace();
			return ReturnT.fail("取消点赞失败");
		}
	}

	/**
	 * 按userid和fid获取点赞信息
	 * @param fid
	 * @param session
	 * @return
	 */
	@GetMapping("/getEnjoyFid/{fid}")
	@ResponseBody
	public ReturnT<?> getEnjoyFid(@PathVariable String fid, HttpSession session){
		Map<String, Object> map = new HashMap<>();
		try {
			Enjoy enjoy = new Enjoy();
			enjoy.setFid(fid);
			enjoy.setUserid((String) session.getAttribute("userid"));
			map.put("enjoy",enjoyService.getEnjoyFid(enjoy));
			return new ReturnT<>(HttpStatus.OK, "获取点赞数据成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("获取点赞数据失败");
		}
	}
}
