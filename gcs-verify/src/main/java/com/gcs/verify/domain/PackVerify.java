package com.gcs.verify.domain;

import com.gcs.common.annotation.Excel;

import java.util.List;

public class PackVerify {

    @Excel(name = "申请编号")
    private Long tableId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long studentId;

    private Long deptId;

    private Long packId;

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

    private List<Gift> gifts;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
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

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    @Override
    public String toString() {
        return "PackVerify{" +
                "tableId=" + tableId +
                ", studentId=" + studentId +
                ", deptId=" + deptId +
                ", packId=" + packId +
                ", isDeleted=" + isDeleted +
                ", nowStep=" + nowStep +
                ", applyState=" + applyState +
                ", studentName='" + studentName + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", verifyHistories=" + verifyHistories +
                ", gifts=" + gifts +
                '}';
    }
}
