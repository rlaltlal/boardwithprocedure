package com.green.pdssp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.pdssp.dao.PdsSpDao;
import com.green.pdssp.vo.FilesVo;
import com.green.pdssp.vo.PdsVo;

@Repository
public class PdsSpDaoImpl implements PdsSpDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		System.out.println("dao map : "+map);
		sqlSession.selectList("PdsSp.PdsPagingList",map);
		List<PdsVo> list = (List<PdsVo>) map.get("result");
		System.out.println("dao after map : "+map);
		return list;
	}

	@Override
	public PdsVo getPdsView(HashMap<String, Object> map) {
		sqlSession.selectList("PdsSp.PdsView",map);
		List<PdsVo> pdsList=(List<PdsVo>)map.get("result");
		PdsVo pdsVo=pdsList.get(0);
		return pdsVo;
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		sqlSession.selectList("PdsSp.FileList",map);
		List<FilesVo> list=(List<FilesVo>) map.get("result");
		return list;
	}

	@Override
	public void setWrite(HashMap<String, Object> map) {
		sqlSession.insert("PdsSp.PdsWrite",map);
		
	}

	@Override
	public void setUpdate(HashMap<String, Object> map) {
		sqlSession.update("PdsSp.PdsUpdate",map);
		
	}

	@Override
	public void setDelete(HashMap<String, Object> map) {
		sqlSession.delete("PdsSp.PdsDelete",map);
	}

}
