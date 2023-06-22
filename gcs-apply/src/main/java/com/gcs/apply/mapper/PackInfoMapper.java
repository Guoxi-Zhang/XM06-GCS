package com.gcs.apply.mapper;

import java.util.List;
import com.gcs.apply.domain.PackInfo;
import com.gcs.apply.domain.PackGift;

/**
 * 礼包设置Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
public interface PackInfoMapper 
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
     * 删除礼包设置
     * 
     * @param packId 礼包设置主键
     * @return 结果
     */
    public int deletePackInfoByPackId(Long packId);

    /**
     * 批量删除礼包设置
     * 
     * @param packIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackInfoByPackIds(Long[] packIds);

    /**
     * 批量删除礼包物品选择
     * 
     * @param packIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackGiftByPackIds(Long[] packIds);
    
    /**
     * 批量新增礼包物品选择
     * 
     * @param packGiftList 礼包物品选择列表
     * @return 结果
     */
    public int batchPackGift(List<PackGift> packGiftList);
    

    /**
     * 通过礼包设置主键删除礼包物品选择信息
     * 
     * @param packId 礼包设置ID
     * @return 结果
     */
    public int deletePackGiftByPackId(Long packId);
}
