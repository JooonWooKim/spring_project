<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
	<form method="post">
		<div class="form-group">
			<label for="username">Username</label> 
				<input type="text" name="username" value="" class="form-control" placeholder="Enter username" id="username">
		</div>
		<p id="valid_username"></p>
		<div class="form-group">
			<label for="pwd">Password</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password">
		</div>
		<p id="valid_password"></p>
		
		<div class="form-group">
			<label for="email">Email</label> <input type="email"
				class="form-control" placeholder="Enter email" id="email">
		</div>
		<p id="valid_email"></p>

	</form>
	<button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>