package com.zheye.controller;

import com.zheye.bean.Via;
import com.zheye.code.ReturnT;
import com.zheye.service.ViaService;
import com.zheye.utils.FileUploadUtil;
import com.zheye.utils.PathUtil;
import com.zheye.utils.ThumbnailatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户头像控制类
 */
@RequestMapping("/zheye-forum/profilePhoto")
@SessionAttributes("profilePhoto")
@Controller
public class ProfilePhotoController {
	@Autowired
	ViaService viaService;
	@Autowired
	FileUploadUtil fileUploadUtil;
	@Autowired
	PathUtil pathUtil;
	@Autowired
	ThumbnailatorUtil thumbnailatorUtil;

	/**
	 * 上传用户头像（插入、修改）
	 *
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/setProfilePhoto")
	@ResponseBody
	public ReturnT<?> setUserPhoto(@RequestParam("photo") MultipartFile file, HttpSession session, HttpServletRequest request, Model model) {
		try {
			// 当前文件大小
			long currentFileSize = file.getSize();
			// 上传源文件允许的最大值
			long fileLength = thumbnailatorUtil.getFileLength();
			if (currentFileSize <= fileLength) {
				//肯定报错啊，int=null,,但是只有登录的时候才能进入该页面，故不用判断是否登录
				String userid = (String) session.getAttribute("userid");
				Via via = new Via();
				via.setUserid(userid);

				// 用于存放新生成的文件名字(不重复)
				String newFileName;
				if (viaService.getVia(userid) == null) {	//如果该用户还没有上传过头像，则进行新增操作
					// 保存文件
					newFileName = fileUploadUtil.fileUpload(file, pathUtil.getUserPath());
					via.setPhoto(newFileName);
					// 将via保存到数据库
					viaService.setVia(via);
				} else {    //如果该用户上传过头像，则进行修改操作
					// 保存文件
					newFileName = fileUploadUtil.fileUpload(file, pathUtil.getUserPath());
					via.setPhoto(newFileName);
					// 将via保存到数据库(修改)
					viaService.updateVia(via);
				}
				model.addAttribute("profilePhoto", newFileName);

				return new ReturnT<>(HttpStatus.OK, "修改头像成功", newFileName);
			} else {
				return ReturnT.fail("请上传不超过 " + fileLength/(1024*1024) + "M 的头像!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("修改头像失败");
		}
	}
}
