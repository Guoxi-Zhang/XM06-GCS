package com.gcs.pack.controller;

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
import com.gcs.pack.domain.PackInfo;
import com.gcs.pack.service.IPackInfoService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 礼包信息Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-20
 */
@RestController
@RequestMapping("/pack/pack_info")
public class PackInfoController extends BaseController
{
    @Autowired
    private IPackInfoService packInfoService;

    /**
     * 查询礼包信息列表
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackInfo packInfo)
    {
        startPage();
        List<PackInfo> list = packInfoService.selectPackInfoList(packInfo);
        return getDataTable(list);
    }

    /**
     * 导出礼包信息列表
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:export')")
    @Log(title = "礼包信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackInfo packInfo)
    {
        List<PackInfo> list = packInfoService.selectPackInfoList(packInfo);
        ExcelUtil<PackInfo> util = new ExcelUtil<PackInfo>(PackInfo.class);
        util.exportExcel(response, list, "礼包信息数据");
    }

    /**
     * 获取礼包信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:query')")
    @GetMapping(value = "/{giftId}")
    public AjaxResult getInfo(@PathVariable("giftId") Long giftId)
    {
        return success(packInfoService.selectPackInfoByGiftId(giftId));
    }

    /**
     * 新增礼包信息
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:add')")
    @Log(title = "礼包信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackInfo packInfo)
    {
        return toAjax(packInfoService.insertPackInfo(packInfo));
    }

    /**
     * 修改礼包信息
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:edit')")
    @Log(title = "礼包信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackInfo packInfo)
    {
        return toAjax(packInfoService.updatePackInfo(packInfo));
    }

    /**
     * 删除礼包信息
     */
    @PreAuthorize("@ss.hasPermi('pack:pack_info:remove')")
    @Log(title = "礼包信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{giftIds}")
    public AjaxResult remove(@PathVariable Long[] giftIds)
    {
        return toAjax(packInfoService.deletePackInfoByGiftIds(giftIds));
    }
}
