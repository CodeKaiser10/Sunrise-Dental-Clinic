package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.ReportDAO;
import com.sunrisedc.sunrisedentalclinic.model.Report;
import java.util.List;

public class ReportService {

    private static ReportService instance;
    private ReportDAO reportDAO;

    private ReportService() {
        this.reportDAO = new ReportDAO();
    }

    public ReportService(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (ReportService.class) {
                if (instance == null) {
                    instance = new ReportService();
                }
            }
        }
        return instance;
    }

    public void generateReport(String type, int generatedBy) { }
    public List<Report> getAllReports() { return null; }
}
