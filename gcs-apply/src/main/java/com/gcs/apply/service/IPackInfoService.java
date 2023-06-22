package com.gcs.apply.service;

import java.util.List;
import com.gcs.apply.domain.PackInfo;

/**
 * 礼包设置Service接口
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public interface IPackInfoService 
{
    /**
     * 查询礼包设置
     * 
     * @param packId 礼包设置主键
     * @return 礼包设置
     */
    public PackInfo selectPackInfoByPackId(Long packId);

    /**
     * 查询礼包设置列表
     * 
     * @param packInfo 礼包设置
     * @return 礼包设置集合
     */
    public List<PackInfo> selectPackInfoList(PackInfo packInfo);

    /**
     * 新增礼包设置
     * 
     * @param packInfo 礼包设置
     * @return 结果
     */
    public int insertPackInfo(PackInfo packInfo);

    /**
     * 修改礼包设置
     * 
     * @param packInfo 礼包设置
     * @return 结果
     */
    public int updatePackInfo(PackInfo packInfo);

    /**
     * 批量删除礼包设置
     * 
     * @param packIds 需要删除的礼包设置主键集合
     * @return 结果
     */
    public int deletePackInfoByPackIds(Long[] packIds);

    /**
     * 删除礼包设置信息
     * 
     * @param packId 礼包设置主键
     * @return 结果
     */
    public int deletePackInfoByPackId(Long packId);
}
