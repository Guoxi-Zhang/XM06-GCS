package com.gcs.apply.service;

import java.util.List;

import com.gcs.apply.domain.BenefitApply;
import com.gcs.apply.domain.PackApply;

/**
 * 爱心大礼包申请Service接口
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public interface IPackApplyService 
{
    /**
     * 查询爱心大礼包申请
     * 
     * @param tableId 爱心大礼包申请主键
     * @return 爱心大礼包申请
     */
    public PackApply selectPackApplyByTableId(Long tableId);

    /**
     * 查询爱心大礼包申请列表
     * 
     * @param packApply 爱心大礼包申请
     * @return 爱心大礼包申请集合
     */
    public List<PackApply> selectPackApplyList(PackApply packApply);
    public List<PackApply> selectPackApplyWithRepeat(PackApply packApply);

    /**
     * 新增爱心大礼包申请
     * 
     * @param packApply 爱心大礼包申请
     * @return 结果
     */
    public int insertPackApply(PackApply packApply);

    /**
     * 修改爱心大礼包申请
     * 
     * @param packApply 爱心大礼包申请
     * @return 结果
     */
    public int updatePackApply(PackApply packApply);

    /**
     * 批量删除爱心大礼包申请
     * 
     * @param tableIds 需要删除的爱心大礼包申请主键集合
     * @return 结果
     */
    public int deletePackApplyByTableIds(Long[] tableIds);

    /**
     * 删除爱心大礼包申请信息
     * 
     * @param tableId 爱心大礼包申请主键
     * @return 结果
     */
    public int deletePackApplyByTableId(Long tableId);
}
