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
    <div class="page-head-row">
        <h1 class="page-heading">Appointments</h1>
        <a href="${pageContext.request.contextPath}/receptionist/appointments?action=new" class="btn-pill">+ Book Appointment</a>
    </div>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">search.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="get" class="inline-form">
                <div class="field">
                    <label>Appointment Number</label>
                    <input type="text" name="appointmentNumber" placeholder="AP-..." value="${param.appointmentNumber}">
                </div>
                <button type="submit" class="btn-pill">Search &raquo;</button>
            </form>
            <c:if test="${not empty searchResult}">
                <div class="search-hit">
                    Found: <strong>${searchResult.appointmentNumber}</strong> —
                        ${searchResult.patientName} with ${searchResult.dentistName}
                    on ${searchResult.appointmentDateTime} (${searchResult.appointmentStatus})
                </div>
            </c:if>
        </div>
    </div>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">appointment_list.exe</span>
        </div>
        <div class="panel-body">
            <table class="data-table">
                <thead>
                <tr><th>Number</th><th>Patient</th><th>Dentist</th><th>Date &amp; Time</th><th>Status</th><th>Actions</th></tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${appointments}">
                    <tr>
                        <td>${a.appointmentNumber}</td>
                        <td>${a.patientName}</td>
                        <td>${a.dentistName}</td>
                        <td>${a.appointmentDateTime}</td>
                        <td>${a.appointmentStatus}</td>
                        <td class="actions">
                            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="status">
                                <input type="hidden" name="id" value="${a.appointmentId}">
                                <input type="hidden" name="status" value="COMPLETED">
                                <button type="submit" class="btn-mini btn-edit">Complete</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="post" onsubmit="return confirm('Cancel this appointment?');" style="display:inline;">
                                <input type="hidden" name="action" value="cancel">
                                <input type="hidden" name="id" value="${a.appointmentId}">
                                <button type="submit" class="btn-mini btn-del">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty appointments}">
                    <tr><td colspan="6" class="empty-row">No appointments yet.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>