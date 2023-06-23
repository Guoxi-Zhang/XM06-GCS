package com.gcs.apply.service.impl;

import java.util.List;

import com.gcs.apply.domain.BenefitApply;
import com.gcs.common.annotation.DataScope;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.apply.mapper.PackApplyMapper;
import com.gcs.apply.domain.PackApply;
import com.gcs.apply.service.IPackApplyService;

/**
 * 爱心大礼包申请Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
@Service
public class PackApplyServiceImpl implements IPackApplyService 
{
    @Autowired
    private PackApplyMapper packApplyMapper;

    /**
     * 查询爱心大礼包申请
     * 
     * @param tableId 爱心大礼包申请主键
     * @return 爱心大礼包申请
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public PackApply selectPackApplyByTableId(Long tableId)
    {
        return packApplyMapper.selectPackApplyByTableId(tableId);
    }

    /**
     * 查询爱心大礼包申请列表
     * 
     * @param packApply 爱心大礼包申请
     * @return 爱心大礼包申请
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public List<PackApply> selectPackApplyList(PackApply packApply)
    {
        return packApplyMapper.selectPackApplyList(packApply);
    }
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public List<PackApply> selectPackApplyWithRepeat(PackApply packApply)
    {
        return packApplyMapper.selectPackApplyWithRepeat(packApply);
    }

    /**
     * 新增爱心大礼包申请
     * 
     * @param packApply 爱心大礼包申请
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public int insertPackApply(PackApply packApply)
    {
        packApply.setCreateTime(DateUtils.getNowDate());
        return packApplyMapper.insertPackApply(packApply);
    }

    /**
     * 修改爱心大礼包申请
     * 
     * @param packApply 爱心大礼包申请
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public int updatePackApply(PackApply packApply)
    {
        packApply.setUpdateTime(DateUtils.getNowDate());
        return packApplyMapper.updatePackApply(packApply);
    }

    /**
     * 批量删除爱心大礼包申请
     * 
     * @param tableIds 需要删除的爱心大礼包申请主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public int deletePackApplyByTableIds(Long[] tableIds)
    {
        return packApplyMapper.deletePackApplyByTableIds(tableIds);
    }

    /**
     * 删除爱心大礼包申请信息
     * 
     * @param tableId 爱心大礼包申请主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "pack_apply", userAlias = "pack_apply")
    public int deletePackApplyByTableId(Long tableId)
    {
        return packApplyMapper.deletePackApplyByTableId(tableId);
    }
}
