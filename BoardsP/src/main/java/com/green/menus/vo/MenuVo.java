package com.green.menus.vo;

public class MenuVo {

	private String menu_id    ;
	private String menu_name  ;
	private String menu_seq   ;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(String menu_seq) {
		this.menu_seq = menu_seq;
	}
	@Override
	public String toString() {
		return "MenuVo [menu_id=" + menu_id + ", menu_name=" + menu_name + ", menu_seq=" + menu_seq + "]";
	}
	public MenuVo(String menu_id, String menu_name, String menu_seq) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.menu_seq = menu_seq;
	}
	
	public MenuVo() {}
	public MenuVo(Object menu_id2, String menu_name2, int i) {
		// TODO Auto-generated constructor stub
	}
}                             
