<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- List 제목 -->
	<h1>${board}</h1>
	
	<table>
	<tr>
		<td>NUM</td><td>TITLE</td><td>WRITE</td><td>DATE</td><td>HIT</td>
	</tr>
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.num}</td>
		<td>
		<c:catch>
		<c:forEach begin="1" end="${dto.depth}">--</c:forEach>
		</c:catch>
		<a href="noticeView?num=${dto.num}">${dto.title}</a>	
		</td>		
		<td>${dto.writer}</td>
		<td>${dto.reg_date}</td>
		<td>${dto.hit}</td>
	</tr>
	</c:forEach>
</table>

	<a href="${board}Write">WRITE</a>

</body>
</html>