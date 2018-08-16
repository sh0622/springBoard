<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/offcanvas.css" rel="stylesheet">

     <%@ include file="/WEB-INF/views/common/jquery.jsp" %>
     
     <style>
     tr {height:40px;}
     td {padding-right:10px;}
     </style>
     <script>
     	 $(function(){
     		$("#makeBtn").click(function(){
     			if($("#bd_name").val().length < 2){
     				alert("게시판명을 입력해주세요");
     				$(this).focus();
     				return false;
     			}
     			
     			$("#frm1").submit();
     		});
     	});
     </script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<!-- 테이블 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
			
			<form action="/board/making" method="get" id="frm1">
			
			<input type="hidden" name="id" value="${studentVo.id }">
				<table>
					<tr>
						<td>게시판이름</td>
						<td><input type="text" name="bd_name" id="bd_name"></td>
						
						<td>
							<select name="bd_ck">
									<option value=1>사용</option>
									<option value=0>미사용</option>
							</select>
						</td>
						<!--  생성을 누르면 id가 안가욘  -->
						<td>
							<input type="submit" value="생성" id="makeBtn" class="btn btn-danger"/ >
						</td>
					</tr>
				</table>
			</form>
			
			<c:forEach items="${boardList }" var="vo">
				<form action="/board/update" method="get" id="${vo.bd_no}">
				<input type="hidden" name="id" value="${studentVo.id }">
				<input type="hidden" name="bd_no" value="${vo.bd_no}">
				<input type="hidden" name="bd_name" value="${vo.bd_name}">
					<table>
						<tr>
							<td class="td1">게시판이름</td>
								<td><input type="text" value="${vo.bd_name }" name="name"></td>
								<td>
									<select name="bd_ck">
										<option value=1 ${vo.bd_ck == 1 ? "selected" : ''}>사용</option>
										<option value=0 ${vo.bd_ck == 0 ? "selected" : ''}>미사용</option>
									</select>
								</td>
								<td>
									<input type="submit" value="수정" class= "btn btn-warning"/>
								</td>
						</tr>
					</table>
				</form>
			<!-- 두개를 만들고 마지막거만 삭제하면 둘다 같이 삭제됨 -->
			<!-- <script>
				$(function(){
					$("select[name=bd_ck]").change(function(){
						$("#${vo.bd_no}").submit();
					});
				});
			</script> -->
			</c:forEach>
			
			
			</div>
			<%@ include file="/WEB-INF/views/common/right.jsp"%>
			<hr>

		</div>
		<!--/.container-->
	</div>
</body>
</html>