package com.gcs.pack.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 礼包信息对象 pack_info
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public class PackInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物品ID */
    @Excel(name = "物品ID")
    private Long giftId;

    /** 物品名 */
    @Excel(name = "物品名")
    private String giftName;

    /** 物品图片 */
    @Excel(name = "物品图片")
    private String giftPicture;

    /** 物品尺寸 */
    @Excel(name = "物品尺寸")
    private String giftSize;

    /** 物品介绍 */
    @Excel(name = "物品介绍")
    private String giftIntroduction;

    /** 物品库存量 */
    @Excel(name = "物品库存量")
    private Long giftInventory;

    /** 软删除 */
    private Integer isDeleted;

    /** 物品单价 */
    @Excel(name = "物品单价")
    private BigDecimal giftPrice;

    /** 物品适用性别 */
    @Excel(name = "物品适用性别")
    private String gender;

    /** 批次 */
    @Excel(name = "批次")
    private Long batchId;

    /** 部门 */
    @Excel(name = "部门")
    private Long deptId;

    public void setGiftId(Long giftId) 
    {
        this.giftId = giftId;
    }

    public Long getGiftId() 
    {
        return giftId;
    }
    public void setGiftName(String giftName) 
    {
        this.giftName = giftName;
    }

    public String getGiftName() 
    {
        return giftName;
    }
    public void setGiftPicture(String giftPicture) 
    {
        this.giftPicture = giftPicture;
    }

    public String getGiftPicture() 
    {
        return giftPicture;
    }
    public void setGiftSize(String giftSize) 
    {
        this.giftSize = giftSize;
    }

    public String getGiftSize() 
    {
        return giftSize;
    }
    public void setGiftIntroduction(String giftIntroduction) 
    {
        this.giftIntroduction = giftIntroduction;
    }

    public String getGiftIntroduction() 
    {
        return giftIntroduction;
    }
    public void setGiftInventory(Long giftInventory) 
    {
        this.giftInventory = giftInventory;
    }

    public Long getGiftInventory() 
    {
        return giftInventory;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setGiftPrice(BigDecimal giftPrice) 
    {
        this.giftPrice = giftPrice;
    }

    public BigDecimal getGiftPrice() 
    {
        return giftPrice;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("giftId", getGiftId())
            .append("giftName", getGiftName())
            .append("giftPicture", getGiftPicture())
            .append("giftSize", getGiftSize())
            .append("giftIntroduction", getGiftIntroduction())
            .append("giftInventory", getGiftInventory())
            .append("isDeleted", getIsDeleted())
            .append("giftPrice", getGiftPrice())
            .append("gender", getGender())
            .append("batchId", getBatchId())
            .append("deptId", getDeptId())
            .toString();
    }
}
