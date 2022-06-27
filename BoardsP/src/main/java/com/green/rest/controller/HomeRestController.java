package com.green.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.green.user.service.UserService;
import com.green.user.vo.UserVo;

//package - com.green.user : user controller
//          com.green.rest : board controller
 
// @RestController : @Controller와 @ResponseBody의 혼합
@RestController
@RequestMapping("/RestBoard")
public class HomeRestController {
	
	@Autowired
	private UserService userService;
	
	// link: /Board
	@RequestMapping(method=RequestMethod.GET, 
			headers="Accept=application/json" )
	public List<UserVo> list() {
		List<UserVo> list=userService.getUserList();
		return list;
		// map없이 List를 바로 전달
		// json 형식 [{"key":"value", ...},{...}]
	}
	
	// link: /Board/{id}
	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			headers="Accept=application/json")
	public UserVo getUserById(@PathVariable String id) {
		UserVo vo=null;
		vo=userService.getView(id);
		return vo;
	}
	
	// link: /Board, method=post
	@RequestMapping(method=RequestMethod.POST,
			headers="Accept=application/json")
	public List<UserVo> getUserByName(String name) {
		List<UserVo> list=null;
		list=userService.getViewName(name);
		return list;
	}
	
	// link: /Board/name/{name}
	@RequestMapping(value="/name/{name}",
			method=RequestMethod.GET,
			headers="Accept=application/json")
	public List<UserVo> getUserByName2(@PathVariable String name){
		List<UserVo> list=userService.getViewName(name);
		return list;
	}
	 
}
