package com.green.boardsp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.boardsp.vo.BoardVo;

@Repository("boardSpDao")
public class BoardSpDaoImpl implements com.green.boardsp.dao.BoardSpDao {
	//Stored Procedure 사용할경우 List<..> list=sqlSession.select("", ...) 결과를 list가 받을수 없음
	//결과를 받기위해서는 map에 결과를 넣고 다시 꺼내줘야함
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVo> getBoardList(HashMap<String, Object> map) {
		sqlSession.selectList("BoardSp.BoardList",map);
		List<BoardVo> list=(List<BoardVo>) map.get("result"); // map에 procedure의 out결과가 들어감
				
		return list;
	}

	@Override
	public BoardVo getView(HashMap<String, Object> map) {
		BoardVo vo=null;
		sqlSession.selectList("BoardSp.BoardView", map);
		List<BoardVo> list=(List<BoardVo>) map.get("result");
		vo=list.get(0); // 한개만 담긴 list를 꺼냄
		return vo;
	}

	@Override
	public void updateBoard(HashMap<String, Object> map) {
		sqlSession.update("BoardSp.UpdateBoard",map);
		
	}

	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		sqlSession.delete("BoardSp.DeleteBoard",map);
		
	}

	@Override
	public void boardWrite(HashMap<String, Object> map) {
		System.out.println(map);
		sqlSession.insert("BoardSp.InsertBoard",map);
		
	}

}
