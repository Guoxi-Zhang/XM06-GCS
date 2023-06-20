package com.gcs.apply.service.impl;

import java.util.List;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.gcs.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.gcs.apply.domain.PackList;
import com.gcs.apply.mapper.PackApplyMapper;
import com.gcs.apply.domain.PackApply;
import com.gcs.apply.service.IPackApplyService;

/**
 * 大礼包申请Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@Service
public class PackApplyServiceImpl implements IPackApplyService 
{
    @Autowired
    private PackApplyMapper packApplyMapper;

    /**
     * 查询大礼包申请
     * 
     * @param tableId 大礼包申请主键
     * @return 大礼包申请
     */
    @Override
    public PackApply selectPackApplyByTableId(Long tableId)
    {
        return packApplyMapper.selectPackApplyByTableId(tableId);
    }

    /**
     * 查询大礼包申请列表
     * 
     * @param packApply 大礼包申请
     * @return 大礼包申请
     */
    @Override
    public List<PackApply> selectPackApplyList(PackApply packApply)
    {
        return packApplyMapper.selectPackApplyList(packApply);
    }

    /**
     * 新增大礼包申请
     * 
     * @param packApply 大礼包申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPackApply(PackApply packApply)
    {
        packApply.setCreateTime(DateUtils.getNowDate());
        int rows = packApplyMapper.insertPackApply(packApply);
        insertPackList(packApply);
        return rows;
    }

    /**
     * 修改大礼包申请
     * 
     * @param packApply 大礼包申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePackApply(PackApply packApply)
    {
        packApply.setUpdateTime(DateUtils.getNowDate());
        packApplyMapper.deletePackListByTableId(packApply.getTableId());
        insertPackList(packApply);
        return packApplyMapper.updatePackApply(packApply);
    }

    /**
     * 批量删除大礼包申请
     * 
     * @param tableIds 需要删除的大礼包申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePackApplyByTableIds(Long[] tableIds)
    {
        packApplyMapper.deletePackListByTableIds(tableIds);
        return packApplyMapper.deletePackApplyByTableIds(tableIds);
    }

    /**
     * 删除大礼包申请信息
     * 
     * @param tableId 大礼包申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePackApplyByTableId(Long tableId)
    {
        packApplyMapper.deletePackListByTableId(tableId);
        return packApplyMapper.deletePackApplyByTableId(tableId);
    }

    /**
     * 新增物品选择列表信息
     * 
     * @param packApply 大礼包申请对象
     */
    public void insertPackList(PackApply packApply)
    {
        List<PackList> packListList = packApply.getPackListList();
        Long tableId = packApply.getTableId();
        if (StringUtils.isNotNull(packListList))
        {
            List<PackList> list = new ArrayList<PackList>();
            for (PackList packList : packListList)
            {
                packList.setTableId(tableId);
                list.add(packList);
            }
            if (list.size() > 0)
            {
                packApplyMapper.batchPackList(list);
            }
        }
    }
}
