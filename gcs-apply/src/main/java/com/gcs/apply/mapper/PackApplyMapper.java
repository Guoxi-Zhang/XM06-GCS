package com.gcs.apply.mapper;

import java.util.List;
import com.gcs.apply.domain.PackApply;

/**
 * 爱心大礼包申请Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public interface PackApplyMapper 
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
     * 删除爱心大礼包申请
     * 
     * @param tableId 爱心大礼包申请主键
     * @return 结果
     */
    public int deletePackApplyByTableId(Long tableId);

    /**
     * 批量删除爱心大礼包申请
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackApplyByTableIds(Long[] tableIds);
}
