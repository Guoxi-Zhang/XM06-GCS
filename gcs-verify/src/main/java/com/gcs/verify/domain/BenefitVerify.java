package com.gcs.verify.domain;

import com.gcs.common.annotation.Excel;

import java.util.List;

public class BenefitVerify {

    @Excel(name = "申请编号")
    private Long tableId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 生活补助 */
    @Excel(name = "生活补助")
    private Long liveBenefit;

    /** 路费补助 */
    @Excel(name = "路费补助")
    private Long travelBenefit;

    /** 逻辑删除 */
    private Integer isDeleted;

    // 审核单位
    private Integer nowStep;

    // 审核状态
    private Integer applyState;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "学院")
    private String school;

    @Excel(name = "年级")
    private String grade;

    private List<VerifyHistory> verifyHistories;





    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getLiveBenefit() {
        return liveBenefit;
    }

    public void setLiveBenefit(Long liveBenefit) {
        this.liveBenefit = liveBenefit;
    }

    public Long getTravelBenefit() {
        return travelBenefit;
    }

    public void setTravelBenefit(Long travelBenefit) {
        this.travelBenefit = travelBenefit;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getNowStep() {
        return nowStep;
    }

    public void setNowStep(Integer nowStep) {
        this.nowStep = nowStep;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
        return "BenefitVerify{" +
                "tableId=" + tableId +
                ", studentId='" + studentId + '\'' +
                ", liveBenefit=" + liveBenefit +
                ", travelBenefit=" + travelBenefit +
                ", isDeleted=" + isDeleted +
                ", nowStep=" + nowStep +
                ", applyState=" + applyState +
                ", studentName='" + studentName + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", verifyHistories=" + verifyHistories +
                '}';
    }
}
