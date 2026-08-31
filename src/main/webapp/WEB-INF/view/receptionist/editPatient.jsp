<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Edit Patient</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Edit Patient</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-yellow"></span>
            <span class="panel-bar-name">edit_patient</span>
        </div>

        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/patients" method="post" class="grid-form">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${editPatient.patientId}">
                <div class="field"><label>Name</label><input type="text" name="name" value="${editPatient.name}" required></div>
                <div class="field"><label>Contact Number</label><input type="text" name="contactNumber" value="${editPatient.contactNumber}"></div>
                <div class="field"><label>Date of Birth</label><input type="text" name="dateOfBirth" value="${editPatient.dateOfBirth}"></div>
                <div class="field">
                    <label>Gender</label>
                    <select name="gender">
                        <option value="MALE" ${editPatient.gender == 'MALE' ? 'selected' : ''}>Male</option>
                        <option value="FEMALE" ${editPatient.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                    </select>
                </div>
                <div class="field field-full"><label>Address</label><input type="text" name="address" value="${editPatient.address}"></div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Save Changes &raquo;</button>
                    <a href="${pageContext.request.contextPath}/receptionist/patients" class="btn-mini btn-edit" style="margin-left: 10px;">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
