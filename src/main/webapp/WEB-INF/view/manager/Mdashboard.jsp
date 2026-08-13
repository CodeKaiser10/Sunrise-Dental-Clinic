<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Manager Dashboard</title>
</head>
<body>
    <div class="grain-canvas" style="justify-content: flex-start; padding-top: 6rem;" >
        <jsp:include page="/WEB-INF/view/includes/header.jsp" />
        <div class="dashboard-container">
            <h1 class="login-title" style="text-align: left">Manager Dashboard</h1>
            <p class="login-subtitle" style="text-align: left">Welcome, ${sessionScope.user.fullName}.</p>
            <div class="dash-grid">
                <a href="${pageContext.request.contextPath}/manager/users" class="glass-card dash-tile">
                    <h3>Manage Staff</h3><p>Add, edit and remove User Accounts</p>
                </a>
                <a href="${pageContext.request.contextPath}/manager/patients" class="glass-card dash-tile">
                    <h3>Search Patients</h3><p>Look up patient information</p>
                </a>
                <a href="${pageContext.request.contextPath}/manager/dentists" class="glass-card">
                    <h3>Search Patients</h3><p>Look up dentist information</p>
                </a>
                <a href="${pageContext.request.contextPath}/manager/appointments" class="glass-card">
                    <h3>Appointments</h3><p>Search and manage appointments</p>
                </a>
                <a href="${pageContext.request.contextPath}/manager/analytics" class="glass-card">
                    <h3>Analytics</h3><p>View clinic statistics</p>
                </a>
                <a href="${pageContext.request.contextPath}/manager/reports" class="glass-card">
                    <h3>Reports</h3><p>Generate and export reports</p>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
