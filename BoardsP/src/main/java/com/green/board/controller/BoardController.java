package com.green.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.service.impl.BoardService;
import com.green.board.vo.BoardVo;
import com.green.menus.service.MenuService;
import com.green.menus.vo.MenuVo;

@Controller
@RequestMapping("/Board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private MenuService menuService;
	
	
	//link : /Board/List
	@RequestMapping("/List")
	public ModelAndView list(String menu_id) {
		ModelAndView mv=new ModelAndView();
		List<BoardVo> boardList=null;
		List<MenuVo> menuList=null;
		menuList=menuService.getMenuList();
		boardList=boardService.getBoardList(menu_id);
		MenuVo currMenu=menuService.getMenuView(menu_id); //현재메뉴
		mv.setViewName("board/list"); // WEB-INF/views/board/list.jsp
		mv.addObject("boardList",boardList);
		mv.addObject("menuList",menuList);
		mv.addObject("currMenu",currMenu);
		return mv;
	}
	
	// menu_id=${vo.menu_id}&idx=__&bnum=${vo.bnum}&lvl=${vo.lvl}&step=${vo.step}&nref=${vo.nref}
	@RequestMapping("/WriteForm")
	public ModelAndView writeform(BoardVo vo) {
		//@reqpar은 map에만 사용
		ModelAndView mv=new ModelAndView();
		List<MenuVo> menuList=menuService.getMenuList();
		if(vo.getBnum()>0) { //답글처리
			HashMap<String,Object> map=new HashMap<String, Object>();
			map.put("idx", vo.getIdx());
			vo=boardService.getBoard(map);
			vo.setCont(vo.getCont()+"\n================\n");
		}
		mv.setViewName("/board/writeform");
		mv.addObject("menuList",menuList);
		mv.addObject("menu_id",vo.getMenu_id());
		mv.addObject("vo",vo);
		return mv;
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(BoardVo vo) {
		// vo(menu_id,title,writter,cont,bnum,lvl,step,nref)
		ModelAndView mv=new ModelAndView();

		//새글 저장
		boardService.boardInsert(vo);
		
		//
		mv.setViewName("redirect:/Board/List?menu_id="+vo.getMenu_id());
		return mv;
	}
	
	@RequestMapping("/View")
	public ModelAndView view(@RequestParam HashMap<String,Object> map) {
		// map(menu_id,idx)
		ModelAndView mv=new ModelAndView();
		
		//메뉴
		List<MenuVo> list=menuService.getMenuList();
		
		//글 조회
		String idx=(String)map.get("idx");
		BoardVo vo=boardService.getBoard(map);
		vo.setCont(vo.getCont().replace("\n","<br>"));
		//줄바꿈처리
		mv.addObject("menuList",list);
		mv.addObject("vo",vo);
		mv.setViewName("/board/view");
		return mv;
	}
	
	@RequestMapping("/Delete")
	public ModelAndView delete(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		boardService.deleteBoard(map);
		mv.addObject("map", map);
		mv.setViewName("redirect:/Board/List?menu_id="+map.get("menu_id"));
		System.out.println(mv);
		return mv;		
	}
	
	// link: /Board/UpdateForm?idx=5&menu_id=MENU01
	@RequestMapping("/UpdateForm")
	public ModelAndView updateform(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		//메뉴
		List<MenuVo> list=menuService.getMenuList();
		BoardVo boardVo=boardService.getBoard(map);
		
		mv.addObject("menuList",list);
		mv.addObject("menu_id",(String)map.get("menu_id"));
		mv.addObject("boardVo",boardVo);
		mv.setViewName("board/update");
		return mv;
	}
	
	// link:/Board/Update (idx,menu_id,title,cont)
	@RequestMapping("/Update")
	public ModelAndView update(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv=new ModelAndView();
		boardService.boardUpdate(map);
		mv.setViewName("redirect:/Board/List?menu_id="+map.get("menu_id"));
		return mv;
	}
	
	
}
