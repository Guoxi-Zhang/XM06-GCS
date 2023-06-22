package com.gcs.apply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gcs.common.annotation.Excel;
import com.gcs.common.core.domain.BaseEntity;

/**
 * 礼包物品选择对象 pack_gift
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public class PackGift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物品编号 */
    @Excel(name = "物品编号")
    private Long giftId;

    /** 礼包编号 */
    @Excel(name = "礼包编号")
    private Long packId;

    /** 物品数量 */
    @Excel(name = "物品数量")
    private Long giftNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGiftId(Long giftId) 
    {
        this.giftId = giftId;
    }

    public Long getGiftId() 
    {
        return giftId;
    }
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
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
            .append("id", getId())
            .append("giftId", getGiftId())
            .append("packId", getPackId())
            .append("giftNum", getGiftNum())
            .toString();
    }
}
