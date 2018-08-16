<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>make board</title>
<link href="/jsp/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/offcanvas.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<%@ include file="/WEB-INF/views/common/jquery.jsp"%>


<script>
$(function(){
	
	$("table tbody tr").on("click", function() {
			if ($(this).data("name") == 0) {
				$("#pt_no").val($(this).data("id"));
				$("#frm").submit();
			}else if($(this).data("name") >= 1)
				alert("삭제된 게시물입니다!!");
		});
	});
</script>


</head>
<body>
	<%@ include file="/WEB-INF/views/common/top.jsp" %>

<br>
	<form action="/post/detail" method="get" id="frm">
		<input type="hidden" name="pt_no" id="pt_no">
		<input type="hidden" name="bd_name" value="${boardvo.bd_name}">
	</form>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">

				<div class="row">
					<div class="col-xs-12 blog-main">
						<h2 class="sub-header">게시판리스트</h2>
						<div class="table-responsive">

							<table class="table table-striped">
								<thead>
									<tr>
										<th>게시글 번호</th>
										<th>제목</th>
										<th>작성자 아이디</th>
										<th>작성일시</th>
									</tr>
								</thead>
								<tbody>
									<!-- npsp라는 이름으로 공백 set해준 후 el태그로 쓸 수 있다 -->
									<!-- html에서는 공백으로 띄워쓰기 안돼서 &nbsp 태그로 바꿔줌 -->
									<%
										request.setAttribute("nbsp", " ");
									%>

									<c:forEach items="${postPageList }" var="vo">
										<tr data-id="${vo.pt_no }" data-name="${vo.pt_remove }">
											<td>${vo.rn}</td>
											<td>${fn:replace(vo.pt_title, nbsp, '&nbsp;')}</td>
											<td>${vo.std_id}</td>
											<td><fmt:formatDate value="${vo.pt_date}"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>

						</div>

						<a class="btn btn-default pull-right" id="add"
							href="/post/add?bd_no=${bd_no}">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination" class="pager">
								<%=request.getAttribute("pageNavi")%>
							</ul>
						</div>

					</div>
				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<%@ include file="/WEB-INF/views/common/right.jsp"%>
		</div>
		<!--/.container-->
	</div>
</body>
</html>
