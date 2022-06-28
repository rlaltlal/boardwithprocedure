<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<!-- 메뉴 목록-->
<table id="menu">
	<tr>
	<c:forEach var="menu" items="${menuList}">
		<td><a href="/PdsSp/List?menu_id=${menu.menu_id}&nowpage=1&pagecount=10&pagegrpnum=1">${menu.menu_name}</a></td>
	</c:forEach>
	<td><a href="/PdsSp/List?menu_id=&nowpage=1&pagecount=10&pagegrpnum=1">All</a></td>
</tr>
</table>