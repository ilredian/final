<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>Category</h3>
<table class="table table-hover">
	<tr>
		<th>카테고리 번호</th>
		<th>카테고리 이름</th>
		<th>asdf</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.categoryno}</td>
			<td>${list.categoryname}</td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update"
						id="updateBoardCategory.go?cn=${list.categoryname}&cno=${list.categoryno}"
						value="수정"> <input type="button"
						class="btn btn-danger delete"
						id="deleteBoardCategory.go?cn=${list.categoryname}&cno=${list.categoryno}"
						value="삭제">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="" method="post" onsubmit="return insertBoardCategory()">
	
	<input type="submit" class="btn btn-success insert"
		id="insertBoardCategory" value="추가">
</form>
