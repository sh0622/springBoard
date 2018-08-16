<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/offcanvas.css" rel="stylesheet">

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>

<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">


var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

$(function(){
	// 현재 있는 파일 수
    var cnt = $("#fileListWrap ul li").length;
	// 업로드 가능한 파일갯수
	var total = 5-cnt;
	// 추가될 input
	var fileInput = $("#fileWrap").length;
	
	if(cnt==5){
		$("#fileWrap").css("display","none");
	}else{
		$("#fileWrap").css("display","block");
	}
	
    //servlet으로 넘겨줌 얼마 남았는지
	$("#file_cnt").val(fileInput);
	
	$(".btnPlus").click(function(){
		if(fileInput < total){
			var tag = "<div id=\"fileWrap\"><input type=\"file\" name=\"uploadFile\"></div>";
			$("#fileWrap").append(tag);
			fileInput++;
			$("#file_cnt").val(fileInput);
		}
	});
	
	$(".btnMin").click(function(){
		if(cnt>0){
			fileInput--;
			$("#file_cnt").val(fileInput);
			$("div[id=fileWrap]:last").remove();
		}
	});
});

</script>

</head>
<body>
<%@ include file="/WEB-INF/views/common/top.jsp" %>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>

				<!-- 여기서 부터 가져옴 -->
				<div class="col-xs-12 col-sm-12">
					<form class="form-horizontal" role="form" action="/post/update" method="post" id="frm" enctype="multipart/form-data">
						<input type="hidden" name="bd_no" value="${postVo.bd_no }" >
						<input type="hidden" name="pt_no" value="${postVo.pt_no }" >
						
						<div class="form-group">
							<label for="pic" class="col-sm-1 control-label" style="text-align: left;">제목</label>
							<div class="col-sm-10">
								<input type="text" name="title" value="${postVo.pt_title }">
							</div>
						</div>

						<div class="form-group">
							<!-- 이 부분을 기반으로 에디터를 꾸며준다. -->
							<textarea name="smarteditor" id="smarteditor" rows="10" cols="50" style="width: 70%; height: 412px;">
								${postVo.pt_content }
							</textarea>
						</div>

						<label for="name" class="col-sm-2 control-label" style="text-align: left;">첨부파일</label>
						<div class="col-sm-10">
							<div id="fileListWrap">
								<ul style="list-style: none;">
								<c:forEach items="${fileList }" var="list">
									<li><label class="control-label" style="display:block"> ${list.file_name }</label></li>
								</c:forEach>
								</ul>
								
							</div>
							<input type="hidden" name="file_cnt" id="file_cnt">
							<div id="fileWrap">
								<input type="file" name="uploadFile" id="file">
							</div>
							<button type="button" class="glyphicon glyphicon-plus btnPlus"></button>
							<button type="button" class="glyphicon glyphicon-minus btnMin"></button>
							<input type="button" value="저장" id="savebutton" style="float: right;">
						</div>
					</form>
				</div>
				<!--/row-->
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<%@ include file="/WEB-INF/views/common/right.jsp"%>
			<hr>

			<footer>
				<p>&copy; Company 2014</p>
			</footer>

		</div>
		<!--/.container-->
	</div>
	
</body>
</html>