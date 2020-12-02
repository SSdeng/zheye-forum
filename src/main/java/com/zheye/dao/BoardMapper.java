package com.zheye.dao;

import com.zheye.bean.Board;

import java.util.List;

public interface BoardMapper {

    // 新增板块
    void insert(Board record);

    // 按bid删除板块信息
    void deleteByKey(String bid);

    // 修改板块
    void updateByKey(Board record);

    // 查询板块的所有信息
    List<Board> selectBoard();
    
    // 按板块名查询
    List<Board> selectBoardByName(Board board);

    // 按板块ID查询板块信息
    Board selectBoardByKey(String bid);

    // 总板块数
    int selectCount();
}