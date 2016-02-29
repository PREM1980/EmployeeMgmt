<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored ="false" %>
<html>

<body>
	<jsp:include page="header.jsp" />
	<!-- <h4>Title : ${title}</h1>
	<h4>Message : ${message}</h1>
	 -->
	<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name == null}">
				<h2>
					<a href="login">User</a>
					<a href="login">Admin</a>
				</h2>
				</c:when>
	</c:choose>
	<sec:authorize access="hasRole('ROLE_USER')">
			
		<jsp:include page="leftnav.jsp" />
		
		<div id="content">
		
		</div>


	</sec:authorize>
</body>
</html>