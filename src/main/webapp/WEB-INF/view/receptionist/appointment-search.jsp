<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Search Appointment</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Search Appointment</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">search.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="get" class="inline-form">
                <input type="hidden" name="action" value="search">
                <div class="field">
                    <label>Appointment Number</label>
                    <input type="text" name="appointmentNumber" placeholder="APP-001" value="${param.appointmentNumber}">
                </div>
                <button type="submit" class="btn-pill">Search &raquo;</button>
            </form>

            <c:if test="${not empty searchResult}">
                <table class="data-table" style="margin-top:16px;">
                    <tr><th>Number</th><td>${searchResult.appointmentNumber}</td></tr>
                    <tr><th>Patient</th><td>${searchResult.patientName}</td></tr>
                    <tr><th>Dentist</th><td>${searchResult.dentistName}</td></tr>
                    <tr><th>Treatment</th><td>${searchResult.treatmentType}</td></tr>
                    <tr><th>Date &amp; Time</th><td>${searchResult.appointmentDateTime}</td></tr>
                    <tr><th>Status</th><td>${searchResult.appointmentStatus}</td></tr>
                </table>
            </c:if>
            <c:if test="${not empty param.appointmentNumber and empty searchResult}">
                <p class="empty-row" style="margin-top:14px;">No appointment found with number "${param.appointmentNumber}".</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
