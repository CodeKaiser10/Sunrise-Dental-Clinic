<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Appointments</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-wrap">Appointments</h1>

    <div class="dash-grid">
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/appointments?action=new">
            <div class="dash-ico">&#9733;</div>
            <h3>Register Appointment</h3>
            <p>Book a new appointment and patient</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/appointments?action=search">
            <div class="dash-ico">&#9733;</div>
            <h3>Search Appointment</h3>
            <p>Find appointment by its number</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/receptionist/appointments?action=manage">
            <div class="dash-ico">&#9733;</div>
            <h3>Manage Appointments</h3>
            <p>View, complete or cancel appointments</p>
        </a>
    </div>
</div>
</body>
</html>