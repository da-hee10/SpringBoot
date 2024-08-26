<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
	#box{
		width: 600px;
		margin: auto;
		margin-top: 100px;
		
	}
	
	textarea {
		resize: none;
	}
	
	th {
		width: 15%;
		vertical-align: middle !important;
	}
</style>

<script type="text/javascript">
	function send(f) {
		
		//입력값 검증
		let p_title	    = f.p_title.value.trim();
		let p_content   = f.p_content.value.trim();
		let photo		= f.photo.value.trim();
		
		if(p_title=='') {
			alert('제목을 입력하시오.');
			f.p_title.value = "";
			f.p_title.focus();
			return;
		}
		if(p_content=='') {
			alert('내용을 입력하시오.');
			f.p_content.value = "";
			f.p_content.focus();
			return;
		}
		if(photo=='') {
			alert('사진을 첨부하시오.');
			return;
		}
		
		//f.method = "post";
		f.action = "insert.do"; // 전송대상(PhotoInsertAction)
		f.submit();	// 전송
	}

</script>

</head>
<body>
	<!-- 파일올릴 때 폼태그 속성 꼭 넣어주기!!! -->
	<form method="POST" enctype="multipart/form-data">
		<div id="box">
		<!-- 코드 정렬 : ctrl + shift + f -->
			<div class="panel panel-primary">
				<div class="panel-heading"><h4>사진올리기</h4></div>
				<div class="panel-body">
					
					<table class="table">
						<tr>
							<th>제목</th>
							<td><input class="form-control" name="p_title"></td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td>
								<textarea class="form-control" rows="6" name="p_content"></textarea>
							</td>
						</tr>
						
						<tr>
							<th>사진</th>
							<td><input class="form-control" name="photo" type="file"></td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<input class="btn btn-success" type="button" value="메인화면" 
									   onclick="location.href='list.do'">
								<input class="btn btn-primary" type="button" value="사진올리기"
										onclick="send(this.form);">
							</td>
						</tr>
						
					</table>
				</div>
			</div>
		</div>
	</form>
	
</body>
</html>