package com.gcs.verify.service;

import java.util.List;
import com.gcs.verify.domain.VerifyHistory;

/**
 * 审核历史Service接口
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
public interface IVerifyHistoryService 
{
    /**
     * 查询审核历史
     * 
     * @param verifyId 审核历史主键
     * @return 审核历史
     */
    public VerifyHistory selectVerifyHistoryByVerifyId(Long verifyId);

    /**
     * 查询审核历史列表
     * 
     * @param verifyHistory 审核历史
     * @return 审核历史集合
     */
    public List<VerifyHistory> selectVerifyHistoryList(VerifyHistory verifyHistory);

    /**
     * 新增审核历史
     * 
     * @param verifyHistory 审核历史
     * @return 结果
     */
    public int insertVerifyHistory(VerifyHistory verifyHistory);

    /**
     * 修改审核历史
     * 
     * @param verifyHistory 审核历史
     * @return 结果
     */
    public int updateVerifyHistory(VerifyHistory verifyHistory);

    /**
     * 批量删除审核历史
     * 
     * @param verifyIds 需要删除的审核历史主键集合
     * @return 结果
     */
    public int deleteVerifyHistoryByVerifyIds(Long[] verifyIds);

    /**
     * 删除审核历史信息
     * 
     * @param verifyId 审核历史主键
     * @return 结果
     */
    public int deleteVerifyHistoryByVerifyId(Long verifyId);
}
