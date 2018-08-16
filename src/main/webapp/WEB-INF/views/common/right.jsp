<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
	<div class="list-group">
		<a href="/board/list" class="list-group-item active">게시판생성</a>
		
		<!-- for문으로  -->
		<c:forEach items="${boardList}" var="vo">
			<c:if test="${vo.bd_ck == '1' }">
			<a href="/post/list?bd_no=${vo.bd_no}&bd_name=${vo.bd_name}" class="list-group-item">${vo.bd_name }</a>
			</c:if>
		</c:forEach>
	</div>
</div>
