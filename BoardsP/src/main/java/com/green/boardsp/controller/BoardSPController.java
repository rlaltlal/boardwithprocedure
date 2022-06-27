package com.green.boardsp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.boardsp.service.BoardSpService;
import com.green.boardsp.vo.BoardVo;
import com.green.menus.service.MenuService;
import com.green.menus.vo.MenuVo;

@Controller
public class BoardSPController {
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private BoardSpService boardSpService;
	
	@RequestMapping("/BoardSp/List") // BoardSp/List?menu_id=MENU03
	public ModelAndView list(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		List<MenuVo> menuList=menuService.getMenuList();
		
		String menu_id=(String)map.get("menu_id");
		MenuVo currMenu=null;
		if(menu_id!=null)
			currMenu=menuService.getMenuView(menu_id);
		else
			currMenu=new MenuVo(" ","전체","0");
		
		List<BoardVo> boardList=boardSpService.getBoardList(map);
			
		mv.addObject("boardList",boardList);
		mv.addObject("menuList",menuList);
		mv.addObject("currMenu",currMenu);
		mv.setViewName("boardSp/list");
		return mv;
	}
	
	@RequestMapping("/BoardSp/View") // BoardSp/View?menu_id=MENU03
	public ModelAndView view(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		
		String menu_id=(String)map.get("menu_id");
		List<MenuVo> menuList=menuService.getMenuList();
		BoardVo boardVo=boardSpService.getView(map);
		
		// 오라클에 저장된 개행문자 "\n" -> <br>태그로 변경
		boardVo.setCont(boardVo.getCont().replace("\n", "<br>"));
		
		mv.addObject("menu_id",menu_id);
		mv.addObject("menuList",menuList);
		mv.addObject("vo",boardVo);
		
		mv.setViewName("boardSp/view");
		return mv;
		
	}
	
	@RequestMapping("/BoardSp/UpdateForm") // BoardSp/Update?idx=3&menu_id=MENU01
	public ModelAndView updateform(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		String menu_id=(String)map.get("menu_id");
		List<MenuVo> menuList=menuService.getMenuList();
		BoardVo boardVo=boardSpService.getView(map);
		
		mv.addObject("menu_id",menu_id);
		mv.addObject("menuList",menuList);
		mv.addObject("boardVo",boardVo);
		mv.setViewName("boardSp/update");
		return mv;
	}
	@RequestMapping("/BoardSp/Update") // BoardSp/Update
	public ModelAndView update(@RequestParam HashMap<String,Object> map) {
		// map : idx= 1, menu_id= MENU01, title= JAVA 게시물, cont= 내용 이곳은 줄바꿈
		ModelAndView mv=new ModelAndView();
		String menu_id=(String)map.get("menu_id");
		boardSpService.updateBoard(map);
		
		mv.setViewName("redirect:/BoardSp/List?menu_id="+menu_id);
		return mv;
	}
	
	@RequestMapping("/BoardSp/Delete") // BoardSp/Delete?idx=3&menu_id=MENU01
	public ModelAndView delete(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		String menu_id=(String) map.get("menu_id");
		boardSpService.deleteBoard(map);
		mv.setViewName("redirect:/BoardSp/List?menu_id="+menu_id);
		return mv;
	}
	
	@RequestMapping("/BoardSp/WriteForm") // BoardSp/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0
	public ModelAndView writeform(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		String menu_id=(String)map.get("menu_id");
		List<MenuVo> menuList=menuService.getMenuList();
		
		mv.addObject("menu_id",menu_id);
		mv.addObject("menuList",menuList);
		mv.addObject("map",map);
		mv.setViewName("boardSp/writeform");
		return mv;
	}


	@RequestMapping("/BoardSp/Write") // /BoardSp/Write
	public ModelAndView write(@RequestParam HashMap<String,Object> map) {
		//map :  menu_id= MENU01, bnum= 0, lvl= 0, step= 0, nref= 0, title= 제목, writter= 글쓴이, cont= 내용
		ModelAndView mv=new ModelAndView();
		String menu_id=(String)map.get("menu_id");
		
		boardSpService.boardWrite(map);
		mv.setViewName("redirect:/BoardSp/List?menu_id="+menu_id);
		return mv;
	}
}
