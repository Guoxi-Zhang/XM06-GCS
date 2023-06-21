package com.gcs.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.process.mapper.ProcessManagementMapper;
import com.gcs.process.domain.ProcessManagement;
import com.gcs.process.service.IProcessManagementService;

/**
 * 流程管理Service业务层处理
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@Service
public class ProcessManagementServiceImpl implements IProcessManagementService 
{
    @Autowired
    private ProcessManagementMapper processManagementMapper;

    /**
     * 查询流程管理
     * 
     * @param id 流程管理主键
     * @return 流程管理
     */
    @Override
    public ProcessManagement selectProcessManagementById(Long id)
    {
        return processManagementMapper.selectProcessManagementById(id);
    }

    /**
     * 查询流程管理列表
     * 
     * @param processManagement 流程管理
     * @return 流程管理
     */
    @Override
    public List<ProcessManagement> selectProcessManagementList(ProcessManagement processManagement)
    {
        return processManagementMapper.selectProcessManagementList(processManagement);
    }
    public List<ProcessManagement> selectProcessManagementRepeat(ProcessManagement processManagement)
    {
        return processManagementMapper.selectProcessManagementRepeat(processManagement);
    }
    /**
     * 新增流程管理
     * 
     * @param processManagement 流程管理
     * @return 结果
     */
    @Override
    public int insertProcessManagement(ProcessManagement processManagement)
    {
        return processManagementMapper.insertProcessManagement(processManagement);
    }

    /**
     * 修改流程管理
     * 
     * @param processManagement 流程管理
     * @return 结果
     */
    @Override
    public int updateProcessManagement(ProcessManagement processManagement)
    {
        return processManagementMapper.updateProcessManagement(processManagement);
    }

    /**
     * 批量删除流程管理
     * 
     * @param ids 需要删除的流程管理主键
     * @return 结果
     */
    @Override
    public int deleteProcessManagementByIds(Long[] ids)
    {
        return processManagementMapper.deleteProcessManagementByIds(ids);
    }

    /**
     * 删除流程管理信息
     * 
     * @param id 流程管理主键
     * @return 结果
     */
    @Override
    public int deleteProcessManagementById(Long id)
    {
        return processManagementMapper.deleteProcessManagementById(id);
    }
}
