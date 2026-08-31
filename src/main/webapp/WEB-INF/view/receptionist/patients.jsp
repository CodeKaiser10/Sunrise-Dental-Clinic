<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Patients</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <div class="page-head-row">
        <h1 class="page-heading">Patients</h1>
        <a href="${pageContext.request.contextPath}/receptionist/patients?action=new" class="btn-pill">+ New Patient</a>
    </div>

    <!-- Search box -->
    <div class="panel">
        <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
        <span class="panel-bar-name">search_patients></span>
    </div>
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/receptionist/patients" method="post" class="inline-form">
            <div class="field">
                <label>Patient Name</label>
                <input type="text" name="search" placeholder="Type a name.." value="${param.search}">
            </div>
            <button type="submit" class="btn-pill">Search &raquo;</button>
        </form>
    </div>

    <!-- Patient list -->
    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">patient_list.exe</span>
        </div>
        <div class="panel-body">
            <table class="data-table">
                <thead>
                <tr><th>Name</th><th>Address</th><th>Contact</th><th>Date of Birth</th><th>Gender</th><th>Actions</th></tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${patients}">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.address}</td>
                        <td>${p.contactNumber}</td>
                        <td>${p.dateOfBirth}</td>
                        <td>${p.gender}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/receptionist/patients?action=edit&id=${p.patientId}" class="btn-mini btn-edit">Edit</a>
                            <form action="${pageContext.request.contextPath}/receptionist/patients" method="post" onsubmit="return confirm('Delete ${p.name}?');" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${p.patientId}">
                                <button type="submit" class="btn-mini btn-del">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty patients}">
                    <tr><td colspan="6" class="empty-row">No patients found.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
