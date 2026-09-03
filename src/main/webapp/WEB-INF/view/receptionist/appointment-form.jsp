<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Book Appointment</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Register Appointment</h1>

    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">book_appointment.exe</span>
        </div>
        <div class="panel-body">
            <p class="form-sub">Enter the patient and appointment details. If the contact number already exists, the existing patient is used.</p>

            <form action="${pageContext.request.contextPath}/receptionist/appointments" method="post">

                <!-- Patient details -->
                <div class="form-grid">
                    <div class="form-field">
                        <label>Patient Name</label>
                        <input type="text" name="patientName" required>
                    </div>
                    <div class="form-field">
                        <label>Contact Number</label>
                        <input type="text" name="contactNumber" required>
                    </div>
                    <div class="form-field">
                        <label>Date Of Birth</label>
                        <input type="date" name="dateOfBirth" required>
                    </div>
                    <div class="form-field">
                        <label>Gender</label>
                        <select name="gender">
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </div>
                    <div class="form-field form-field-full">
                        <label>Address</label>
                        <input type="text" name="address">
                    </div>
                </div>

                <!-- Appointment details -->
                <div class="form-grid">
                    <div class="form-field">
                        <label>Dentist</label>
                        <select name="dentistId" required>
                            <option value="">-- Select dentist --</option>
                            <c:forEach var="d" items="${dentists}">
                                <option value="${d.id}">${d.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-field">
                        <label>Treatment Type</label>
                        <input type="text" name="treatmentType" placeholder="e.g. Cleaning, Filling">
                    </div>
                    <div class="form-field form-field-full">
                        <label>Appointment Date &amp; Time</label>
                        <input type="datetime-local" name="appointmentDateTime" required>
                    </div>
                </div>

                <button type="submit" class="btn-pill btn-wide">Book Appointment &raquo;</button>
                <a href="${pageContext.request.contextPath}/receptionist/appointments" class="btn-mini btn-edit" style="margin-left:10px;">Cancel</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
