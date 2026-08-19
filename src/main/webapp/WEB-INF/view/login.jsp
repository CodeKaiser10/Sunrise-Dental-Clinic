<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Sunrise Dental Clinic — Login</title>
</head>
<body>

<div class="y2k-screen">
    <div class="y2k-window">

        <div class="y2k-titlebar">
            <span class="dot dot-pink"></span>
            <span class="dot dot-yellow"></span>
            <span class="dot dot-green"></span>
            <span class="y2k-titlebar-name">login.exe</span>
            <span class="y2k-titlebar-controls">_ &#9633; &#10005;</span>
        </div>

        <div class="y2k-window-body">
            <h1 class="y2k-title">Sunrise Dental Clinic</h1>
            <p class="y2k-sub">~*~ Login to Continue ~*~</p>

            <!-- Error message, shown only when the controller sets one. -->
            <c:if test="${not empty errorMessage}">
                <div class="y2k-alert">${errorMessage}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post" class="y2k-form">
                <div class="y2k-field">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required autocomplete="username">
                </div>
                <div class="y2k-field">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required autocomplete="current-password">
                </div>
                <button type="submit" class="y2k-btn y2k-btn-block">Log In &raquo;</button>
            </form>

            <div class="y2k-footer">&#9733; best viewed in 800&times;600 &#9733; since 2001</div>
        </div>

    </div>
</div>

</body>
</html>
