<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Book Appointment</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Book Appointment</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">book_appointment.exe</span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="post" class="grid-form">
                <div class="field">
                    <label>Patient</label>
                    <select name="patientId" required>
                        <option value="">-- Select patient --</option>
                        <c:forEach var="p" items="${patients}">
                            <option value="${p.patientId}">${p.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field">
                    <label>Dentist</label>
                    <select name="dentistId" required>
                        <option value="">-- Select dentist --</option>
                        <c:forEach var="d" items="${dentists}">
                            <option value="${d.id}">${d.fullName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field field-full">
                    <label>Date &amp; Time</label>
                    <input type="datetime-local" name="appointmentDateTime" required>
                </div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Book &raquo;</button>
                    <a href="${pageContext.request.contextPath}/receptionist/appointments" class="btn-mini btn-edit" style="margin-left:10px;">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
