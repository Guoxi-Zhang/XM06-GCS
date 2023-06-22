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
import com.gcs.apply.domain.PackInfo;
import com.gcs.apply.service.IPackInfoService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 礼包设置Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-22
 */
@RestController
@RequestMapping("/pack_info/pack_info")
public class PackInfoController extends BaseController
{
    @Autowired
    private IPackInfoService packInfoService;

    /**
     * 查询礼包设置列表
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackInfo packInfo)
    {
        startPage();
        List<PackInfo> list = packInfoService.selectPackInfoList(packInfo);
        return getDataTable(list);
    }

    /**
     * 导出礼包设置列表
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:export')")
    @Log(title = "礼包设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackInfo packInfo)
    {
        List<PackInfo> list = packInfoService.selectPackInfoList(packInfo);
        ExcelUtil<PackInfo> util = new ExcelUtil<PackInfo>(PackInfo.class);
        util.exportExcel(response, list, "礼包设置数据");
    }

    /**
     * 获取礼包设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:query')")
    @GetMapping(value = "/{packId}")
    public AjaxResult getInfo(@PathVariable("packId") Long packId)
    {
        return success(packInfoService.selectPackInfoByPackId(packId));
    }

    /**
     * 新增礼包设置
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:add')")
    @Log(title = "礼包设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackInfo packInfo)
    {
        return toAjax(packInfoService.insertPackInfo(packInfo));
    }

    /**
     * 修改礼包设置
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:edit')")
    @Log(title = "礼包设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackInfo packInfo)
    {
        return toAjax(packInfoService.updatePackInfo(packInfo));
    }

    /**
     * 删除礼包设置
     */
    @PreAuthorize("@ss.hasPermi('pack_info:pack_info:remove')")
    @Log(title = "礼包设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{packIds}")
    public AjaxResult remove(@PathVariable Long[] packIds)
    {
        return toAjax(packInfoService.deletePackInfoByPackIds(packIds));
    }
}
