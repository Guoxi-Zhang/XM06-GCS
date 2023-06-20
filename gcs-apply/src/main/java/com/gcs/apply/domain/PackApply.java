package com.gcs.apply.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 大礼包申请对象 pack_apply
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public class PackApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请编号 */
    @Excel(name = "申请编号")
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

    /** 申请原因 */
    @Excel(name = "申请原因")
    private String applyReason;

    /** 申请批次 */
    @Excel(name = "申请批次")
    private Long batchId;

    /** 逻辑删除 */
    private Integer isDeleted;

    /** 审核单位 */
    @Excel(name = "审核单位")
    private Long nowStep;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private Long applyState;

    /** 物品选择列表信息 */
    private List<PackList> packListList;

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
    public void setApplyReason(String applyReason) 
    {
        this.applyReason = applyReason;
    }

    public String getApplyReason() 
    {
        return applyReason;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
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

    public List<PackList> getPackListList()
    {
        return packListList;
    }

    public void setPackListList(List<PackList> packListList)
    {
        this.packListList = packListList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tableId", getTableId())
            .append("studentId", getStudentId())
            .append("operatorId", getOperatorId())
            .append("operatorType", getOperatorType())
            .append("applyReason", getApplyReason())
            .append("batchId", getBatchId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .append("nowStep", getNowStep())
            .append("applyState", getApplyState())
            .append("packListList", getPackListList())
            .toString();
    }
}
