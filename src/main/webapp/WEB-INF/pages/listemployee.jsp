<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <jsp:include page="csrf.jsp" />
<head>
</head>

<style type="text/css">
	.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
	.TFtable td{ 
		padding:7px; border:#4e95f4 1px solid;
	}
	/* provide some minimal visual accomodation for IE8 and below */
	.TFtable tr{
		background: #b8d1f3;
	}
	/*  Define the background color for all the ODD background rows  */
	.TFtable tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	/*  Define the background color for all the EVEN background rows  */
	.TFtable tr:nth-child(even){
		background: #dae5f4;
	}
</style>


<script>
 </script>
    <head>
        <title>List of All Employees</title>
    </head>
    <body>
    <jsp:include page="header.jsp" />
    <jsp:include page="leftnav.jsp" />
    <div id="content">
    <h2>List of all Employees</h2>

    <h3>Employees</h3>
    <c:if  test="${!empty employeeList}">
    <table class="TFtable">
    <tr>
    	<th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Telephone</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${employeeList}" var="emp">
        <tr>
        	<td class="empid">${emp.id}</td>
            <td class="empfname">${emp.lastname}, ${emp.firstname} </td>
            <td class="empemail">${emp.email}</td>
            <td class="emptel">${emp.telephone}</td>
            <td><button class="del">Delete</button></td>
            <td><button class="upd">Update</button></td>
        </tr>
    </c:forEach>
    </table>
    </c:if>
    </div>
    </body>
</html>