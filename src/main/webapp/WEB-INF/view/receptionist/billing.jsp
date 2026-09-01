<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/includes/head.jsp" />
    <title>Billing</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<div class="page-wrap">
    <h1 class="page-heading">Billing</h1>

    <!-- Create a bill -->
    <div class="panel">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">create_bill.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/billing" method="post" class="grid-form">
                <div class="field"><label>Appointment ID</label><input type="number" name="appointmentId" required></div>
                <div class="field"><label>Consultation Fee</label><input type="number" step="0.01" name="consultationFee" value="0" required></div>
                <div class="field"><label>Treatment Fee</label><input type="number" step="0.01" name="treatmentFee" value="0" required></div>
                <div class="field"><label>Discount</label><input type="number" step="0.01" name="discount" value="0" required></div>
                <div class="field"><label>Bill Date</label><input type="date" name="billDate"></div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Calculate &amp; Save &raquo;</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Show the calculated bill -->
    <c:if test="${not empty bill}">
        <div class="panel">
            <div class="panel-bar">
                <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
                <span class="panel-bar-name">bill.exe</span>
            </div>
            <div class="panel-body">
                <table class="data-table">
                    <tr><th>Consultation Fee</th><td>Rs. ${bill.consultationFee}</td></tr>
                    <tr><th>Treatment Fee</th><td>Rs. ${bill.treatmentFee}</td></tr>
                    <tr><th>Discount</th><td>- Rs. ${bill.discount}</td></tr>
                    <tr><th>Total</th><td><strong>Rs. ${bill.totalAmount}</strong></td></tr>
                </table>
                <button onclick="window.print()" class="btn-pill" style="margin-top:14px;">Print Bill &raquo;</button>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
