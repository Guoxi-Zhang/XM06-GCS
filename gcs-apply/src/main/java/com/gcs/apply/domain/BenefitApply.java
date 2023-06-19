package com.gcs.apply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 补助申请对象 benefit_apply
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public class BenefitApply extends BaseEntity
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

    /** 申请人单位 */
    @Excel(name = "申请人单位")
    private Integer operatorType;

    /** 生活补助 */
    @Excel(name = "生活补助")
    private Long liveBenefit;

    /** 路费补助 */
    @Excel(name = "路费补助")
    private Long travelBenefit;

    /** 逻辑删除 */
    private Integer isDeleted;

    /** 当前审核单位 */
    @Excel(name = "当前审核单位")
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
    public void setLiveBenefit(Long liveBenefit) 
    {
        this.liveBenefit = liveBenefit;
    }

    public Long getLiveBenefit() 
    {
        return liveBenefit;
    }
    public void setTravelBenefit(Long travelBenefit) 
    {
        this.travelBenefit = travelBenefit;
    }

    public Long getTravelBenefit() 
    {
        return travelBenefit;
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
            .append("liveBenefit", getLiveBenefit())
            .append("travelBenefit", getTravelBenefit())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .append("nowStep", getNowStep())
            .append("applyState", getApplyState())
            .toString();
    }
}
