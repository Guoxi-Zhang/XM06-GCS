package com.gcs.apply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 爱心大礼包申请对象 pack_apply
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public class PackApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请编号 */
    private Long tableId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long studentId;

    /** 申请人学号/工号 */
    @Excel(name = "申请人学号/工号")
    private Long operatorId;

    /** 申请单位 */
    @Excel(name = "申请单位")
    private Integer operatorType;

    /** 申请礼包 */
    @Excel(name = "申请礼包")
    private Long packId;

    /** 申请原因 */
    @Excel(name = "申请原因")
    private String applyReason;

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
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
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
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
    }
    public void setApplyReason(String applyReason) 
    {
        this.applyReason = applyReason;
    }

    public String getApplyReason() 
    {
        return applyReason;
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
            .append("packId", getPackId())
            .append("applyReason", getApplyReason())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .append("nowStep", getNowStep())
            .append("applyState", getApplyState())
            .toString();
    }
}
