<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Medical Records</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Patient Medical Records</h1>

    <!-- Look up a patient -->
    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">lookup.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/dentist/records" method="get" class="inline-form">
                <div class="field">
                    <label>Patient ID</label>
                    <input type="number" name="patientId" value="${param.patientId}" required>
                </div>
                <button type="submit" class="btn-pill">View Record &raquo;</button>
            </form>
        </div>
    </div>

    <!-- Notes form: shown whenever a patient has been looked up -->
    <c:if test="${not empty param.patientId}">
        <div class="panel">
            <div class="panel-bar">
                <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
                <span class="panel-bar-name">record.exe</span>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty medicalRecord}">
                        <p class="form-sub">Record created: ${medicalRecord.createdDate}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="form-sub">No record yet for this patient — add notes below to create one.</p>
                    </c:otherwise>
                </c:choose>

                <form action="${pageContext.request.contextPath}/dentist/records" method="post">
                    <input type="hidden" name="patientId" value="${param.patientId}">
                    <div class="field field-full">
                        <label>Medical Notes</label>
                        <textarea name="notes" rows="6" class="text-area">${medicalRecord.notes}</textarea>
                    </div>
                    <button type="submit" class="btn-pill">Save Notes &raquo;</button>
                </form>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>