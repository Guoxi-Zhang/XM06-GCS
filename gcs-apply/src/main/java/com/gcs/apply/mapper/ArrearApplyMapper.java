package com.gcs.apply.mapper;

import java.util.List;
import com.gcs.apply.domain.ArrearApply;

/**
 * 欠缴费申请Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public interface ArrearApplyMapper 
{
    /**
     * 查询欠缴费申请
     * 
     * @param tableId 欠缴费申请主键
     * @return 欠缴费申请
     */
    public ArrearApply selectArrearApplyByTableId(Long tableId);

    /**
     * 查询欠缴费申请列表
     * 
     * @param arrearApply 欠缴费申请
     * @return 欠缴费申请集合
     */
    public List<ArrearApply> selectArrearApplyList(ArrearApply arrearApply);

    /**
     * 新增欠缴费申请
     * 
     * @param arrearApply 欠缴费申请
     * @return 结果
     */
    public int insertArrearApply(ArrearApply arrearApply);

    /**
     * 修改欠缴费申请
     * 
     * @param arrearApply 欠缴费申请
     * @return 结果
     */
    public int updateArrearApply(ArrearApply arrearApply);

    /**
     * 删除欠缴费申请
     * 
     * @param tableId 欠缴费申请主键
     * @return 结果
     */
    public int deleteArrearApplyByTableId(Long tableId);

    /**
     * 批量删除欠缴费申请
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArrearApplyByTableIds(Long[] tableIds);
}
