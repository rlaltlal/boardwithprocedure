package com.green.boardsp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.boardsp.dao.BoardSpDao;
import com.green.boardsp.service.BoardSpService;
import com.green.boardsp.vo.BoardVo;

@Service("boardSpService")
public class BoardSpServiceImpl implements BoardSpService{

	@Autowired
	private BoardSpDao boardSpDao;
	
	@Override
	public List<BoardVo> getBoardList(HashMap<String,Object> map) {
		List<BoardVo> list=null;
		list=boardSpDao.getBoardList(map);
		return list;
	}

	@Override
	public BoardVo getView(HashMap<String, Object> map) {
		BoardVo vo=null;
		vo=boardSpDao.getView(map);
		return vo;
	}

	@Override
	public void updateBoard(HashMap<String, Object> map) {
		boardSpDao.updateBoard(map);
		
	}

	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		boardSpDao.deleteBoard(map);
		
	};;

	@Override
	public void boardWrite(HashMap<String, Object> map) {
		// bnum, lvl, step, nref String -> int
		int bnum= Integer.parseInt(((String)map.get("bnum"))) ;
		int lvl = Integer.parseInt(((String)map.get("lvl")));
		int step= Integer.parseInt(((String)map.get("step"))) ;
		int nref= Integer.parseInt(((String)map.get("nref")));
		map.put("bnum",bnum);
		map.put("lvl",lvl);
		map.put("step",step);
		map.put("nref",nref);

		boardSpDao.boardWrite(map);
		
	}

}
