<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">search</span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="post" class="grid-form">
                <div class="field"><label>Patient ID</label><input type="number" name="patientId" required></div>
                <div class="field"><label>Dentist ID</label><input type="number" name="dentistId" required></div>
                <div class="field field-full"><label>Date &amp; Time</label><input type="datetime-local" name="appointmentDateTime" required></div>
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
