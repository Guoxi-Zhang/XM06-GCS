package com.gcs.process.service;

import java.util.List;
import com.gcs.process.domain.ProcessManagement;

/**
 * 流程管理Service接口
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
public interface IProcessManagementService 
{
    /**
     * 查询流程管理
     * 
     * @param id 流程管理主键
     * @return 流程管理
     */
    public ProcessManagement selectProcessManagementById(Long id);

    /**
     * 查询流程管理列表
     * 
     * @param processManagement 流程管理
     * @return 流程管理集合
     */
    public List<ProcessManagement> selectProcessManagementList(ProcessManagement processManagement);

    /**
     * 新增流程管理
     * 
     * @param processManagement 流程管理
     * @return 结果
     */
    public int insertProcessManagement(ProcessManagement processManagement);

    /**
     * 修改流程管理
     * 
     * @param processManagement 流程管理
     * @return 结果
     */
    public int updateProcessManagement(ProcessManagement processManagement);

    /**
     * 批量删除流程管理
     * 
     * @param ids 需要删除的流程管理主键集合
     * @return 结果
     */
    public int deleteProcessManagementByIds(Long[] ids);

    /**
     * 删除流程管理信息
     * 
     * @param id 流程管理主键
     * @return 结果
     */
    public int deleteProcessManagementById(Long id);
}
