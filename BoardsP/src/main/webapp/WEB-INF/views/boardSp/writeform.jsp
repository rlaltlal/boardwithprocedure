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
	#board td:first-child{width: 150px; text-align: center;}
	#board td:first-child{text-align: left;}
	#board input{width: 100%;}
	#board textarea{width: 100%;}
</style>
<script>
	window.onload=function(){
		let form1=document.getElementById('form1');
		form1.addEventListener('submit',function (e) {
			//제목,글쓴이 필수입력
			let title=document.getElementById('title').value.trim();
			let writter=document.getElementById('writter').value.trim();
			if(title==''){
				alert('제목을 입력하세요')
				e.preventDefault(); //기본이벤트 제거
				e.stopPropagation(); // 이벤트 버블링 방지(다른이벤트에 영향을 주지 못함)
			}
			else if(writter==''){
				alert('글쓴이를 입력하세요')
				e.preventDefault(); //기본이벤트 제거
				e.stopPropagation(); // 이벤트 버블링 방지(다른이벤트에 영향을 주지 못함)

			}
		})
	}
</script>
</head>
<body>
	<div id="main">
		<!-- 메뉴 목록-->
		<%@ include file="/WEB-INF/include/menus.jsp" %>
		<!-- 새글쓰기-->
		<form action="/BoardSp/Write" id="form1" method="post">
			<input type="hidden" name="menu_id" value="${menu_id}">
			<input type="hidden" name="bnum" value="${map.bnum}">
			<input type="hidden" name="lvl" value="${map.lvl}">
			<input type="hidden" name="step" value="${map.step}">
			<input type="hidden" name="nref" value="${map.nref}">
			<table id="board">
				<caption>
					<!-- jstl에는 if else가 없어서 c:choose사용 -->
					<c:choose>
						<c:when test="${map.bnum eq 0}">
							<h2>새글 쓰기</h2>				
						</c:when>
						<c:otherwise>
							<h2>답글 쓰기</h2>				
						</c:otherwise>
					</c:choose>
				</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title" ></td>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writter" id="writter" ></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="cont" id="cont" cols="30" rows="10"></textarea></td>

				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="저장"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>