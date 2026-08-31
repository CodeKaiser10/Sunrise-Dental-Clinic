<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>New Patient</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Register New Patients</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">new_patient</span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/patients" method="post" class="grid-form">
                <div class="field"><label>Name</label><input type="text" name="name" required></div>
                <div class="field"><label>Contact Number</label><input type="text" name="contactNumber"></div>
                <div class="field"><label>Date of Birth</label><input type="text" name="dateOfBirth"></div>
                <div class="field">
                    <label>Gender</label>
                    <select name="gender">
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                    </select>
                </div>
                <div class="field field-full"><label>Address</label><input type="text" name="address"></div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Register &raquo;</button>
                    <a href="${pageContext.request.contextPath}/receptionist/patients" class="btn-mini btn-edit" style="margin-left: 10px;"></a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
