<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>My Profile</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">My Profile</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">profile.exe</span>
        </div>
        <div class="panel-body">
            <table class="data-table">
                <tr><th>Full Name</th><td>${sessionScope.user.fullName}</td></tr>
                <tr><th>Username</th><td>${sessionScope.user.username}</td></tr>
                <tr><th>Role</th><td>${sessionScope.user.role}</td></tr>
                <tr><th>Email</th><td>${sessionScope.user.email}</td></tr>
                <tr><th>Phone</th><td>${sessionScope.user.phone}</td></tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
