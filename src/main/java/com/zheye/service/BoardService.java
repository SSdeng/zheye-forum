package com.zheye.service;

import com.zheye.bean.Board;
import com.zheye.dao.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	/**
	 * 新增板块信息
	 * @param board
	 */
	public void setBoard(Board board) {
		
		boardMapper.insert(board);
	}

	/**
	 * 按bid删除板块信息
	 * @param board_delete
	 */
	public void deleteBoard(Board board_delete) {

		boardMapper.deleteByKey(board_delete.getBid());
	}

	/**
	 * 修改板块
	 * @param board
	 */
	public void updateBoard(Board board) {
		
		boardMapper.updateByKey(board);
	}

	/**
	 * 查询板块信息（无条件）
	 * @return
	 */
	public List<Board> getBoard() {

		return boardMapper.selectBoard();
	}

	/**
	 * 按板块ID查询板块信息
	 * @param bid
	 * @return
	 */
	public Board getBoardId(String bid) {

		return boardMapper.selectBoardByKey(bid);
	}

	/**
	 * 板块名查询板块信息
	 * @param board
	 * @return
	 */
	public List<Board> getBoardName(Board board) {

		return boardMapper.selectBoardByName(board);
	}

	/**
	 * 总板块数
	 * @return
	 */
    public int getCount() {
    	return boardMapper.selectCount();
    }
}
