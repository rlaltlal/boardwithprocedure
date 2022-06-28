package com.green.pdssp.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.menus.service.MenuService;
import com.green.menus.vo.MenuVo;
import com.green.pdssp.service.PdsSpService;
import com.green.pdssp.vo.FilesVo;
import com.green.pdssp.vo.PdsVo;
import com.green.user.vo.UserVo;

@Controller
public class PdsSpController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PdsSpService pdsSpService;
	
	//로그인---------------------------------------------------------
	@RequestMapping("/loginProcess2")
	public  ModelAndView  loginProcess(
		HttpSession     session,
		@RequestParam   HashMap<String, Object> map) {
		ModelAndView mv=new ModelAndView();
		session.setAttribute("id","sky");
		mv.setViewName("redirect:/PdsSp/List?menu_id=MENU01&nowpage=1&pagecount=10&pagegrpnum=1");
		return mv;		
	}
	
	
	// 로그아웃
	@RequestMapping("/logoutProcess2") 
	public  String  logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";  // 로그아웃시 이동할 주소 -> /login
	}
	
	// PdsSp/List?menu_id=MENU01&nowpage=1&pagecount=10&pagegrpnum=1
	@RequestMapping("/PdsSp/List")
	public ModelAndView list(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		List<MenuVo> menuList=menuService.getMenuList();
		
		List<PdsVo> pdsSpList=pdsSpService.getPdsList(map);
		//pagePdsVo <- pdsSpService.getPdsList(map); 실행후 
		//             pdsSpService에서 변경된 값을 map에 돌려받고 pagePdsVo에 저장해준다
		PdsVo pagePdsVo=(PdsVo) map.get("pagePdsVo");
		
		String menu_id=(String)map.get("menu_id");
		MenuVo vo=null;
		if(menu_id!=null) {
			vo=menuService.getMenuView(menu_id);
		}
		else {
			vo=new MenuVo(null,"전체","0");
		}
		
		// 잠시 막아둠String menu_name=pagePdsVo.getMenu_name();
		
		mv.addObject("menuList",menuList);
		//mv.addObject("menu_name",menu_name);
		mv.addObject("menu_id",menu_id);
		mv.addObject("pdsSpList",pdsSpList);
		System.out.println(pagePdsVo);
		mv.addObject("pagePdsVo",pagePdsVo);
		mv.setViewName("pdsSp/list");
		return mv;
	}
	
	@RequestMapping("/PdsSp/View")
	public ModelAndView view(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		
		//메뉴 목록
		List<MenuVo> menuList=menuService.getMenuList();
		
		//idx로 검색된 pdsVo 글 정보
		PdsVo pdsVo=pdsSpService.getPdsView(map);
		
		//idx로 조회된 filesList 파일 목록
		List<FilesVo> filesList=pdsSpService.getFileList(map);
		
		String menu_id=(String)map.get("menu_id");
		
		mv.setViewName("pdsSp/view");
		mv.addObject("menuList",menuList);
		mv.addObject("pdsVo",pdsVo);
		mv.addObject("filesList",filesList);
		mv.addObject("menu_id",menu_id);
		mv.addObject("map",map);
		
		return mv;
	}
	@RequestMapping("/PdsSp/UpdateForm")
	public ModelAndView updateform(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		
		//메뉴 목록
		List<MenuVo> menuList=menuService.getMenuList();
		
		//idx로 검색된 pdsVo 글 정보
		PdsVo pdsVo=pdsSpService.getPdsView(map);
		
		//idx로 조회된 filesList 파일 목록
		List<FilesVo> filesList=pdsSpService.getFileList(map);
		
		String menu_id=(String)map.get("menu_id");

		mv.setViewName("pdsSp/updateform");
		mv.addObject("menuList",menuList);
		mv.addObject("pdsVo",pdsVo);
		mv.addObject("filesList",filesList);
		mv.addObject("menu_id",menu_id);
		mv.addObject("map",map);
		
		return mv;
	}

	@RequestMapping("/PdsSp/Update")
	public ModelAndView update(@RequestParam HashMap<String,Object> map,HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		
		pdsSpService.setUpdate(map,request);
		
		// int 데이터 파싱
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		int pagecount = Integer.parseInt(String.valueOf(map.get("pagecount")));
		int pagegrpnum = Integer.parseInt(String.valueOf(map.get("pagegrpnum")));
		String str="redirect:/PdsSp/List?menu_id=%s&nowpage=%d&pagecount=%d&pagegrpnum=%d";
		String loc=String.format(str, (String)map.get("menu_id"), nowpage, pagecount, pagegrpnum);
		mv.setViewName(loc);
		return mv;
		
	}
	@RequestMapping("/PdsSp/WriteForm") 
	// PdsSp/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0&nowpage=1&pagecount=10&pagegrpnum=1
	public ModelAndView writeform(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		
		//메뉴 목록
		List<MenuVo> menuList=menuService.getMenuList();
		
		//idx로 검색된 pdsVo 글 정보
//		PdsVo pdsVo=pdsSpService.getPdsView(map);
		
		//idx로 조회된 filesList 파일 목록
//		List<FilesVo> filesList=pdsSpService.getFileList(map);
		
		String menu_id=(String)map.get("menu_id");
		
		mv.setViewName("pdsSp/writeform");
		mv.addObject("menuList",menuList);
//		mv.addObject("pdsVo",pdsVo);
//		mv.addObject("filesList",filesList);
//		mv.addObject("menu_id",menu_id);
		mv.addObject("map",map);
		
		return mv;
	}
	
	@RequestMapping("/PdsSp/Write")
	//PdsSp/List?menu_id=MENU01&nowpage=1&pagecount=10&pagegrpnum=1
	public ModelAndView write(@RequestParam HashMap<String,Object> map, HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		
		pdsSpService.setWrite(map,request); 
		
		// int 데이터 파싱
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		int pagecount = Integer.parseInt(String.valueOf(map.get("pagecount")));
		int pagegrpnum = Integer.parseInt(String.valueOf(map.get("pagegrpnum")));
		String fmt="redirect:/PdsSp/List?menu_id=%s&nowpage=%d&pagecount=%d&pagegrpnum=%d";
		String loc=String.format(fmt, (String)map.get("menu_id"), nowpage, pagecount, pagegrpnum);
		mv.addObject("map",map);
		mv.setViewName(loc);
		return mv;
	}

}
