<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% response.setHeader("X-Frame-Options", "SAMEORIGIN"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/ROOT/navereditor/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
//전역변수선언
var editor_object = [];
	
	$(function() {
	

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : editor_object,
			elPlaceHolder : "editor",
			sSkinURI : "/ROOT/navereditor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,
				fOnBeforeUnload : function() {
				}
			},
			fCreator : "createSEditor2"
		});

		//전송버튼 클릭이벤트
		$("#savebutton").click(function() {
			//id가 smarteditor인 textarea에 에디터에서 대입
			editor_object.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증

			//폼 submit
			$("#frm").submit();
		});
	});
</script>

</head>
<body>
	<form id="frm" method="post" action="send.go">

		<textarea id="editor" style="HEIGHT: 220px; WIDTH: 610px" rows="10"
			cols="30" name="editor"></textarea>
		<input id="savebutton" type="button" value="저장"> <input
			onclick="location.href='navereditor';" type="button" value="취소">

	</form>
</body>
</html>