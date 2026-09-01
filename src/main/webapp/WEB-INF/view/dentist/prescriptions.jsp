<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Prescriptions</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Prescriptions</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">lookup.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/dentist/prescriptions" method="get" class="inline-form">
                <div class="field">
                    <label>Patient ID</label>
                    <input type="number" name="patientId" value="${param.patientId}" required>
                </div>
                <button type="submit" class="btn-pill">View &raquo;</button>
            </form>
        </div>
    </div>

    <c:if test="${not empty param.patientId}">
        <div class="panel">
            <div class="panel-bar">
                <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
                <span class="panel-bar-name">new_prescription.exe</span>
            </div>
            <div class="panel-body">
                <form action="${pageContext.request.contextPath}/dentist/prescriptions" method="post" class="grid-form">
                    <input type="hidden" name="patientId" value="${param.patientId}">
                    <input type="hidden" name="dentistId" value="${sessionScope.user.id}">
                    <div class="field"><label>Date</label><input type="date" name="date" required></div>
                    <div class="field"><label>Medication</label><input type="text" name="medication" required></div>
                    <div class="field"><label>Dosage</label><input type="text" name="dosage"></div>
                    <div class="field"><label>Notes</label><input type="text" name="notes"></div>
                    <div class="field field-full">
                        <button type="submit" class="btn-pill">Add Prescription &raquo;</button>
                    </div>
                </form>
            </div>
        </div>
    </c:if>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">prescription_list.exe</span>
        </div>
        <div class="panel-body">
            <table class="data-table">
                <thead>
                <tr><th>Date</th><th>Medication</th><th>Dosage</th><th>Notes</th></tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${prescriptions}">
                    <tr>
                        <td>${p.date}</td>
                        <td>${p.medication}</td>
                        <td>${p.dosage}</td>
                        <td>${p.notes}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty prescriptions}">
                    <tr><td colspan="4" class="empty-row">No prescriptions for this patient.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
