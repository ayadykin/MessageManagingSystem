<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"></link>
<title>Create/Edit view</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 border">
				<form:form align="center" name="messageForm" method="post" action="${action}" commandName="message" role="form">
					<div class="form-group text-center">
						<h2>Create/Edit View</h2>
					</div>
					<div class="form-inline">
						<label id="userNameLabel" class="col-md-2 control-label no-padding" for="userName">User name</label>
						<div class="col-md-10 no-padding">
							<form:input class="form-control black-border" type="text" path="userName" />
							<div class="has-error">
								<form:errors path="userName" class="alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">Message text</label>
						<form:textarea class="form-control black-border" rows="10" cols="20" path="message"></form:textarea>
						<div class="has-error">
							<form:errors path="message" class="alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<form:input type="hidden" path="messageId" value="${messageId}" />
						<div class="text-right">
							<button class="btn btn-secondary" type="submit">Save</button>
							<a href="/message" class="btn btn-secondary">Cancel</a>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>