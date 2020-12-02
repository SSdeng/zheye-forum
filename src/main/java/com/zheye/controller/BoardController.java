package com.zheye.controller;

import com.zheye.bean.Board;
import com.zheye.code.ReturnT;
import com.zheye.service.BoardService;
import com.zheye.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 版块控制类
 */
@Controller
@RequestMapping("/zheye-forum/board")
@SessionAttributes(value = {"board", "boardEdit"}, types = {String.class})
public class BoardController {
	@Autowired
	BoardService boardService;

	/**
	 * 添加板块信息
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/setBoard")
	@ResponseBody
	public ReturnT<?> setBoard(HttpServletRequest request) {
		try {
			Board board_add = new Board();
			//不知为何，Board board_add获取的值永远不是提交过来的结果，所以使用request.getParameter("bname")来获取
			board_add.setBname(request.getParameter("bname"));
			if (boardService.getBoardName(board_add).size() == 0) {    // 该版块名不存在
				board_add.setBid(UUIDUtil.getRandomUUID());
				boardService.setBoard(board_add);
				return ReturnT.success("添加板块成功");
			} else {
				return ReturnT.fail(HttpStatus.NOT_FOUND, "该板块已存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("添加板块失败");
		}
	}

	/**
	 * 按bid删除板块信息
	 *
	 * @param bid
	 * @return
	 */
	@DeleteMapping("/deleteBoard/{bid}")
	@ResponseBody
	public ReturnT<?> deleteBoard(@PathVariable String bid) {
		try {
			Board board_delete = new Board();
			board_delete.setBid(bid);
			boardService.deleteBoard(board_delete);
			return ReturnT.success("删除板块成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("删除板块失败");
		}
	}

	/**
	 * 修改板块
	 *
	 * @param request
	 * @return
	 */
	@PutMapping("/updateBoard")
	@ResponseBody
	public ReturnT<?> updateBoard(HttpServletRequest request) {
		try {
			Board board = new Board();
			board.setBid(request.getParameter("bid"));
			board.setBname(request.getParameter("bname"));
			if (boardService.getBoardName(board).size() == 0) {    // 该版块名不存在
				boardService.updateBoard(board);
				return ReturnT.success("修改板块成功");
			}else {
				return ReturnT.fail(HttpStatus.NOT_FOUND, "该板块已存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("修改板块失败");
		}
	}

	/**
	 * 查询板块信息（无条件）
	 */
	@GetMapping("/getBoard")
	@ResponseBody
	public ReturnT<?> getBoard() {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("board", boardService.getBoard());
			// 总板块数
			map.put("total", boardService.getCount());
			return new ReturnT<>(HttpStatus.OK, "获取板块数据成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnT.fail("获取板块数据失败");
		}
	}
}
