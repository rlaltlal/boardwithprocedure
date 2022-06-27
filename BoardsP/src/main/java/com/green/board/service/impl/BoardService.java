package com.green.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.dao.BoardDao;
import com.green.board.vo.BoardVo;

@Service("boardService")
public class BoardService implements com.green.board.service.BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getBoardList(String menu_id) {
		List<BoardVo> list=null;
		list=boardDao.getBoardList(menu_id);
		return list;
	}

	public void boardInsert(BoardVo vo) {
		boardDao.boardInsert(vo);
		
	}

	public BoardVo getBoard(BoardVo vo) {
		BoardVo vo2=null;
		vo2=boardDao.getBoard(vo);
		return vo2;
	}

	public BoardVo getBoard(Map<String, Object> map) {
		BoardVo vo=boardDao.getBoard(map);
		return vo;
	}

	public void deleteBoard(HashMap<String, Object> map) {
		boardDao.deleteBoard(map);
		
	}

	public void boardUpdate(HashMap<String, Object> map) {
		boardDao.boardUpdate(map);
		
	}


}
