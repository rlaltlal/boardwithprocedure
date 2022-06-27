package com.green.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.board.vo.BoardVo;

@Repository("boardDao")
public class BoardDao implements com.green.board.dao.BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVo> getBoardList(String menu_id) {
		List<BoardVo> list=null;
		list=sqlSession.selectList("Board.getBoardList",menu_id);
		return list;
	}

	@Override
	public void boardInsert(BoardVo vo) {
		int bnum=vo.getBnum();
		if(bnum==0) {//새글쓰기
			sqlSession.insert("Board.BoardInsert",vo);
		}
		else {			
			sqlSession.update("Board.UpdateRef",vo);
			sqlSession.insert("Board.ReplyBoard",vo);
		}
		
	}

	@Override
	public BoardVo getBoard(BoardVo vo) {
		vo=sqlSession.selectOne("Board.getBoard", vo);
		return vo;
	}

	//맵사용
	@Override
	public BoardVo getBoard(Map<String, Object> map) {
		//조회수 증가
		sqlSession.update("Board.ReadCountUpdate",map);
		//글 조회
		BoardVo vo=sqlSession.selectOne("Board.getBoardMap", map);
		return vo;
	}

	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		sqlSession.delete("Board.deleteBoard",map);
	}

	@Override
	public void boardUpdate(HashMap<String, Object> map) {
		sqlSession.update("Board.updateBoard",map);
	}

}
