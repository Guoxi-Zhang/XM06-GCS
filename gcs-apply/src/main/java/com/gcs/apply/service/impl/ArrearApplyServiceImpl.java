package com.gcs.apply.service.impl;

import java.util.List;

import com.gcs.common.annotation.DataScope;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.apply.mapper.ArrearApplyMapper;
import com.gcs.apply.domain.ArrearApply;
import com.gcs.apply.service.IArrearApplyService;

/**
 * 欠缴费申请Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
@Service
public class ArrearApplyServiceImpl implements IArrearApplyService 
{
    @Autowired
    private ArrearApplyMapper arrearApplyMapper;

    /**
     * 查询欠缴费申请
     * 
     * @param tableId 欠缴费申请主键
     * @return 欠缴费申请
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public ArrearApply selectArrearApplyByTableId(Long tableId)
    {
        return arrearApplyMapper.selectArrearApplyByTableId(tableId);
    }
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public ArrearApply selectArrearApplyWithInfoByTableId(Long tableId)
    {
        return arrearApplyMapper.selectArrearApplyWithInfoByTableId(tableId);
    }

    /**
     * 查询欠缴费申请列表
     * 
     * @param arrearApply 欠缴费申请
     * @return 欠缴费申请
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public List<ArrearApply> selectArrearApplyList(ArrearApply arrearApply)
    {
        return arrearApplyMapper.selectArrearApplyList(arrearApply);
    }
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public List<ArrearApply> selectArrearApplyListWithInfo(ArrearApply arrearApply)
    {
        return arrearApplyMapper.selectArrearApplyListWithInfo(arrearApply);
    }
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public List<ArrearApply> selectArrearApplyWithRepeat(ArrearApply arrearApply)
    {
        return arrearApplyMapper.selectArrearApplyWithRepeat(arrearApply);
    }
    public List<Long> selectBatchBasedOnCurrentTimestamp()
    {
        return arrearApplyMapper.selectBatchBasedOnCurrentTimestamp();
    }

    /**
     * 新增欠缴费申请
     * 
     * @param arrearApply 欠缴费申请
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public int insertArrearApply(ArrearApply arrearApply)
    {
        arrearApply.setCreateTime(DateUtils.getNowDate());
        return arrearApplyMapper.insertArrearApply(arrearApply);
    }

    /**
     * 修改欠缴费申请
     * 
     * @param arrearApply 欠缴费申请
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public int updateArrearApply(ArrearApply arrearApply)
    {
        arrearApply.setUpdateTime(DateUtils.getNowDate());
        return arrearApplyMapper.updateArrearApply(arrearApply);
    }

    /**
     * 批量删除欠缴费申请
     * 
     * @param tableIds 需要删除的欠缴费申请主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public int deleteArrearApplyByTableIds(Long[] tableIds)
    {
        return arrearApplyMapper.deleteArrearApplyByTableIds(tableIds);
    }

    /**
     * 删除欠缴费申请信息
     * 
     * @param tableId 欠缴费申请主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "arrear_apply", userAlias = "arrear_apply")
    public int deleteArrearApplyByTableId(Long tableId)
    {
        return arrearApplyMapper.deleteArrearApplyByTableId(tableId);
    }
}
