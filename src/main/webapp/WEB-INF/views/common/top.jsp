<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/login/main">HOME</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav" style="float: none">
				<c:forEach items="${boardList}" var="vo">
					<c:if test="${vo.bd_ck == '1' }">
						<li><a href="/post/list?bd_no=${vo.bd_no}&bd_name=${vo.bd_name}">${vo.bd_name }</a></li>
					</c:if>
				</c:forEach>
				<li style="float: right"><a href="/login/logout">logout</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container -->
</nav>
<!-- /.navbar -->
