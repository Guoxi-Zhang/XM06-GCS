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
import com.gcs.apply.domain.ArrearApply;
import com.gcs.apply.service.IArrearApplyService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 欠缴费申请Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
@RestController
@RequestMapping("/arrear/arrear_apply")
public class ArrearApplyController extends BaseController
{
    @Autowired
    private IArrearApplyService arrearApplyService;

    /**
     * 查询欠缴费申请列表
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrearApply arrearApply)
    {
        startPage();
        List<ArrearApply> list = arrearApplyService.selectArrearApplyList(arrearApply);
        return getDataTable(list);
    }

    /**
     * 导出欠缴费申请列表
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:export')")
    @Log(title = "欠缴费申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrearApply arrearApply)
    {
        List<ArrearApply> list = arrearApplyService.selectArrearApplyList(arrearApply);
        ExcelUtil<ArrearApply> util = new ExcelUtil<ArrearApply>(ArrearApply.class);
        util.exportExcel(response, list, "欠缴费申请数据");
    }

    /**
     * 获取欠缴费申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(arrearApplyService.selectArrearApplyByTableId(tableId));
    }

    /**
     * 新增欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:add')")
    @Log(title = "欠缴费申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrearApply arrearApply)
    {
        return toAjax(arrearApplyService.insertArrearApply(arrearApply));
    }

    /**
     * 修改欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:edit')")
    @Log(title = "欠缴费申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrearApply arrearApply)
    {
        return toAjax(arrearApplyService.updateArrearApply(arrearApply));
    }

    /**
     * 删除欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:remove')")
    @Log(title = "欠缴费申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(arrearApplyService.deleteArrearApplyByTableIds(tableIds));
    }
}
