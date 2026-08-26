<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Receptionist Dashboard — Sunrise Dental Clinic</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/includes/header.jsp" />

<div class="dash-container">
    <h1 class="dash-heading">Receptionist Dashboard</h1>
    <p class="dash-welcome">~*~ Welcome, ${sessionScope.user.fullName} ~*~</p>

    <div class="dash-grid">
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/appointments">
            <div class="dash-ico">&#9733;</div>
            <h3>Appointments</h3>
            <p>Register, search and manage appointments</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/patients">
            <div class="dash-ico">&#9733;</div>
            <h3>Patients</h3>
            <p>Search and manage patient records</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/dentists">
            <div class="dash-ico">&#9733;</div>
            <h3>Search Dentists</h3>
            <p>Look up dentist information</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/billing">
            <div class="dash-ico">&#9733;</div>
            <h3>Billing</h3>
            <p>Calculate and print bills</p>
        </a>
    </div>
</div>

</body>
</html>
