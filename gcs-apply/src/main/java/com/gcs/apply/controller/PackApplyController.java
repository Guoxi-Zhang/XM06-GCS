package com.gcs.apply.controller;

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
import com.gcs.apply.domain.PackApply;
import com.gcs.apply.service.IPackApplyService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 大礼包申请Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@RestController
@RequestMapping("/pack_apply/pack_apply")
public class PackApplyController extends BaseController
{
    @Autowired
    private IPackApplyService packApplyService;

    /**
     * 查询大礼包申请列表
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackApply packApply)
    {
        startPage();
        List<PackApply> list = packApplyService.selectPackApplyList(packApply);
        return getDataTable(list);
    }

    /**
     * 导出大礼包申请列表
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:export')")
    @Log(title = "大礼包申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackApply packApply)
    {
        List<PackApply> list = packApplyService.selectPackApplyList(packApply);
        ExcelUtil<PackApply> util = new ExcelUtil<PackApply>(PackApply.class);
        util.exportExcel(response, list, "大礼包申请数据");
    }

    /**
     * 获取大礼包申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(packApplyService.selectPackApplyByTableId(tableId));
    }

    /**
     * 新增大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:add')")
    @Log(title = "大礼包申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackApply packApply)
    {
        return toAjax(packApplyService.insertPackApply(packApply));
    }

    /**
     * 修改大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:edit')")
    @Log(title = "大礼包申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackApply packApply)
    {
        return toAjax(packApplyService.updatePackApply(packApply));
    }

    /**
     * 删除大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:remove')")
    @Log(title = "大礼包申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(packApplyService.deletePackApplyByTableIds(tableIds));
    }
}
