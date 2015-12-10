<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
<h3>사기 분류</h3>
</div>
<br>
<div style="float: right;">
<form action="insertStatementCategory.go" method="post">
	분류 번호 :<input type="text" name="cno"> 분류 이름 :<input type="text" name="cn">
	<input type="submit" class="btn btn-primary insert" value="추가">
</form>
</div>
<table class="table table-hover">
	<tr>
		<th>사기 카테고리 분류 번호</th>
		<th>사기 카테고리 분류 이름</th>
		<th>기능</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.cheatno}</td>
			<td>${list.cheatname}</td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update" value="수정">
					<input type="button"
						class="btn btn-danger delete"
						id="deleteStatementCategory.go?cn=${list.cheatname}&cno=${list.cheatno}"
						value="삭제">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>