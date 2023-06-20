package com.gcs.apply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 物品选择列表对象 pack_list
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public class PackList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long chooseId;

    /** 申请物品 */
    @Excel(name = "申请物品")
    private Long giftId;

    /** 申请物品名 */
    @Excel(name = "申请物品")
    private String giftName;

    /** 申请表ID */
    private Long tableId;

    /** 物品数量 */
    @Excel(name = "物品数量")
    private Long giftNum;

    public void setChooseId(Long chooseId) 
    {
        this.chooseId = chooseId;
    }

    public Long getChooseId() 
    {
        return chooseId;
    }
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

    public void setTableId(Long tableId) 
    {
        this.tableId = tableId;
    }

    public Long getTableId() 
    {
        return tableId;
    }
    public void setGiftNum(Long giftNum) 
    {
        this.giftNum = giftNum;
    }

    public Long getGiftNum() 
    {
        return giftNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("chooseId", getChooseId())
            .append("giftId", getGiftId())
            .append("tableId", getTableId())
            .append("giftNum", getGiftNum())
            .toString();
    }
}
