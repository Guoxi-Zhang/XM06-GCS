package com.gcs.apply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.gcs.common.core.domain.entity.SysUser;
import com.gcs.common.utils.SecurityUtils;
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
 * 爱心大礼包申请Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
@RestController
@RequestMapping("/pack_apply/pack_apply")
public class PackApplyController extends BaseController
{
    @Autowired
    private IPackApplyService packApplyService;

    /**
     * 查询爱心大礼包申请列表
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
     * 导出爱心大礼包申请列表
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:export')")
    @Log(title = "爱心大礼包申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackApply packApply)
    {
        List<PackApply> list = packApplyService.selectPackApplyList(packApply);
        ExcelUtil<PackApply> util = new ExcelUtil<PackApply>(PackApply.class);
        util.exportExcel(response, list, "爱心大礼包申请数据");
    }

    /**
     * 获取爱心大礼包申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(packApplyService.selectPackApplyByTableId(tableId));
    }

    @GetMapping("/user_info")
    public Map<String,Long> getUserRole()
    {
        Map<String,Long> map = new HashMap<String,Long>();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = SecurityUtils.getUserId();
        Long roleId=user.getRoles().get(0).getRoleId();
        map.put("roleId",roleId);
        map.put("userId",userId);
        map.put("operatorId",userId);
        return map;
    }

    /**
     * 新增爱心大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:add')")
    @Log(title = "爱心大礼包申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackApply packApply)
    {
        return toAjax(packApplyService.insertPackApply(packApply));
    }

    /**
     * 修改爱心大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:edit')")
    @Log(title = "爱心大礼包申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackApply packApply)
    {
        return toAjax(packApplyService.updatePackApply(packApply));
    }

    /**
     * 删除爱心大礼包申请
     */
    @PreAuthorize("@ss.hasPermi('pack_apply:pack_apply:remove')")
    @Log(title = "爱心大礼包申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(packApplyService.deletePackApplyByTableIds(tableIds));
    }
}
