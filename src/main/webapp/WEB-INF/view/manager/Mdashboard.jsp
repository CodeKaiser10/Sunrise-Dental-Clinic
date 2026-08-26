<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Manager Dashboard — Sunrise Dental Clinic</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/includes/header.jsp" />

<div class="dash-container">
    <h1 class="dash-heading">Manager Dashboard</h1>
    <p class="dash-welcome">~*~ Welcome, ${sessionScope.user.fullName} ~*~</p>

    <div class="dash-grid">
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/users">
            <div class="dash-ico">&#9733;</div>
            <h3>Manage Staff</h3>
            <p>Add, edit and remove user accounts</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/patients">
            <div class="dash-ico">&#9733;</div>
            <h3>Search Patients</h3>
            <p>Look up patient information</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/dentists">
            <div class="dash-ico">&#9733;</div>
            <h3>Search Dentists</h3>
            <p>Look up dentist information</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/appointments">
            <div class="dash-ico">&#9733;</div>
            <h3>Appointments</h3>
            <p>Search and manage appointments</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/analytics">
            <div class="dash-ico">&#9733;</div>
            <h3>Analytics</h3>
            <p>View clinic statistics</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/manager/reports">
            <div class="dash-ico">&#9733;</div>
            <h3>Reports</h3>
            <p>Generate and export reports</p>
        </a>
    </div>
</div>

</body>
</html>
