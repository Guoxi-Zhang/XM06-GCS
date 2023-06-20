package com.gcs.pack.mapper;

import java.util.List;
import com.gcs.pack.domain.PackInfo;

/**
 * 礼包信息Mapper接口
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public interface PackInfoMapper 
{
    /**
     * 查询礼包信息
     * 
     * @param giftId 礼包信息主键
     * @return 礼包信息
     */
    public PackInfo selectPackInfoByGiftId(Long giftId);

    /**
     * 查询礼包信息列表
     * 
     * @param packInfo 礼包信息
     * @return 礼包信息集合
     */
    public List<PackInfo> selectPackInfoList(PackInfo packInfo);

    /**
     * 新增礼包信息
     * 
     * @param packInfo 礼包信息
     * @return 结果
     */
    public int insertPackInfo(PackInfo packInfo);

    /**
     * 修改礼包信息
     * 
     * @param packInfo 礼包信息
     * @return 结果
     */
    public int updatePackInfo(PackInfo packInfo);

    /**
     * 删除礼包信息
     * 
     * @param giftId 礼包信息主键
     * @return 结果
     */
    public int deletePackInfoByGiftId(Long giftId);

    /**
     * 批量删除礼包信息
     * 
     * @param giftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackInfoByGiftIds(Long[] giftIds);
}
