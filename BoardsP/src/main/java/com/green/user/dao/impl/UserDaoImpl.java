package com.green.user.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.user.dao.UserDao;
import com.green.user.vo.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<UserVo> getUserList() {
		List<UserVo> list=sqlSession.selectList("User.UserList");
		return list;
	}

	@Override
	public UserVo getView(String id) {
		UserVo vo=null;
		vo=sqlSession.selectOne("User.getView", id);
		return vo;
	}

	@Override
	public List<UserVo> getViewName(String name) {
		List<UserVo> list=null;
		list=sqlSession.selectList("User.getViewName", name);
		return list;
	}

	@Override
	public UserVo login(HashMap<String,Object> map) {
		UserVo vo=null;
		vo=sqlSession.selectOne("User.login",map);
		return vo;		
	}

}
