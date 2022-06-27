<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/css/common.css">
<style>
	#board td:nth-of-type(1){width: 150px; text-align: center;}
	#board td:nth-of-type(2){width:400px; text-align: left;}
	#board td:nth-of-type(3){width: 150px; text-align: center;}
	#board td:nth-of-type(4){width:400px; text-align: left;}
	#board tr:nth-of-type(4){height: 400px;vertical-align: top;}
	#board input{width: 100%;}
	#board textarea{width: 100%; height: 400px;}
</style>
<script>

</script>
</head>
<body>
	<div id="main">
		<!-- 메뉴 목록-->
		<%@ include file="/WEB-INF/include/menus.jsp" %>
		<!-- 글 조회-->
		<table id="board">
			<caption><h2>내용 보기</h2></caption>
			<tr>
				<td>글 번호</td>
				<td>${vo.idx}</td>
				<td>조회수</td>
				<td>${vo.readcount}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${vo.regdate}</td>
				<td>작성자</td>
				<td>${vo.writter}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${vo.title}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">${vo.cont}</td>
			</tr>
			<tr>
				<td colspan="4">
					[<a href="/BoardSp/WriteForm?menu_id=${menu_id}&bnum=0&lvl=0&step=0&nref=0">새글쓰기</a>]
					[<a href="/BoardSp/WriteForm?menu_id=${menu_id}&idx=${vo.idx}&bnum=${vo.bnum}&lvl=${vo.lvl}&step=${vo.step}&nref=${vo.nref}">답글쓰기</a>]
					<!--  & < >  (html: &amp; &lt; &gt) -->
					[<a href="/BoardSp/UpdateForm?idx=${vo.idx}&menu_id=${menu_id}">수정</a>]
					[<a href="/BoardSp/Delete?idx=${vo.idx}&menu_id=${menu_id}">삭제</a>]
					<!-- 삭제하고 같이가져간 menuid로 이동 -->
					[<a href="/BoardSp/List?menu_id=${menu_id}">목록으로</a>]
					[<a href="javascript:history.back()">이전으로</a>]
					[<a href="/">Home</a>]
				</td>
			</tr>
		</table>
	</div>
</body>
</html>