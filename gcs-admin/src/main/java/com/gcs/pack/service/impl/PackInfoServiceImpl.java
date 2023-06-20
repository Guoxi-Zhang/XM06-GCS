package com.gcs.pack.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.pack.mapper.PackInfoMapper;
import com.gcs.pack.domain.PackInfo;
import com.gcs.pack.service.IPackInfoService;

/**
 * 礼包信息Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@Service
public class PackInfoServiceImpl implements IPackInfoService 
{
    @Autowired
    private PackInfoMapper packInfoMapper;

    /**
     * 查询礼包信息
     * 
     * @param giftId 礼包信息主键
     * @return 礼包信息
     */
    @Override
    public PackInfo selectPackInfoByGiftId(Long giftId)
    {
        return packInfoMapper.selectPackInfoByGiftId(giftId);
    }

    /**
     * 查询礼包信息列表
     * 
     * @param packInfo 礼包信息
     * @return 礼包信息
     */
    @Override
    public List<PackInfo> selectPackInfoList(PackInfo packInfo)
    {
        return packInfoMapper.selectPackInfoList(packInfo);
    }

    /**
     * 新增礼包信息
     * 
     * @param packInfo 礼包信息
     * @return 结果
     */
    @Override
    public int insertPackInfo(PackInfo packInfo)
    {
        return packInfoMapper.insertPackInfo(packInfo);
    }

    /**
     * 修改礼包信息
     * 
     * @param packInfo 礼包信息
     * @return 结果
     */
    @Override
    public int updatePackInfo(PackInfo packInfo)
    {
        return packInfoMapper.updatePackInfo(packInfo);
    }

    /**
     * 批量删除礼包信息
     * 
     * @param giftIds 需要删除的礼包信息主键
     * @return 结果
     */
    @Override
    public int deletePackInfoByGiftIds(Long[] giftIds)
    {
        return packInfoMapper.deletePackInfoByGiftIds(giftIds);
    }

    /**
     * 删除礼包信息信息
     * 
     * @param giftId 礼包信息主键
     * @return 结果
     */
    @Override
    public int deletePackInfoByGiftId(Long giftId)
    {
        return packInfoMapper.deletePackInfoByGiftId(giftId);
    }
}
