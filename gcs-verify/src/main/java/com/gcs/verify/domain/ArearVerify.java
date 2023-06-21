package com.gcs.verify.domain;

import com.gcs.common.annotation.Excel;

import java.util.List;

public class ArearVerify {

    private Long tableId;

    private Long studentId;

    private Long batchId;

    private Long arearId;

    private Double arearAmount;

    private String arearReason;

    private Long nowStep;

    private Long applyState;


    private String studentName;

    private String arearName;

    private Double arearCost;


    private String school;

    private String grade;

    private List<VerifyHistory> verifyHistories;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getArearId() {
        return arearId;
    }

    public void setArearId(Long arearId) {
        this.arearId = arearId;
    }

    public Double getArearAmount() {
        return arearAmount;
    }

    public void setArearAmount(Double arearAmount) {
        this.arearAmount = arearAmount;
    }

    public String getArearReason() {
        return arearReason;
    }

    public void setArearReason(String arearReason) {
        this.arearReason = arearReason;
    }

    public Long getNowStep() {
        return nowStep;
    }

    public void setNowStep(Long nowStep) {
        this.nowStep = nowStep;
    }

    public Long getApplyState() {
        return applyState;
    }

    public void setApplyState(Long applyState) {
        this.applyState = applyState;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getArearName() {
        return arearName;
    }

    public void setArearName(String arearName) {
        this.arearName = arearName;
    }

    public Double getArearCost() {
        return arearCost;
    }

    public void setArearCost(Double arearCost) {
        this.arearCost = arearCost;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<VerifyHistory> getVerifyHistories() {
        return verifyHistories;
    }

    public void setVerifyHistories(List<VerifyHistory> verifyHistories) {
        this.verifyHistories = verifyHistories;
    }

    @Override
    public String toString() {
        return "ArearVerify{" +
                "tableId=" + tableId +
                ", studentId=" + studentId +
                ", batchId=" + batchId +
                ", arearId=" + arearId +
                ", arearAmount=" + arearAmount +
                ", arearReason='" + arearReason + '\'' +
                ", nowStep=" + nowStep +
                ", applyState=" + applyState +
                ", studentName='" + studentName + '\'' +
                ", arearName='" + arearName + '\'' +
                ", arearCost=" + arearCost +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", verifyHistories=" + verifyHistories +
                '}';
    }
}
