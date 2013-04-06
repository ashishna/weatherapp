<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Expedia Weather Application</title>
	</head>

	<body>
		<form:form id="form" modelAttribute="inputForm" method="POST">
		<table>
		<tr>
			<td colspan=2">
				<font color="red">
					<form:errors path="zipCode" />
				</font>
			</td>
		</tr>
	      <tr>
	        <td>Enter a zip code: </td>
	        <td><form:input path="zipCode" maxlength="5"/></td>
	      </tr>
	      <c:choose>
	      <c:when test="${error eq null}">
	       <tr>
	        <td>Weather Details for entered zip code: </td>
	      </tr>
	      <tr>
	      	<td><b>Temp (F)</b></td><td><b>City</b></td><td><b>State</b></td>
	      </tr>
	      
	      <tr>
	      	<td>${temp}</td><td>${city}</td><td>${state}</td>
	      </tr>
	      </c:when>
	      <c:otherwise>
	      	<tr>
	      		<td>
	      			<font color="red">
	      				${error}
      				</font>
	      		</td>
      		</tr>
	      </c:otherwise>
	      </c:choose>
	      <tr>
	      	<td>
	      		<input type="submit"/>
	      	</td>
      	</tr>
	    </table>
    </form:form>
	</body>
</html>
