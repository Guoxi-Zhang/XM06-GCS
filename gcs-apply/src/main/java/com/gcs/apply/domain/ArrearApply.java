package com.gcs.apply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 欠缴费申请对象 arrear_apply
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public class ArrearApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请编号 */
    @Excel(name = "申请编号")
    private Long tableId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 申请人学号/工号 */
    @Excel(name = "申请人学号/工号")
    private String operatorId;

    /** 申请单位 */
    @Excel(name = "申请单位")
    private Integer operatorType;

    /** 欠费批次 */
    @Excel(name = "欠费批次")
    private Long batchId;

    /** 欠费项目 */
    @Excel(name = "欠费项目")
    private Long arrearId;

    /** 缓缴金额 */
    @Excel(name = "缓缴金额")
    private Long arrearAmount;

    /** 欠费原因 */
    @Excel(name = "欠费原因")
    private String arrearReason;

    /** 证明材料 */
    private String arrearAttn;

    /** 逻辑删除 */
    private Integer isDeleted;

    /** 审核单位 */
    @Excel(name = "审核单位")
    private Long nowStep;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private Long applyState;

    public void setTableId(Long tableId) 
    {
        this.tableId = tableId;
    }

    public Long getTableId() 
    {
        return tableId;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setOperatorId(String operatorId) 
    {
        this.operatorId = operatorId;
    }

    public String getOperatorId() 
    {
        return operatorId;
    }
    public void setOperatorType(Integer operatorType) 
    {
        this.operatorType = operatorType;
    }

    public Integer getOperatorType() 
    {
        return operatorType;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setArrearId(Long arrearId) 
    {
        this.arrearId = arrearId;
    }

    public Long getArrearId() 
    {
        return arrearId;
    }
    public void setArrearAmount(Long arrearAmount) 
    {
        this.arrearAmount = arrearAmount;
    }

    public Long getArrearAmount() 
    {
        return arrearAmount;
    }
    public void setArrearReason(String arrearReason) 
    {
        this.arrearReason = arrearReason;
    }

    public String getArrearReason() 
    {
        return arrearReason;
    }
    public void setArrearAttn(String arrearAttn) 
    {
        this.arrearAttn = arrearAttn;
    }

    public String getArrearAttn() 
    {
        return arrearAttn;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setNowStep(Long nowStep) 
    {
        this.nowStep = nowStep;
    }

    public Long getNowStep() 
    {
        return nowStep;
    }
    public void setApplyState(Long applyState) 
    {
        this.applyState = applyState;
    }

    public Long getApplyState() 
    {
        return applyState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tableId", getTableId())
            .append("studentId", getStudentId())
            .append("operatorId", getOperatorId())
            .append("operatorType", getOperatorType())
            .append("batchId", getBatchId())
            .append("arrearId", getArrearId())
            .append("arrearAmount", getArrearAmount())
            .append("arrearReason", getArrearReason())
            .append("arrearAttn", getArrearAttn())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .append("nowStep", getNowStep())
            .append("applyState", getApplyState())
            .toString();
    }
}
