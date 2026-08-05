<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Welcome - Sunrise Dental Clinic</title>
</head>
<body>

<!-- Optional: Include Navbar if you want it on the welcome screen -->
<jsp:include page="/WEB-INF/view/includes/header.jsp" />

<!-- Grain background wrapper -->
<div class="grain-canvas">

    <!-- Glass card for the welcome message -->
    <div class="glass-card">
        <h1 class="login-title">Sunrise Dental Clinic</h1>
        <p class="login-subtitle">Management System</p>

        <a href="${pageContext.request.contextPath}/login" class="btn-primary mt-4">Go to Login</a>
    </div>

</div>

</body>
</html>