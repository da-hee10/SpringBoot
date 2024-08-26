<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL -->    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
   #box{
       width: 1200px;
       margin: auto; /* 중앙정렬(Horizontal) */
       margin-top: 20px;
   }
   
   .mycolor{
      background: #333333;
      color: white;
   }
</style>



</head>
<body>
  <div id="box">
     <table class="table">
        <tr class="mycolor">
           <th>사번</th>
           <th>사원명</th>
           <th>성별</th>
           <th>부서번호</th>
           <th>직위</th>
           <th>입사일자</th>
           <th>상사번호</th>
           <th>년봉</th>
        </tr>
        <!-- data -->
        
        <!-- for(SawonVo vo  : list) 동일 -->
        <c:forEach var="vo"  items="${ list }">
	        <tr>
	           <td>${ vo.sabun }</td>
	           <td>${ vo.saname }</td>
	           <td>${ vo.sasex }</td>
	           <td>${ vo.deptno }</td>
	           <td>${ vo.sajob }</td>
	           <td>${ fn:substring(vo.sahire,0,10) }</td>
	           <td>${ vo.samgr }</td>
	           <td><fmt:formatNumber type="currency" value="${ vo.sapay * 10000 }"/></td>
	        </tr>
        </c:forEach>
        
        
        
        
        
     </table>
  </div>
</body>
</html>




