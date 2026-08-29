package com.sunrisedc.sunrisedentalclinic.model;

public class Report {

    private int reportId;
    private String type;
    private String generateDate;
    private int generatedBy;

    private String generateByName;

    public Report() {}

    public Report(int reportId, String type, String generateDate, int generatedBy) {
        this.reportId = reportId;
        this.type = type;
        this.generateDate = generateDate;
        this.generatedBy = generatedBy;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public int getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(int generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getGenerateByName() {
        return generateByName;
    }

    public void setGenerateByName(String generateByName) {
        this.generateByName = generateByName;
    }
}
