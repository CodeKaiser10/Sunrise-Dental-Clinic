<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Edit Staff</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Edit Staff Member</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">edit_staff.exe></span>
        </div>

        <div class="panel-body">
            <!-- pre-filled with selected users current details -->
            <form action="${pageContext.request.contextPath}/manager/users" method="post" class="grid-form">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="username" value="${editUser.username}">

                <div class="field">
                    <label>Username</label>
                    <input type="text" name="username" value="${editUser.username}" required>
                </div>

                <div class="field">
                    <label>Full name</label>
                    <input type="text" name="fullName" value="${editUser.fullname}" required>
                </div>

                <div class="field">
                    <label>Email</label>
                    <input type="email" name="email" value="${editUser.email}" required>
                </div>

                <div class="field">
                    <label>Phone</label>
                    <input type="text" name="phone" value="${editUser.phone}" required>
                </div>

                <div class="field">
                    <label>Role</label>
                    <select name="role" required>
                        <option value="RECEPTIONIST" ${editUser.role == 'RECEPTIONIST' ? 'selected' : ''}>Receptionist</option>
                        <option value="MANAGER" ${editUser.role == 'MANAGER' ? 'selected' : ''}>Manager</option>
                        <option value="DENTIST" ${editUser.role == 'DENTIST' ? 'selected' : ''}>Dentist</option>
                    </select>
                </div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Save Changes &raquo;</button>
                    <a href="${pageContext.request.contextPath}/manager/users}" class="btn-mini btn-edit" style="margin-left: 10px;">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
