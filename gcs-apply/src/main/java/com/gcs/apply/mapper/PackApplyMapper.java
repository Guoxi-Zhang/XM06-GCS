package com.gcs.apply.mapper;

import java.util.List;
import com.gcs.apply.domain.PackApply;
import com.gcs.apply.domain.PackList;

/**
 * 大礼包申请Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public interface PackApplyMapper 
{
    /**
     * 查询大礼包申请
     * 
     * @param tableId 大礼包申请主键
     * @return 大礼包申请
     */
    public PackApply selectPackApplyByTableId(Long tableId);

    /**
     * 查询大礼包申请列表
     * 
     * @param packApply 大礼包申请
     * @return 大礼包申请集合
     */
    public List<PackApply> selectPackApplyList(PackApply packApply);

    /**
     * 新增大礼包申请
     * 
     * @param packApply 大礼包申请
     * @return 结果
     */
    public int insertPackApply(PackApply packApply);

    /**
     * 修改大礼包申请
     * 
     * @param packApply 大礼包申请
     * @return 结果
     */
    public int updatePackApply(PackApply packApply);

    /**
     * 删除大礼包申请
     * 
     * @param tableId 大礼包申请主键
     * @return 结果
     */
    public int deletePackApplyByTableId(Long tableId);

    /**
     * 批量删除大礼包申请
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackApplyByTableIds(Long[] tableIds);

    /**
     * 批量删除物品选择列表
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackListByTableIds(Long[] tableIds);
    
    /**
     * 批量新增物品选择列表
     * 
     * @param packListList 物品选择列表列表
     * @return 结果
     */
    public int batchPackList(List<PackList> packListList);
    

    /**
     * 通过大礼包申请主键删除物品选择列表信息
     * 
     * @param tableId 大礼包申请ID
     * @return 结果
     */
    public int deletePackListByTableId(Long tableId);
}
