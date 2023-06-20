package com.gcs.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 流程管理对象 process_management
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public class ProcessManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long id;

    /** 学生申请提交开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "学生申请提交开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 学生申请提交结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "学生申请提交结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 申请审核提交结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请审核提交结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 资金来源 */
    @Excel(name = "资金来源")
    private Long fundingSource;

    /** 批次 */
    @Excel(name = "批次")
    private Long batch;

    /** 流程类型 */
    @Excel(name = "流程类型")
    private Long type;

    /** 逻辑删除 */
    private Integer isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setFundingSource(Long fundingSource) 
    {
        this.fundingSource = fundingSource;
    }

    public Long getFundingSource() 
    {
        return fundingSource;
    }
    public void setBatch(Long batch) 
    {
        this.batch = batch;
    }

    public Long getBatch() 
    {
        return batch;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("submitTime", getSubmitTime())
            .append("fundingSource", getFundingSource())
            .append("batch", getBatch())
            .append("type", getType())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
