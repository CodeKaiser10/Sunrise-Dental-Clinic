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
    <div class="panel no-print">
        <div class="panel-bar">
            <span class="dot dot-pink"></span><span class="dot dot-yellow"></span><span class="dot dot-green"></span>
            <span class="panel-bar-name">create_bill.exe</span>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/receptionist/billing" method="post" class="form-grid" id="billForm" onsubmit="return validateBill()">
                <div class="field"><label>Appointment ID</label><input type="number" name="appointmentId" required></div>
                <div class="field"><label>Consultation Fee</label><input type="number" step="0.01" name="consultationFee" id="consult" value="0" oninput="updateTotal()" required></div>
                <div class="field"><label>Treatment Fee</label><input type="number" step="0.01" name="treatmentFee" id="treat" value="0" oninput="updateTotal()" required></div>
                <div class="field"><label>Discount</label><input type="number" step="0.01" name="discount" id="disc" value="0" oninput="updateTotal()" required></div>
                <div class="field"><label>Bill Date</label><input type="date" name="billDate"></div>

                <div class="form-field form-field-full">
                    <div class="live-total">Total: Rs. <span id="liveTotal">0.00</span></div>
                    <div class="err-msg" id="errMsg"></div>
                </div>
                <div class="field field-full">
                    <button type="submit" class="btn-pill">Calculate &amp; Save &raquo;</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Show the calculated bill -->
    <c:if test="${not empty bill}">
        <div class="receipt" id="receipt">
            <h2>Sunrise Dental Clinic</h2>
            <div class="rc-sub">~*~ Receipt ~*~</div>

            <div class="rc-line"><span>Appointment ID</span><span>${bill.appointmentId}</span></div>
            <div class="rc-line"><span>Bill Date</span><span>${bill.billDate}</span></div>
            <div class="rc-line"><span>Consultation Fee</span><span>Rs. ${bill.consultationFee}</span></div>
            <div class="rc-line"><span>Treatment Fee</span><span>${bill.treatmentFee}</span></div>
            <div class="rc-line"><span>Discount</span><span>% ${bill.discount}</span></div>
            <div class="rc-line"><span>TOTAL</span><span>Rs. ${bill.totalAmount}</span></div>


            <div class="rc-foot">Thank you for visiting Sunrise Dental Clinic! Stay Safe!</div>
        </div>

        <div style="text-align:center; margin-top:18px;" class="no-print">
            <button onclick="window.print()" class="btn-pill">Print / Download Receipt &raquo;</button>
        </div>
    </c:if>
</div>

<script>
    //consultation, treatment, discount updated as the user types.
    function updateTotal() {
        var consult = parseFloat(document.getElementById('consult').value) || 0;
        var treat = parseFloat(document.getElementById('treat').value) || 0;
        var disc = parseFloat(document.getElementById('disc').value) || 0;
        var total = consult + treat + disc;
        document.getElementById('liveTotal').textContent = (consult + treat - disc).toFixed(2);
    }

    function validateBill() {
        var consult = parseFloat(document.getElementById('consult').value) || 0
        var treat = parseFloat(document.getElementById('treat').value) || 0;
        var disc = parseFloat(document.getElementById('disc').value) || 0;
        var err = document.getElementById('errMsg');

        if (consult < 0 || treat < 0 || disc < 0) {
            err.textContent = 'Fees and discount cannot be less than 0';
        }
        if (disc > consult + treat) {
            err.textContent = 'Discount cannot be greater than total fees';
        }
        err.textContent = '';
        return true;

        window.onload = function () {
            updateTotal();
        }
    }
</script>
</body>
</html>
