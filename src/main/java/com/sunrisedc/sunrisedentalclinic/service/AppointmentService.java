package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.AppointmentDAO;
import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import java.util.List;

//Business layer for appointments using singleton
public class AppointmentService {

    private static AppointmentService instance;
    private AppointmentDAO appointmentDAO;

    private AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    public AppointmentService (AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public static AppointmentService getInstance() {
        if (instance == null) {
            synchronized (AppointmentService.class) {
                if (instance == null) {
                    instance = new AppointmentService();
                }
            }
        }
        return instance;
    }

    //books an appointment, generating a unique number for appointment and default status if not set
    public void bookAppointment(Appointment appointment) {
        if (appointment.getAppointmentNumber() == null || appointment.getAppointmentNumber().isEmpty()) {
            appointment.setAppointmentNumber(generateNumber());
        }
        if (appointment.getAppointmentStatus() == null || appointment.getAppointmentStatus().isEmpty()) {
            appointment.setAppointmentStatus("SCHEDULED");
        }
        appointmentDAO.insert(appointment);
    }

    public List<Appointment> getAllAppointments() {return appointmentDAO.findAll(); }
    public Appointment findByNumber(String appointmentNumber) {return appointmentDAO.findByNumber(appointmentNumber);}
    public void updateStatus(int appointmentId, String status) { appointmentDAO.updateStatus(appointmentId, status);}
    public void cancelAppointment(int appointmentId) { appointmentDAO.updateStatus(appointmentId, "CANCELLED");}

    //builds a reference
    private String generateNumber() {
        int next = appointmentDAO.countAll() + 1;
        return String.format("APP-%03d", next);
    }
}
