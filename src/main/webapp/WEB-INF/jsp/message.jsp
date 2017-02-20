<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"></link>
<title>Main View</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 border">
				<div class="panel-heading text-center">
					<h2>Main View</h2>
				</div>
				<table class="table table-hover table-striped table-border">
					<thead class="thead-inverse">
						<tr>
							<th>Message id</th>
							<th>Message text</th>
							<th>User name</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${content}">
							<c:forEach var="messages" items="${user.value.messages}">
								<tr>
									<td><c:out value="${messages.id}" /></td>
									<td><c:out value="${messages.message}" /></td>
									<td><c:out value="${user.value.name}" /></td>
									<td class="text-center"><a class="btn btn-secondary black-border"
										href="<c:url value="/message/update?userName=${user.value.name}&messageId=${messages.id}&message=${messages.message}&impl=${impl}"/>">Edit</a>
										<a class="btn btn-secondary black-border"
										href="<c:url value="/message/delete?userName=${user.value.name}&messageId=${messages.id}&message=${messages.message}&impl=${impl}"/>">Delete</a>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<a href="<c:url value="/message?impl=DB"/>">DB implementation</a><br> <a href="<c:url value="/message?impl=Coll"/>">Collection
						implementation (default)</a>
				</div>
				<div class="well text-center">
					<a class="btn btn-secondary" href="<c:url value='/message/create?impl=${impl}' />"><em>Add new message</em></a>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</body>
</html>