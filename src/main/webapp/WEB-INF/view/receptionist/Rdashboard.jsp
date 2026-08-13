<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp"/>
    <title>Receptionist Dashboard</title>
</head>
<body>
   <div class="grain-canvas" style="justify-content: flex-start; padding-top: 6rem;">
       <jsp:include page="/WEB-INF/view/includes/header.jsp"/>
       <div class="dashboard-container">s
           <h1 class="login-title" style="text-align: left">Receptionist Dashboard</h1>
           <p class="login-subtitle" style="text-align: left">Welcome, ${sessionScope.user.fullName}.</p>
           <div class="dash-grid">
               <a href="${pageContext.request.contextPath}/receptionist/appointments" class="glass-card dash-tile">
                   <h3>Appointment</h3><p>Register, search and manage appointments</p>
               </a>
               <a href="${pageContext.request.contextPath}/receptionist/patients" class="glass-card dash-tile">
                   <h3>Patients</h3><p>Search and manage patient records</p>
               </a>
               <a href="${pageContext.request.contextPath}/receptionist/dentists" class="glass-card dash-tile">
                   <h3>Search Dentists</h3><p>Look up dentist information</p>
               </a>
               <a href="${pageContext.request.contextPath}/receptionist/billing" class="glass-card dash-tile">
                   <h3>Billing</h3><p>Calculate and print bills</p>
               </a>
           </div>
       </div>
   </div>
</body>
</html>
