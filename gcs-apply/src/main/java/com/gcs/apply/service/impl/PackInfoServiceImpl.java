package com.gcs.apply.service.impl;

import java.util.List;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.gcs.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.gcs.apply.domain.PackGift;
import com.gcs.apply.mapper.PackInfoMapper;
import com.gcs.apply.domain.PackInfo;
import com.gcs.apply.service.IPackInfoService;

/**
 * 礼包设置Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
@Service
public class PackInfoServiceImpl implements IPackInfoService 
{
    @Autowired
    private PackInfoMapper packInfoMapper;

    /**
     * 查询礼包设置
     * 
     * @param packId 礼包设置主键
     * @return 礼包设置
     */
    @Override
    public PackInfo selectPackInfoByPackId(Long packId)
    {
        return packInfoMapper.selectPackInfoByPackId(packId);
    }

    /**
     * 查询礼包设置列表
     * 
     * @param packInfo 礼包设置
     * @return 礼包设置
     */
    @Override
    public List<PackInfo> selectPackInfoList(PackInfo packInfo)
    {
        return packInfoMapper.selectPackInfoList(packInfo);
    }

    /**
     * 新增礼包设置
     * 
     * @param packInfo 礼包设置
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPackInfo(PackInfo packInfo)
    {
        packInfo.setCreateTime(DateUtils.getNowDate());
        int rows = packInfoMapper.insertPackInfo(packInfo);
        insertPackGift(packInfo);
        return rows;
    }

    /**
     * 修改礼包设置
     * 
     * @param packInfo 礼包设置
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePackInfo(PackInfo packInfo)
    {
        packInfo.setUpdateTime(DateUtils.getNowDate());
        packInfoMapper.deletePackGiftByPackId(packInfo.getPackId());
        insertPackGift(packInfo);
        return packInfoMapper.updatePackInfo(packInfo);
    }

    /**
     * 批量删除礼包设置
     * 
     * @param packIds 需要删除的礼包设置主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePackInfoByPackIds(Long[] packIds)
    {
        packInfoMapper.deletePackGiftByPackIds(packIds);
        return packInfoMapper.deletePackInfoByPackIds(packIds);
    }

    /**
     * 删除礼包设置信息
     * 
     * @param packId 礼包设置主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePackInfoByPackId(Long packId)
    {
        packInfoMapper.deletePackGiftByPackId(packId);
        return packInfoMapper.deletePackInfoByPackId(packId);
    }

    /**
     * 新增礼包物品选择信息
     * 
     * @param packInfo 礼包设置对象
     */
    public void insertPackGift(PackInfo packInfo)
    {
        List<PackGift> packGiftList = packInfo.getPackGiftList();
        Long packId = packInfo.getPackId();
        if (StringUtils.isNotNull(packGiftList))
        {
            List<PackGift> list = new ArrayList<PackGift>();
            for (PackGift packGift : packGiftList)
            {
                packGift.setPackId(packId);
                list.add(packGift);
            }
            if (list.size() > 0)
            {
                packInfoMapper.batchPackGift(list);
            }
        }
    }
}
