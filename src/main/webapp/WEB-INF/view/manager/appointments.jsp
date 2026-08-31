<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<!-- search by appointment number -->
<div class="page-wrap">
    <h1 class="page-heading">Appointments</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">search_patients></span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/manager/appointments" method="post" class="inline-form">
                <div class="field">
                    <label>Appointment Number</label>
                    <input type="text" name="appointmentNumber" placeholder="APT-.." value="${param.appointmentNumber}">
                </div>
                <button type="submit" class="btn-pill">Search &raquo;</button>
            </form>

            <c:if test="${not empty searchResult}">
                <div class="search-hit">
                    Found: <strong>${searchResult.appointmentNumber}</strong> -
                        ${searchResult.patientName} with ${searchResult.dentistName}
                    on ${searchResult.appointmentDatetime} (${searchResult.appointmentStatus})
                </div>
            </c:if>
        </div>

        <!-- All appointments -->
        <div class="panel">
            <div class="panel-bar">
                <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
                <span class="panel-bar-name">search_patients></span>
            </div>

            <div class="panel-body">
                <table class="data-table">
                    <thead>
                    <tr><th>Number</th><th>Patient</th><th>Dentist</th><th>Date &amp; Time</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${appointments}">
                        <tr>
                            <td>${a.appointmentNumber}</td>
                            <td>${a.patientName}</td>
                            <td>${a.dentistName}</td>
                            <td>${a.appointmentDateTime}</td>
                            <td>${a.appointmentstatus}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty appointments}">
                        <tr><td colspan="5" class="empty-row">No appointments found.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
