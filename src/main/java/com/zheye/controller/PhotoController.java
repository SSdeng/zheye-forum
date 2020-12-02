package com.zheye.controller;

import com.zheye.bean.Photo;
import com.zheye.code.ReturnT;
import com.zheye.service.PhotoService;
import com.zheye.utils.FileUploadUtil;
import com.zheye.utils.PathUtil;
import com.zheye.utils.ThumbnailatorUtil;
import com.zheye.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 照片控制类
 */
@RequestMapping("/zheye-forum/photo")
@Controller
public class PhotoController {
	@Autowired
	PhotoService photoService;
	@Autowired
	FileUploadUtil fileUploadUtil;
	@Autowired
	PathUtil pathUtil;
	@Autowired
	ThumbnailatorUtil thumbnailatorUtil;

	/**
	 * 上传照片
	 *
	 * @param file
	 * @param request
	 * @param session
	 * @param fid
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/setPhoto/{fid}")
	@ResponseBody
	public ReturnT<?> setPhoto(@RequestParam("photo") MultipartFile file, HttpServletRequest request, HttpSession session, @PathVariable String fid) {
		try {
			// 当前文件大小
			long currentFileSize = file.getSize();
			// 上传源文件允许的最大值
			long fileLength = thumbnailatorUtil.getFileLength();
			if (currentFileSize <= fileLength) {
				Photo photo = new Photo();
				photo.setPhoto(fileUploadUtil.fileUpload(file, pathUtil.getPhotoPath()));
				photo.setFid(fid);
				photo.setUserid((String) session.getAttribute("userid"));
				//保存到数据库
				photo.setXid(UUIDUtil.getRandomUUID());
				photoService.setPhoto(photo);

				return ReturnT.success("上传照片成功");
			} else {
				return ReturnT.fail("请上传不超过 " + fileLength/(1024*1024) + "M 的照片!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("上传照片失败");
		}
	}

	/**
	 * 删除某一张照片
	 *
	 * @param xid
	 * @return
	 */
	@DeleteMapping("/deletePhoto/{xid}")
	@ResponseBody
	public ReturnT<?> deletePhoto(@PathVariable String xid, HttpServletRequest request) {
		try {
			// 删除照片（数据库）
			photoService.deletePhoto(xid);
			return ReturnT.success("删除照片成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("删除照片成功");
		}
	}

	/**
	 * 获取相册分类下的对应的照片
	 *
	 * @param fid     相册id
	 * @param session
	 * @return
	 */
	@GetMapping("/getPhoto/{fid}")
	@ResponseBody
	public ReturnT<?> getPhoto(@PathVariable String fid, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			Photo photo = new Photo(fid, (String) session.getAttribute("userid"));
			map.put("listPhotos", photoService.getPhoto(photo));
			return new ReturnT<>(HttpStatus.OK, "获取照片数据成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("获取照片数据失败");
		}
	}
}