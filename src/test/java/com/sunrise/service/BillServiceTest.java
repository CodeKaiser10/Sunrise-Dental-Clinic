package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.BillService;
import com.sunrisedc.sunrisedentalclinic.dao.BillDAO;
import com.sunrisedc.sunrisedentalclinic.model.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillServiceTest {

    @Mock
    private BillDAO billDAO;

    private BillService billService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        billService = new BillService(billDAO);
    }

    @Test
    void CalculateTotalBillWithDiscount() {
        double total = billService.calculateTotal(1000, 2000, 500);
        assertEquals(2500.0, total);
    }

    @Test
    void CalculateTotalBillWithoutDiscount() {
        double total = billService.calculateTotal(1000, 2000, 0);
        assertEquals(3000.0, total);
    }

    @Test
    void TotalAmount() {
        Bill bill = new Bill();
        bill.setConsultationFee(1000.00);
        bill.setTreatmentFee(2000.00);
        bill.setDiscount(200.00);

        billService.createBill(bill);

        //computed total and saved it
        assertEquals(2800.0, bill.getTotalAmount());
        verify(billDAO).insert(bill);
    }

}
