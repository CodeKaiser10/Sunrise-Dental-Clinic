<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Analytics</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Clinic Analytics</h1>

    <div class="stat-grid">
        <div class="stat-card">
            <div class="stat-num">${summary.totalPatients}</div>
            <div class="stat-label">Total Patients</div>
        </div>

        <div class="stat-card">
            <div class="stat-num">${summary.totalAppointments}</div>
            <div class="stat-label">Total Appointments</div>
        </div>

        <div class="stat-card">
            <div class="stat-num">${summary.scheduledAppointments}</div>
            <div class="stat-label">Scheduled</div>
        </div>

        <div class="stat-card">
            <div class="stat-num">${summary.completedAppointments}</div>
            <div class="stat-label">Completed</div>
        </div>

        <div class="stat-card">
            <div class="stat-num">${summary.cancelledAppointments}</div>
            <div class="stat-label">Cancelled</div>
        </div>

        <div class="stat-card">
            <div class="stat-num">${summary.totalRevenue}</div>
            <div class="stat-label">Total Revenue</div>
        </div>
    </div>
</div>
</body>
</html>
