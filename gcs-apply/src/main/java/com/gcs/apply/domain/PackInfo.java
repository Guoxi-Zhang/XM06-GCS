package com.gcs.apply.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 礼包设置对象 pack_info
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public class PackInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 礼包编号 */
    @Excel(name = "礼包编号")
    private Long packId;

    /** 批次号 */
    @Excel(name = "批次号")
    private Long batchId;

    /** 学院年级 */
    @Excel(name = "学院年级")
    private Long deptId;

    /** 逻辑删除 */
    private Integer isDeleted;

    /** 礼包物品选择信息 */
    private List<PackGift> packGiftList;

    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
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
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    public List<PackGift> getPackGiftList()
    {
        return packGiftList;
    }

    public void setPackGiftList(List<PackGift> packGiftList)
    {
        this.packGiftList = packGiftList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("packId", getPackId())
            .append("batchId", getBatchId())
            .append("deptId", getDeptId())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("packGiftList", getPackGiftList())
            .toString();
    }
}
