<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp"/>
    <title>Dentist Dashboard</title>
</head>
<body>
<div class="grain-canvas" style="justify-content: flex-start; padding-top: 6rem;">
    <jsp:include page="/WEB-INF/view/includes/header.jsp"/>
    <div class="dashboard-container">s
        <h1 class="login-title" style="text-align: left">Dentist Dashboard</h1>
        <p class="login-subtitle" style="text-align: left">Welcome, ${sessionScope.user.fullName}.</p>
        <div class="dash-grid">
            <a href="${pageContext.request.contextPath}/dentist/records" class="glass-card dash-tile">
                <h3>Patient medical records</h3><p>View patient medical records</p>
            </a>
            <a href="${pageContext.request.contextPath}/receptionist/prescriptions" class="glass-card dash-tile">
                <h3>Prescriptions</h3><p>Add and update prescriptions</p>
            </a>
        </div>
    </div>
</div>
</body>
</html>