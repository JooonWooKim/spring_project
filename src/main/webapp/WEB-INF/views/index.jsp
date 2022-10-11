<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

    <form action="/" method="GET" class="form-inline p-2 bd-highlight">
        <div>
            <input type="text" name="searchKeyword" class="form-control" placeholder="검색">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>
    
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세 보기</a>
				<span style="float:right">Created: ${board.createDate}</span><br>
			</div>
		</div>
	</c:forEach>

	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number-1}&searchKeyword=${param.searchKeyword}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number+1}&searchKeyword=${param.searchKeyword}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<%@ include file="layout/footer.jsp"%>