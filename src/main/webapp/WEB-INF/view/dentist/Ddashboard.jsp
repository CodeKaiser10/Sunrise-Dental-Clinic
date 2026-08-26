<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Dentist Dashboard — Sunrise Dental Clinic</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/includes/header.jsp" />

<div class="dash-container">
    <h1 class="dash-heading">Dentist Dashboard</h1>
    <p class="dash-welcome">~*~ Welcome, ${sessionScope.user.fullName} ~*~</p>

    <div class="dash-grid">
        <a class="dash-tile" href="${pageContext.request.contextPath}/dentist/records">
            <div class="dash-ico">&#9733;</div>
            <h3>Patient Medical Records</h3>
            <p>View patient medical records</p>
        </a>
        <a class="dash-tile" href="${pageContext.request.contextPath}/dentist/prescriptions">
            <div class="dash-ico">&#9733;</div>
            <h3>Prescriptions</h3>
            <p>Add and update prescriptions</p>
        </a>
    </div>
</div>

</body>
</html>
