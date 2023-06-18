package com.gcs.apply.service.impl;

import java.util.List;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.apply.mapper.BenefitApplyMapper;
import com.gcs.apply.domain.BenefitApply;
import com.gcs.apply.service.IBenefitApplyService;

/**
 * 补助申请Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-18
 */
@Service
public class BenefitApplyServiceImpl implements IBenefitApplyService 
{
    @Autowired
    private BenefitApplyMapper benefitApplyMapper;

    /**
     * 查询补助申请
     * 
     * @param tableId 补助申请主键
     * @return 补助申请
     */
    @Override
    public BenefitApply selectBenefitApplyByTableId(Long tableId)
    {
        return benefitApplyMapper.selectBenefitApplyByTableId(tableId);
    }

    /**
     * 查询补助申请列表
     * 
     * @param benefitApply 补助申请
     * @return 补助申请
     */
    @Override
    public List<BenefitApply> selectBenefitApplyList(BenefitApply benefitApply)
    {
        return benefitApplyMapper.selectBenefitApplyList(benefitApply);
    }

    /**
     * 新增补助申请
     * 
     * @param benefitApply 补助申请
     * @return 结果
     */
    @Override
    public int insertBenefitApply(BenefitApply benefitApply)
    {
        benefitApply.setCreateTime(DateUtils.getNowDate());
        return benefitApplyMapper.insertBenefitApply(benefitApply);
    }

    /**
     * 修改补助申请
     * 
     * @param benefitApply 补助申请
     * @return 结果
     */
    @Override
    public int updateBenefitApply(BenefitApply benefitApply)
    {
        benefitApply.setUpdateTime(DateUtils.getNowDate());
        return benefitApplyMapper.updateBenefitApply(benefitApply);
    }

    /**
     * 批量删除补助申请
     * 
     * @param tableIds 需要删除的补助申请主键
     * @return 结果
     */
    @Override
    public int deleteBenefitApplyByTableIds(Long[] tableIds)
    {
        return benefitApplyMapper.deleteBenefitApplyByTableIds(tableIds);
    }

    /**
     * 删除补助申请信息
     * 
     * @param tableId 补助申请主键
     * @return 结果
     */
    @Override
    public int deleteBenefitApplyByTableId(Long tableId)
    {
        return benefitApplyMapper.deleteBenefitApplyByTableId(tableId);
    }
}
