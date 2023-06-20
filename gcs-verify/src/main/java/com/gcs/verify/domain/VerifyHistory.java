package com.gcs.verify.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 审核历史对象 verify_history
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public class VerifyHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核历史ID */
    private Long verifyId;

    /** 审核的申请表ID */
    @Excel(name = "审核的申请表ID")
    private Long applyId;

    /** 申请类型 */
    @Excel(name = "申请类型")
    private Long applyType;

    /** 审核人员ID */
    @Excel(name = "审核人员ID")
    private Long verifyPersonId;

    /** 审核操作：0未操作，1不通过，2驳回，3通过 */
    @Excel(name = "审核操作：0未操作，1不通过，2驳回，3通过")
    private Long verifyAction;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String verifyAdvice;

    /** 是否删除 */
    private Integer isDeleted;

    @Excel(name = "审核人员姓名")
    private String verifyPersonName;

    public void setVerifyId(Long verifyId) 
    {
        this.verifyId = verifyId;
    }

    public Long getVerifyId() 
    {
        return verifyId;
    }
    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }
    public void setApplyType(Long applyType) 
    {
        this.applyType = applyType;
    }

    public Long getApplyType() 
    {
        return applyType;
    }
    public void setVerifyPersonId(Long verifyPersonId) 
    {
        this.verifyPersonId = verifyPersonId;
    }

    public Long getVerifyPersonId() 
    {
        return verifyPersonId;
    }
    public void setVerifyAction(Long verifyAction) 
    {
        this.verifyAction = verifyAction;
    }

    public Long getVerifyAction() 
    {
        return verifyAction;
    }
    public void setVerifyAdvice(String verifyAdvice) 
    {
        this.verifyAdvice = verifyAdvice;
    }

    public String getVerifyAdvice() 
    {
        return verifyAdvice;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    public String getVerifyPersonName() {
        return verifyPersonName;
    }

    public void setVerifyPersonName(String verifyPersonName) {
        this.verifyPersonName = verifyPersonName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verifyId", getVerifyId())
            .append("applyId", getApplyId())
            .append("applyType", getApplyType())
            .append("verifyPersonId", getVerifyPersonId())
            .append("verifyAction", getVerifyAction())
            .append("verifyAdvice", getVerifyAdvice())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
