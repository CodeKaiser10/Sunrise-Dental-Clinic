<%-- Shared Y2K nav bar, shown on every logged-in page. --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="dash-navbar">
    <a href="${pageContext.request.contextPath}${sessionScope.user.dashboard}" class="dash-brand" style="text-decoration:none;">Sunrise Dental Clinic</a>
    <div class="dash-nav-links">
        <a href="${pageContext.request.contextPath}/help" class="dash-nav-link">Help</a>
        <a href="${pageContext.request.contextPath}/profile" class="dash-nav-link">Profile</a>
        <a href="${pageContext.request.contextPath}/logout" class="dash-nav-link">Logout</a>
    </div>
</nav>