<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
<title>Create/Edit view</title>
</head>
<body>
	<script language="javascript">
		function getAction(submit) {
			if (submit) {
				document.messageForm.action = "${action}";
			} else {
				document.messageForm.action = "/message";
			}
		}
	</script>

	<c:if test="${action} == 'update'">

	</c:if>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<form:form align="center" name="messageForm" method="post" commandName="message" role="form">
					<div class="form-group text-center">
						<h2>Create/Edit view</h2>
					</div>
					<div class="form-group">
						<div>User name</div>
						<form:input class="form-control" type="text" path="userName" />
						<div class="has-error">
							<form:errors path="userName" class="help-inline" />
						</div>
					</div>
					<div class="form-group">
						Message text <br>
						<form:textarea class="form-control" rows="10" cols="20" path="message"></form:textarea>
						<div class="has-error">
							<form:errors path="message" class="help-inline" />
						</div>
					</div>
					<div class="form-group">
						<form:input type="hidden" path="messageId" value="${messageId}" />
						<div class="text-right">
							<button class="btn btn-secondary" type="submit" onclick="getAction(true)">Save</button>
							<button class="btn btn-secondary" type="reset" onclick="getAction(false)">Cancel</button>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>