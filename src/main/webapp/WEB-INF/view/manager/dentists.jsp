<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Search Dentist</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page wrap">
    <h1 class="page-heading">Dentist</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">search_dentists></span>
        </div>

        <div class="panel-body">
            <table class="data-table">
                <thead>
                <tr><th>Name</th><th>Username</th><th>Email</th><th>Phone</th></tr>
                </thead>
                <tbody>
                <c:forEach var="d" items="${dentists}">
                    <tr>
                        <td>${d.fullName}</td>
                        <td>${d.username}</td>
                        <td>${d.email}</td>
                        <td>${d.phone}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dentists}">
                    <tr><td colspan="4" class="empty-row">No dentists found.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
