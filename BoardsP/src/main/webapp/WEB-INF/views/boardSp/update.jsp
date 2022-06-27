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
	.td1{width: 150px; text-align: center;}
	.td2{width: 400px; text-align: left;}
	.td3{width: 150px; text-align: center;}
	.td4{width: 400px; text-align: left;}
	[type=text]{width: 100%;}
	textarea{width: 100%; height: 300px;}
</style>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function () {
		$('#form1').on('submit',function () {
			if($('[name=title]').val().trim()==''){
				alert('제목을 입력해주세요.')
				return false;

			}
		})
		$('[type=button]').on('click',function () {
			$(location).attr('href','/Board/List?menu_id=${menu_id}')
		})
		$('#btnLinkBtn').on('click',function (e) {
			e.preventDefault();
			e.stopPropagation();
			alert('click');
			$(location).href($(this).prop(href))
		})

		//a tag 버튼으로 사용하려면 href=""로 하고, href=""는 새로고침기능이므로
		//이벤트를 막아야함
		$('#btn1').on('click',function (e) {
			
		})

	})
</script>
</head>
<body>
	<div id="main">
		<!-- 메뉴 목록-->
		<%@ include file="/WEB-INF/include/menus.jsp" %>
		<!-- 새글쓰기-->
		<form action="/BoardSp/Update" id="form1" method="post">
			<input type="hidden" name="idx" id="idx" value="${boardVo.idx}">
			<input type="hidden" name="menu_id" id="menu_id" value="${menu_id}">
			<table id="board">
				<caption><h2>${menu_id} 게시물 수정</h2></caption>
				<tr>
					<td class="td1">메뉴 아이디</td>
					<td class="td2" colspan="3">${menu_id}</td>
				</tr>
				<tr>
					<td class="td1">번호</td>
					<td class="td2">${boardVo.idx}</td>
					<td class="td3">날짜</td>
					<td class="td4">${boardVo.regdate}</td>
				</tr>
				<tr>
					<td class="td1">글쓴이</td>
					<td class="td2">${boardVo.writter}</td>
					<td class="td3">조회수</td>
					<td class="td4">${boardVo.readcount}</td>
				</tr>
				<tr>
					<td class="td1">제목</td>
					<td class="td2" colspan="3">
						<input type="text" name="title" id="" value="${boardVo.title}">
					</td>
				</tr>
				<tr>
					<td class="td1">내용</td>
					<td class="td2" colspan="3">
						<textarea name="cont" id="" cols="30" rows="10">${boardVo.cont}</textarea>
						<!-- textarea는 줄바꿈처리 안해도됨-->
					</td>
				</tr>
				<tr>
					<td class="td1" colspan="4">
						<input type="submit" value="수정">
						<input type="button" value="글 목록" id="btnList">
						<a href="/BoardSp/List?menu_id=${menu_id}" id="btnLinkBtn">글목록</a>
						<a href="" id="btn1">글목록</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>