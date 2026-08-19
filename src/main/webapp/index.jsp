<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Sunrise Dental Clinic</title>
</head>
<body>

<div class="y2k-screen">
    <div class="y2k-window">

        <!-- title bar -->
        <div class="y2k-titlebar">
            <span class="dot dot-pink"></span>
            <span class="dot dot-yellow"></span>
            <span class="dot dot-green"></span>
            <span class="y2k-titlebar-name">sunrise_dental.exe</span>
            <span class="y2k-titlebar-controls">_ &#9633; &#10005;</span>
        </div>

        <!-- window body -->
        <div class="y2k-window-body">
            <h1 class="y2k-title">Sunrise Dental Clinic</h1>
            <p class="y2k-sub">~*~ Management System ~*~</p>
            <a class="y2k-btn" href="${pageContext.request.contextPath}/login">Go to Login &raquo;</a>
            <div class="y2k-footer">best viewed in 800&times;600 &#9733; since 2001</div>
        </div>

    </div>
</div>

</body>
</html>
