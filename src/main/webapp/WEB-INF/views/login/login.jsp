<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Songs home</title>

  	<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">    
    
	<!-- jquery를 정적include해준다 -->
    <script src="/js/jquery-1.12.4.js"></script>
    
    
  </head>

  <body>
    <div class="container">
    
      <form class="form-signin" action="/login/loginProcess" method="post">
        <h2 class="form-signin-heading">로그인을 하세요</h2>
        <label for="id" class="sr-only">id</label>
        <input type="text" id="id" name="std_id" class="form-control"
        value="${param.std_id}" placeholder="아이디를 입력해주세요" required autofocus>
    
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="pw" name="pw" class="form-control" placeholder="비밀번호를 입력해주세요" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      </form>

    </div> <!-- /container -->


  </body>
</html>
