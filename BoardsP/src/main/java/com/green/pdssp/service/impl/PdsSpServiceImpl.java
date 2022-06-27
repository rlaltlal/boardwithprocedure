package com.green.pdssp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.pdssp.dao.PdsSpDao;
import com.green.pdssp.service.PdsSpService;
import com.green.pdssp.vo.FilesVo;
import com.green.pdssp.vo.PdsVo;

@Service("PdsSpService")
public class PdsSpServiceImpl implements PdsSpService {

	@Autowired
	private PdsSpDao pdsSpDao;
	
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		// String -> int
		int nowpage= Integer.parseInt(((String)map.get("nowpage"))) ;
		int pagecount = Integer.parseInt(((String)map.get("pagecount")));
		map.put("nowpage",nowpage);
		map.put("pagecount",pagecount);
		
		List<PdsVo> list=pdsSpDao.getPdsList(map);
		
		//페이징을 위한 내용추가
		
		//map에 정보를 추가 
		
		//페이징을 위한 내용 추가
		
		// 1 2 3 4 5 6 7 8 9 10 [다음]
		//한페이지에 보여줄 페이지 번호의 개수 : 10
		
		//한페이지에 보여줄 자료수 : pagecount = 10
		
		//현재 페이지 정보 : nowpage = 1
		
		// pagegrpnum
		System.out.println("??");
		int pagegrpnum= Integer.parseInt(((String)map.get("pagegrpnum"))) ;

		// totalcount
		int totalcount =((Integer)map.get("totalcount")).intValue();

		int pagetotalcount=10;
		BoardPaging bp=new BoardPaging(nowpage,pagecount,totalcount,pagetotalcount,pagegrpnum);
		PdsVo pdsVo=bp.getPdsPagingInfo();
		map.put("pagePdsVo", pdsVo);
		System.out.println("service pds "+pdsVo.toString());
		
		return list;
	}

	@Override
	public PdsVo getPdsView(HashMap<String, Object> map) {
		PdsVo pdsVo=pdsSpDao.getPdsView(map);
		return pdsVo;
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		List<FilesVo> list=pdsSpDao.getFileList(map);
		return list;
	}

	@Override
	public void setWrite(HashMap<String, Object> map, HttpServletRequest request) {
		// 파일저장, 글저장
		
	}

}
