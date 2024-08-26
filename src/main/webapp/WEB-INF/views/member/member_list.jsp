<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
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
		width: 1200px;
		margin: auto;
		margin-top: 50px;
	}
	
	#title{
		text-align: center;
		font-weight: bold;
		font-size: 32px;
		color: green; 
		text-shadow: 1px 1px 1px black;
	}
	
	#empty_msg{
		text-align: center;
		color: red;
		margin-top: 130px;
		font-weight: bold;
		font-size: 20px;
		
	}
/* 	th{
		background: #333333 !important;
		color: white;
	} */
	
	td{
		vertical-align: middle !important;
	}
	div > input[type='button'] {
		width: 80px;
	}
</style>

<script type="text/javascript">

	function del(mem_idx){
		
		// console.log(mem_idx,"삭제");
		if(confirm("정말 삭제하시겠습니까?")==false) return;
		
		// 삭제요청
		location.href = "delete.do?mem_idx=" + mem_idx;	//MemberDeleteAction

	}

</script>

<script type="text/javascript">
	//jQuery 초기화
	$(document).ready(function(){
		
		//showMessage();
		setTimeout(showMessage,100); // 0.1초후에 메시지 띄워라
		
	}); // end - ready()
	
	function showMessage(){
		 // /memberlist.do?reason=admin&mem_idx="+mem_idx =?
		 if("${ param.reason == 'fail_delete'}" == "true"){
			 alert("관리자는 본인을 삭제할 수 없습니다.!!"); 
		 }						
	}
</script>

</head>

<body>
	
	<div id="box">
		<h1 id="title">::::회원목록::::</h1>
		
		<div style="text-align: right; margin-top: 5px; margin-bottom: 5px; margin-left: 3px; float:right; ">
			<input class="btn btn-primary" type="button" value="회원가입" 
				   onclick="location.href='insert_form.do'">
		</div>
		
		<div style="text-align: right; margin-top: 5px; margin-bottom: 5px; float:right;">
		
			<!-- 로그인이 안된 경우 -->
			<c:if test="${ empty sessionScope.user }">
				<input class="btn btn-primary" type="button" value="로그인"
					   onclick="location.href='login_form.do'">
			</c:if>	 
			
			<!-- 로그인이 된 경우 ! empty 가능 -->
			<c:if test="${ not empty sessionScope.user }">  
				<b>${ sessionScope.user.mem_name }</b>님 환영합니다.
				<input class="btn btn-primary" type="button" value="로그아웃"
					   onclick="location.href='logout.do'">
			</c:if>
		</div>
		
		
		<table class="table">
			<tr class="info">
				<th>회원번호</th>
				<th style="width:70px;">회원명</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th style="width:90px;">우편번호</th>
				<th>주소</th>
				<th>아이피</th>
				<th>가입일자</th>
				<th style="width:90px;">회원등급</th>
				<th style="width:70px;">편집</th>
			</tr>
		
		<!-- Data 출력 -->
		<!-- for(MemberVo vo : list) -->
		<c:forEach var="vo" items="${ list }">
			<tr>
				<td>${ vo.mem_idx }</td>
				<td>${ vo.mem_name }</td>
				<td>${ vo.mem_id }</td>
				<td>${ vo.mem_pwd }</td>
				<td>${ vo.mem_zipcode }</td>
				<td>${ vo.mem_addr }</td>
				<td>${ vo.mem_ip }</td>
				<td>${ vo.mem_regdate }</td>
				<td>${ vo.mem_grade }</td>
				<td>
					<!--                         관리자					 또는             로그인한 본인		  -->
					<c:if test="${ sessionScope.user.mem_grade eq '관리자' or ( user.mem_idx eq vo.mem_idx) }">
						<input style="margin-bottom: 2px;" class="btn btn-success" type="button" value="수정" 
							   onclick="location.href='modify_form.do?mem_idx=${ vo.mem_idx }';">
						<input class="btn btn-danger" type="button" value="삭제" onclick="del('${ vo.mem_idx }');">
					</c:if>
				</td>
			</tr>
		</c:forEach>
				
		</table>
				
		<!-- Data가 없는 경우 -->
		<c:if test="${ empty requestScope.list }">
			<div id="empty_msg">등록된 회원정보가 없습니다.</div>
		</c:if>
				
	</div>
</body>
</html>