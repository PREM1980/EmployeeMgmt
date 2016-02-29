<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="js.jsp" />
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

<html>
	
    <head>
        <title>List of All Employees</title>
    </head>
    
    <body>
    	<jsp:include page="header.jsp" />
    	<jsp:include page="leftnav.jsp" />
    	<div id="content">
    	<h3> Add New Employee</h3>
        <form:form method="post" action="addemployee" commandName="employee">
        <table class="TFtable">
        <tr>
            <td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
            <td><form:input path="firstname" /></td>
        </tr>
        <tr>
            <td><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
            <td><form:input path="lastname" /></td>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
            <td><form:input path="telephone" /></td>
        </tr>
        <tr>
            <td><form:label path="dob"><spring:message code="label.dob"/></form:label></td>
            <td><form:input path="dob" id="datepicker"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="label.add"/>"/>
            </td>
        </tr>
        <form:input style="display:none" path="id"/>
    	</table>
    	</form:form>
    	</div>

    </body>
</html>