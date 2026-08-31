<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Manage Staff</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Manage Staff</h1>

    <!-- add staff form -->
    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name"add_staff.exe></span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/manager/users" method="post" class="grid-form">
                <div class="field"><label>Full Name</label><input type="text" name="fullName" required></div>
                <div class="field"><label>Username</label><input type="text" name="username" required></div>
                <div class="field"><label>Password</label><input type="text" name="password" required></div>
                <div class="field"><label>Email</label><input type="email" name="email"></div>
                <div class="field"><label>Phone</label><input type="text" name="phone"></div>
                <div class="field">
                    <label>Role</label>
                    <select name="role" required>
                        <option value="RECEPTIONIST">Receptionist</option>
                        <option value="MANAGER">Manager</option>
                        <option value="DENTIST">Dentist</option>
                    </select>
                </div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill"Add User &raquo;></button>
                </div>
            </form>
        </div>
    </div>

    <!--Staff list table-->
    <div class="panel">
        <div class="panel-bar">
            <thead>
                <tr><th>Name</th><th>Username</th><th>Role</th><th>Email</th><th>Phone</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.fullName}</td>
                        <td>${u.username}</td>
                        <td>${u.role}</td>
                        <td>${u.email}</td>
                        <td>${u.phone}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/manager/users?action=edit&username" class="btn-mini btn-edit">Edit</a>
                            <form action="${pageContext.request.contextPath}/manager/users" method="post" onsubmit="return confirm('Delete ${u.username}?');" style="display:inline ">
                                <input type="hidden" name="action" value="DELETE">
                                <input type="hidden" name="username" value="${u.username}">
                                <button type="submit" class="btn-mini mini-del">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty users}">
                    <tr><td colspan="6" class="empty-row">No staff yet</td></tr>
                </c:if>
            </tbody>
        </div>
    </div>
</div>
</body>
</html>
