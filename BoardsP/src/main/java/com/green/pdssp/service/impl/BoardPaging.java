package com.green.pdssp.service.impl;

import com.green.pdssp.vo.PdsVo;

public class BoardPaging {
	//생성자를 통해 넘어온값
	private int nowpage;
	private int pagecount;
	private int totalcount;
	private int pagetotalcount;
	private int totalpagecount;
	private int pagegrpnum;

	//pagin.jspf가 사용할 변수.. 세팅해줘야함
	private boolean isshowpageprev=true;
	private boolean isshowpagenext=true;
	
	private int pagestartnum;
	private int pageendnum;

	private int prevnowpage;
	private int nextnowpage;

	private int totalrecordpagecount; // 총 페이지수

	public BoardPaging(int nowpage,int pagecount,int totalcount,int pagetotalcount,int pagegrpnum){
		this.nowpage = nowpage ;
		this.pagecount = pagecount ;
		this.totalcount = totalcount ;
		this.pagetotalcount = pagetotalcount ;
		this.pagegrpnum = pagegrpnum ;
	}

	public PdsVo getPdsPagingInfo(){
		PdsVo pdsVo=new PdsVo();

		//생성자에서 넘어오지 않은값 세팅
		//총페이지 수
		this.totalrecordpagecount=(int) Math.ceil((double)this.totalcount / (double)this.pagecount);
	
		//페이지 시작번호
		//   pagestartnum               pageendnum      pagegrpnum
		//       1  2  3  4  5  6 7  8  9  10   > >>     1
		// << < 11 12 13 14 15 16 17 18 19 20   > >>     2
		this.pagestartnum=(this.pagegrpnum-1)*this.pagetotalcount+1;

		//페이지 끝번호
		this.pageendnum=
			(totalrecordpagecount<this.pagegrpnum*this.pagetotalcount)?
				totalrecordpagecount:this.pagegrpnum*this.pagetotalcount;

		//[처음] [이전] 출력 여부
		if(this.pagestartnum == 1){
			this.isshowpageprev=false;
		}
	
		//[다음] [마지막] 출력 여부
		if(this.pageendnum == this.totalrecordpagecount){
			this.isshowpagenext=false;
		}

		//이전 10개를 클릭했을떄 이동할 페이지 번호
		this.prevnowpage=this.pagestartnum-1;

		//다음 10개를 클릭했을떄 이동할 페이지 번호
		this.nextnowpage=this.pageendnum-1;
		
		this.totalpagecount=this.totalrecordpagecount;
		
		
		pdsVo.setNowpage(nowpage);
		pdsVo.setPrevnowpage(prevnowpage);
		pdsVo.setNextnowpage(nextnowpage);
		
		pdsVo.setTotalcount(totalcount);
		pdsVo.setTotalpagecount(totalpagecount);
		
		pdsVo.setPagestartnum(pagestartnum);
		pdsVo.setPageendnum(pageendnum);
		
		pdsVo.setPagecount(pagecount);
		pdsVo.setPagegrpnum(pagegrpnum);
		
		pdsVo.setIsshowpageprev(isshowpageprev);
		pdsVo.setIsshowpagenext(isshowpagenext);
		
		pdsVo.setTotalrecordpagecount(totalrecordpagecount);

		return pdsVo;
	}

	public int getNowpage() {
		return nowpage;
	}

	@Override
	public String toString() {
		return "BoardPaging [nowpage=" + nowpage + ", pagecount=" + pagecount + ", totalcount=" + totalcount
				+ ", pagetotalcount=" + pagetotalcount + ", totalpagecount=" + totalpagecount + ", pagegrpnum="
				+ pagegrpnum + ", isshowpageprev=" + isshowpageprev + ", isshowpagenext=" + isshowpagenext
				+ ", pagestartnum=" + pagestartnum + ", pageendnum=" + pageendnum + ", prevnowpage=" + prevnowpage
				+ ", nextnowpage=" + nextnowpage + ", totalrecordpagecount=" + totalrecordpagecount + "]";
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPagetotalcount() {
		return pagetotalcount;
	}

	public void setPagetotalcount(int pagetotalcount) {
		this.pagetotalcount = pagetotalcount;
	}

	public int getTotalpagecount() {
		return totalpagecount;
	}

	public void setTotalpagecount(int totalpagecount) {
		this.totalpagecount = totalpagecount;
	}

	public int getPagegrpnum() {
		return pagegrpnum;
	}

	public void setPagegrpnum(int pagegrpnum) {
		this.pagegrpnum = pagegrpnum;
	}

	public boolean isIsshowpageprev() {
		return isshowpageprev;
	}

	public void setIsshowpageprev(boolean isshowpageprev) {
		this.isshowpageprev = isshowpageprev;
	}

	public boolean isIsshowpagenext() {
		return isshowpagenext;
	}

	public void setIsshowpagenext(boolean isshowpagenext) {
		this.isshowpagenext = isshowpagenext;
	}

	public int getPagestartnum() {
		return pagestartnum;
	}

	public void setPagestartnum(int pagestartnum) {
		this.pagestartnum = pagestartnum;
	}

	public int getPageendnum() {
		return pageendnum;
	}

	public void setPageendnum(int pageendnum) {
		this.pageendnum = pageendnum;
	}

	public int getPrevnowpage() {
		return prevnowpage;
	}

	public void setPrevnowpage(int prevnowpage) {
		this.prevnowpage = prevnowpage;
	}

	public int getNextnowpage() {
		return nextnowpage;
	}

	public void setNextnowpage(int nextnowpage) {
		this.nextnowpage = nextnowpage;
	}



	
}
