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
import com.gcs.apply.domain.BenefitApply;
import com.gcs.apply.service.IBenefitApplyService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 补助申请Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
@RestController
@RequestMapping("/benefit/benefit_apply")
public class BenefitApplyController extends BaseController
{
    @Autowired
    private IBenefitApplyService benefitApplyService;

    /**
     * 查询补助申请列表
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(BenefitApply benefitApply)
    {
        startPage();
        List<BenefitApply> list = benefitApplyService.selectBenefitApplyList(benefitApply);
        return getDataTable(list);
    }

    /**
     * 导出补助申请列表
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:export')")
    @Log(title = "补助申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BenefitApply benefitApply)
    {
        List<BenefitApply> list = benefitApplyService.selectBenefitApplyList(benefitApply);
        ExcelUtil<BenefitApply> util = new ExcelUtil<BenefitApply>(BenefitApply.class);
        util.exportExcel(response, list, "补助申请数据");
    }

    /**
     * 获取补助申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(benefitApplyService.selectBenefitApplyByTableId(tableId));
    }

    /**
     * 新增补助申请
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:add')")
    @Log(title = "补助申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BenefitApply benefitApply)
    {
        return toAjax(benefitApplyService.insertBenefitApply(benefitApply));
    }

    /**
     * 修改补助申请
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:edit')")
    @Log(title = "补助申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BenefitApply benefitApply)
    {
        return toAjax(benefitApplyService.updateBenefitApply(benefitApply));
    }

    /**
     * 删除补助申请
     */
    @PreAuthorize("@ss.hasPermi('benefit:benefit_apply:remove')")
    @Log(title = "补助申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(benefitApplyService.deleteBenefitApplyByTableIds(tableIds));
    }
}
