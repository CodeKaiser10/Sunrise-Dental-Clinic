<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Sunrise Dental Clinic</title>
</head>
<body>

<!-- grain background wrapper -->
<div class="grain-canvas">

    <div class="glass-card">
        <h1 class="login-title">Sunrise Dental Clinic</h1>
        <p class="login-subtitle">Login to Continue</p>

        <!-- error message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert mb-4" style="background-color: rgba(244, 63, 94, 0.15); border: 1px solid var(--destructive); color: #fff;">
                ${errorMessage}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}//login" method="post">
            <div class="form-group">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="input-glass" placeholder="Enter your username" required>
            </div>

            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="input-glass" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="btn-primary">Log In</button>
        </form>
    </div>
</div>

</body>
</html>
