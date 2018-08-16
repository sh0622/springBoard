<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="/js/jquery-1.12.4.js"></script>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/offcanvas.css" rel="stylesheet">
<style>

#boardStyle {
	border : 1px solid #ededed;
	margin-top : 20px;
	padding : 30px;
	
}


#fileWrap {
	margin-bottom: 50px;
}

#btnWrap{
	margin-bottom: 50px;
	padding-right:10px;
}

.fontCss {
	font-weight: lighter;
}

.button {
	width: 100%;
	height: 30px;
}

.button input {
	float: right;
	margin-right: 5px;
}

.form-group{
	height:30px;
	clear:both;
}

.btn-default{
	border-color:#fff;
}

.rembtn{
	padding:3px 3px;
	font-size:13px;
}

#addRcomBtn{
	width:15%;
	padding : 8px;
}

.form-control{
	width:85%;
	float:left;
	margin-bottom:20px;
}
</style>

<script>
	$(function() {
		$("#updateBtn").click(function() {
			$("#frm").submit();
		});

		$("#deleteBtn").click(function() {
			$("#frm2").submit();
		});

		$("#recomentBtn").click(function() {
			$("#frm3").submit();
		});

		if ( <%= request.getAttribute("memCheck") %> == 1) {
			$("#buttonWrap").css("display", "block");
		} else {
			$("#buttonWrap").css("display", "none");
		}

		$(".remRecomBtn").click(function() {
			$("#rn_no").val($(this).data("name2"));
			$("#pt_no").val($(this).data("name3"));
			$("#frm4").submit();
		});
	});
	
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/top.jsp"%>

	<!-- <form action="/postDetail" method="get" id="frm">
		<input type="hidden" name="re_no" id="re_no">
	</form> -->

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
			
				<div class="row">
					<div class="col-xs-12 blog-main">
						<h2 class=" sub-header">상세페이지</h2>
						<div class="table-responsive ">

							<form class="form-horizontal" role="form" action="/recomment/remove" method="post" id="frm4">
								<input type="hidden" name="rn_no" id="rn_no">
								<input type="hidden" name="pt_no" id="pt_no">
							</form>

							<!-- 여기서 부터 가져옴 -->
							<div class="col-xs-12 col-sm-12" id="boardStyle" style="margin-top:20px;">
								<div class="form-group " >
									<label for="pic" class="col-sm-2 control-label">제목</label>
									<div class="col-sm-10">
										<label name="title" class="fontCss">${postVo.pt_title }</label>
									</div>
								</div>

								<div class="form-group ">
									<label for="id" class="col-sm-2 ">글내용</label>
									<div class="col-sm-10">
										<label name="content" class="fontCss">${postVo.pt_content }</label>
									</div>
								</div>
								
								
								<!--  첨부파일 -->
								<div class="form-group "  id="fileWrap">
									<label for="name" class="col-sm-2 control-label">첨부파일</label>
									<div class="col-sm-10">
										<c:forEach items="${fileList }" var="list">
											<a href="/post/fileDownload?file_name=${list.file_name }" class="fontCss">
												${list.file_name }
											</a>
											<br>
										</c:forEach>
									</div>
								</div>

								<!-- 수정/삭제/답글 버튼 -->
								<div class="form-group " id="btnWrap">
									<div class="button">
										<div id="buttonWrap">
											<form class="form-horizontal" role="form"
												action="/post/update" method="get" id="frm">
												<input type="hidden" name="pt_no" value="${postVo.pt_no}">
												<input type="button" value="수정" id="updateBtn" class="btn btn-info">
											</form>
											<form class="form-horizontal" role="form"
												action="/post/delete" method="post" id="frm2">
												<input type="hidden" name="pt_no" value="${postVo.pt_no}">
												<input type="button" value="삭제" id="deleteBtn" class="btn btn-info">
											</form>
										</div>
										<form class="form-horizontal" role="form" action="/post/child"
											method="get" id="frm3">
											<input type="hidden" name="pt_no" value="${postVo.pt_no}">
											<input type="hidden" name="bd_no" value="${postVo.bd_no}">
											<input type="hidden" name="pt_group" value="${postVo.pt_group}">
											<input type="hidden" name="id" value="${postVo.id}">
											<input type="button" value="답글" id="recomentBtn" class="btn btn-info">
										</form>
									</div>
								</div>

								<!-- 댓글-->
								
								<div class="form-group">
									<label for="call_cnt" class="col-sm-2 control-label" style="line-height:30px;">댓글</label>
									<div class="col-sm-10">

										<form action="/recomment/add" method="post">
<%-- 											<input type="hidden" name="id" value="${sutdentVo.id }">  --%>
												<input type="hidden" name="pt_no" value="${postVo.pt_no }">
											<c:forEach items="${recommentList }" var="recom">

												<label class="fontCss">${recom.re_contnet }</label>
												<label class="fontCss">[${recom.std_id } &#47; <fmt:formatDate
														value="${recom.re_date }" pattern="yyyy-MM-dd" />]
												</label>

												<c:if test="${(recom.id == studentVo.id) && (recom.re_remove == 0)}">
													<!--  댓글 삭제 버튼 -->
													
													<button type="button" class="remRecomBtn btn btn-default rembtn"  
														data-name2="${recom.re_no}" data-name3="${recom.pt_no }">
														<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
													</button>
												
												</c:if>
												<br>
											</c:forEach>
											
											<input type="text" name="recomment"  class="form-control" maxlength="500">
											<input type="submit" value="댓글저장" id="addRcomBtn" class="btn btn-primary btn-sm">
										</form>
									</div>
								</div>
							</div>
							<!--/row-->
						</div>
					</div>
				</div>
			</div>

			<!--/.col-xs-12.col-sm-9-->

			<%@ include file="/WEB-INF/views/common/right.jsp"%>
			<hr>
		</div>
		<!--/.container-->
	</div>

</body>
</html>