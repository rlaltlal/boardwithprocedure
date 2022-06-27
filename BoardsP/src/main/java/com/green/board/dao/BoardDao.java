package com.green.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.green.board.vo.BoardVo;

public interface BoardDao {

	List<BoardVo> getBoardList(String menu_id);

	void boardInsert(BoardVo vo);

	BoardVo getBoard(BoardVo vo);

	BoardVo getBoard(Map<String, Object> map);

	void deleteBoard(HashMap<String, Object> map);

	void boardUpdate(HashMap<String, Object> map);
}
