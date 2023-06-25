package com.gcs.apply.mapper;

import java.util.List;
import com.gcs.apply.domain.BenefitApply;

/**
 * 补助申请Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public interface BenefitApplyMapper 
{
    /**
     * 查询补助申请
     * 
     * @param tableId 补助申请主键
     * @return 补助申请
     */
    public BenefitApply selectBenefitApplyByTableId(Long tableId);

    /**
     * 查询补助申请列表
     * 
     * @param benefitApply 补助申请
     * @return 补助申请集合
     */
    public List<BenefitApply> selectBenefitApplyList(BenefitApply benefitApply);
    public List<BenefitApply> selectBenefitApplyWithRepeat(BenefitApply benefitApply);
    public List<Long> selectBatchBasedOnCurrentTimestamp();

    /**
     * 新增补助申请
     * 
     * @param benefitApply 补助申请
     * @return 结果
     */
    public int insertBenefitApply(BenefitApply benefitApply);

    /**
     * 修改补助申请
     * 
     * @param benefitApply 补助申请
     * @return 结果
     */
    public int updateBenefitApply(BenefitApply benefitApply);

    /**
     * 删除补助申请
     * 
     * @param tableId 补助申请主键
     * @return 结果
     */
    public int deleteBenefitApplyByTableId(Long tableId);

    /**
     * 批量删除补助申请
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBenefitApplyByTableIds(Long[] tableIds);
}
