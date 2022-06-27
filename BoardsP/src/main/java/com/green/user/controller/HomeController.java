package com.green.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.user.service.UserService;
import com.green.user.vo.UserVo;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login") // 현재 servlet xml - interceptor 주석처리 해놓음 
	public String login() {
		return "user/login"; // web-inf/views/user/login.jsp
	}
	
	@RequestMapping("/loginProcess")
	public String loginProcess(HttpSession session,@RequestParam HashMap<String,Object> map ) {
		if(session.getAttribute("login")!=null) {
			// session에 login이라는 값이 존재한다면
			session.removeAttribute("login"); //기존값 제거
		}
		//로그인에 성공하면 UserVo 객체 반환
		UserVo userVo =userService.login(map);
		String returnURL="";
		if(userVo!=null) {//로그인 성공
			session.setAttribute("login", userVo);
			returnURL="redirect:/"; 
		}else {//로그인 실패
			returnURL="redirect:/login";
			
		}
		return returnURL;
	}
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/"; // 로그아웃시 주소 -> /login
	}

	@RequestMapping("/User/List")
	public String list(Model model) {
		List<UserVo> userList=userService.getUserList();
		model.addAttribute("userList",userList);
		return "list";
	}
	
	@RequestMapping("/{val}")
	public String html(@PathVariable String val) {
		String loc="redirect:/static/html/";
		switch(val) {
		case "a": loc+="ajax01.html"; break;
		case "b": loc+="ajax02.html"; break;
		case "c": loc+="ajax03.html"; break;
		case "d": loc+="ajax04.html"; break;
		}
		return loc;
	}
	
	@RequestMapping("/ajax")
	public void ajax(String v,HttpServletResponse response) {
		String fmt="{\r\n"
				+ "	\"id\":\"sky\",\r\n"
				+ "	\"pass\":\"234\",\r\n"
				+ "	\"v\":\"%s\"\r\n"
				+ "}";
		String data=String.format(fmt, v);
		try{
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch(IOException e) {
			
		}
	}
	
	// json library 사용 : map data -> json으로 출력
	// pom.xml - jackson-databind library
	// @ResponseBody 사용 : viewresolver 거치지않고 xml,json을 
	// 출력 가능하게 한다.
	@RequestMapping("/ajax2")
	@ResponseBody
	public Map<String,Object> ajax2(String v) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id","sky");
		map.put("pass","234");
		map.put("v",v);
		return map;
	}
	
	@RequestMapping("/ajaxlist")
	@ResponseBody
	public Map<Integer,Object> ajaxlist() {
		List<UserVo> list=userService.getUserList();
		Map<Integer,Object> map=new HashMap<Integer,Object>();
		//list -> map
		for (int i = 0; i < list.size(); i++) {
			map.put(i, list.get(i));
		}
		return map;
		
	}

	//반복문 없이 list를 map에 바로 put
	@RequestMapping("/ajaxlist2")
	@ResponseBody
	public Map<String,Object> ajaxlist2() {
		List<UserVo> list=userService.getUserList();
		Map<String,Object> map=new HashMap<>();
		map.put("data", list);
		return map;
		
	}


}
