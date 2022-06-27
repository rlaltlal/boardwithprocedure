<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/css/common.css">
<style>
	h2{margin:20px;}
</style>
</head>
<body>
	<div id="main">
	<!-- 메뉴 목록-->
	<%@ include file="/WEB-INF/include/menus.jsp" %>
	<!-- 게시물 목록-->
	<table id="board">
		<caption><h2>${currMenu.menu_name } 게시판</h2></caption>
		<tr>
			<td class="right" colspan="5">
				[ <a href="/BoardSp/WriteForm?menu_id=${currMenu.menu_id}&bnum=0&lvl=0&step=0&nref=0">새글 쓰기</a> ]
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>
					<c:if test="${board.lvl eq 0}">
					${board.bnum}
					</c:if>
				</td>
				<td>
					<b style="display:inline-block;width: ${board.lvl*20}px;"></b>
					<a href="/BoardSp/View?menu_id=${board.menu_id}&idx=${board.idx}">${board.title}</a></td>
				<td>${board.writter}</td>
				<td>${board.regdate}</td>
				<td>${board.readcount}</td>
			</tr>
		</c:forEach>
		
	</table>
	</div>
</body>
</html>