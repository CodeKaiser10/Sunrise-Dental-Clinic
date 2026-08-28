package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.BillDAO;
import com.sunrisedc.sunrisedentalclinic.model.Bill;

public class BillService {

    private static BillService instance;
    private BillDAO billDAO;

    private BillService() {
        this.billDAO = new BillDAO();
    }

    public BillService(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    public static BillService getInstance() {
        if (instance == null) {
            synchronized (BillService.class) {
                if (instance == null) {
                    instance = new BillService();
                }
            }
        }
        return instance;
    }

    public double calculateTotal(double consultationFee, double treatmentFee, double discount) {
        return consultationFee + treatmentFee - discount;
    }

    public void createBill(Bill bill) {
        double total = calculateTotal(bill.getConsultationFee(), bill.getTreatmentFee(), bill.getDiscount());
        bill.setTotalAmount(total);
        billDAO.insert(bill);
    }

    public Bill getBillForAppointment(int appointmentId) {
        return billDAO.findByAppointment(appointmentId);
    }
}
