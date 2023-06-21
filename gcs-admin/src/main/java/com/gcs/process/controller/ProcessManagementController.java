package com.gcs.process.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gcs.common.annotation.Log;
import com.gcs.common.core.controller.BaseController;
import com.gcs.common.core.domain.AjaxResult;
import com.gcs.common.enums.BusinessType;
import com.gcs.process.domain.ProcessManagement;
import com.gcs.process.service.IProcessManagementService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 流程管理Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@RestController
@RequestMapping("/process/process_management")
public class ProcessManagementController extends BaseController
{
    @Autowired
    private IProcessManagementService processManagementService;

    /**
     * 查询流程管理列表
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessManagement processManagement)
    {
        startPage();
        List<ProcessManagement> list = processManagementService.selectProcessManagementList(processManagement);
        return getDataTable(list);
    }

    /**
     * 导出流程管理列表
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:export')")
    @Log(title = "流程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessManagement processManagement)
    {
        List<ProcessManagement> list = processManagementService.selectProcessManagementList(processManagement);
        ExcelUtil<ProcessManagement> util = new ExcelUtil<ProcessManagement>(ProcessManagement.class);
        util.exportExcel(response, list, "流程管理数据");
    }

    /**
     * 获取流程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processManagementService.selectProcessManagementById(id));
    }

    /**
     * 新增流程管理
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:add')")
    @Log(title = "流程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessManagement processManagement)
    {
        List<ProcessManagement> list = processManagementService.selectProcessManagementRepeat(processManagement);
        Long batch = processManagement.getBatch();
        System.out.println(batch);
        int i=0;
        for(ProcessManagement x:list){
            System.out.println(i + " :" + x.toString());
            if(x.getBatch()==batch){

                return AjaxResult.error("已经存在相同批次！操作失败！");
            }
        }
        return toAjax(processManagementService.insertProcessManagement(processManagement));

    }

    /**
     * 修改流程管理
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:edit')")
    @Log(title = "流程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessManagement processManagement)
    {
        List<ProcessManagement> list = processManagementService.selectProcessManagementRepeat(processManagement);
        Long batch = processManagement.getBatch();
        System.out.println(batch);
        int i=0;
        for(ProcessManagement x:list){
            System.out.println(i + " :" + x.toString());
            if(x.getBatch()==batch){

                return AjaxResult.error("已经存在相同批次！操作失败！");
            }
        }
        return toAjax(processManagementService.updateProcessManagement(processManagement));
    }

    /**
     * 删除流程管理
     */
    @PreAuthorize("@ss.hasPermi('process:process_management:remove')")
    @Log(title = "流程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processManagementService.deleteProcessManagementByIds(ids));
    }
}
