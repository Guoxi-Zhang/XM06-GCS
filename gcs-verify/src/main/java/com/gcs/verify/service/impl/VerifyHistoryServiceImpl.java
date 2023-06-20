package com.gcs.verify.service.impl;

import java.util.List;
import com.gcs.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.verify.mapper.VerifyHistoryMapper;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.service.IVerifyHistoryService;

/**
 * 审核历史Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
@Service
public class VerifyHistoryServiceImpl implements IVerifyHistoryService 
{
    @Autowired
    private VerifyHistoryMapper verifyHistoryMapper;

    /**
     * 查询审核历史
     * 
     * @param verifyId 审核历史主键
     * @return 审核历史
     */
    @Override
    public VerifyHistory selectVerifyHistoryByVerifyId(Long verifyId)
    {
        return verifyHistoryMapper.selectVerifyHistoryByVerifyId(verifyId);
    }

    /**
     * 查询审核历史列表
     * 
     * @param verifyHistory 审核历史
     * @return 审核历史
     */
    @Override
    public List<VerifyHistory> selectVerifyHistoryList(VerifyHistory verifyHistory)
    {
        return verifyHistoryMapper.selectVerifyHistoryList(verifyHistory);
    }

    /**
     * 新增审核历史
     * 
     * @param verifyHistory 审核历史
     * @return 结果
     */
    @Override
    public int insertVerifyHistory(VerifyHistory verifyHistory)
    {
        verifyHistory.setCreateTime(DateUtils.getNowDate());
        return verifyHistoryMapper.insertVerifyHistory(verifyHistory);
    }

    /**
     * 修改审核历史
     * 
     * @param verifyHistory 审核历史
     * @return 结果
     */
    @Override
    public int updateVerifyHistory(VerifyHistory verifyHistory)
    {
        verifyHistory.setUpdateTime(DateUtils.getNowDate());
        return verifyHistoryMapper.updateVerifyHistory(verifyHistory);
    }

    /**
     * 批量删除审核历史
     * 
     * @param verifyIds 需要删除的审核历史主键
     * @return 结果
     */
    @Override
    public int deleteVerifyHistoryByVerifyIds(Long[] verifyIds)
    {
        return verifyHistoryMapper.deleteVerifyHistoryByVerifyIds(verifyIds);
    }

    /**
     * 删除审核历史信息
     * 
     * @param verifyId 审核历史主键
     * @return 结果
     */
    @Override
    public int deleteVerifyHistoryByVerifyId(Long verifyId)
    {
        return verifyHistoryMapper.deleteVerifyHistoryByVerifyId(verifyId);
    }
}
