<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Search Patients</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrapp">
    <h1 class="page-heading">Patients</h1>

    <!-- Search box -->
    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">search_patients></span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/manager/reports" method="post" class="inline-form">
                <div class="field">
                    <label>Patient Name</label>
                    <input type="text" name="search" placeholder="Type a name.." value="${param.search}">
                </div>
                <button type="submit" class="btn-pill">Search &raquo;</button>
            </form>
        </div>

        <!-- Search result table -->
        <div class="panel">
            <div class="panel-bar">
                <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
                <span class="panel-bar-name">search_patients></span>
            </div>

            <div class="panel-body">
                <div class="data-table">
                    <thead>
                    <tr><th>Name</th><th>Address</th><th>Contact</th><th>Date of Birth</th><th>Gender</th></tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${patients}">
                        <tr>
                            <td>$(p.name)</td>
                            <td>$(p.address)</td>
                            <td>$(p.contactNumber)</td>
                            <td>$(p.dateOfBirth)</td>
                            <td>$(p.gender)</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty patients}">
                        <tr><td colspan="5" class="empty-row">No patients found</td></tr>
                    </c:if>
                    </tbody>
                </div>
            </div>
        </div>
</div>
</div>
</body>
</html>

