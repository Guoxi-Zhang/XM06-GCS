package com.gcs.apply.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.gcs.common.core.domain.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        List<ArrearApply> list = arrearApplyService.selectArrearApplyListWithInfo(arrearApply);
        return getDataTable(list);
    }
//    public TableDataInfo list(ArrearApply arrearApply)
//    {
//        startPage();
//        List<ArrearApply> list = arrearApplyService.selectArrearApplyList(arrearApply);
//        return getDataTable(list);
//    }

    @GetMapping("/chart")
    public AjaxResult chartData(ArrearApply arrearApply)
    {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<ArrearApply> list = arrearApplyService.selectArrearApplyListWithInfo(arrearApply);
        for(ArrearApply a : list){
            String reason = a.getArrearReason();
            if(map.containsKey(reason)){
                int num = map.get(reason);
                map.replace(reason, num+1);
            }else{
                map.put(reason, 1);
            }
        }

        List<String> reason = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            reason.add(entry.getKey());
            num.add(entry.getValue());
        }
        ajax.put("num",num);
        ajax.put("reason",reason);

        return ajax;
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
        //return success(arrearApplyService.selectArrearApplyByTableId(tableId));
        return success(arrearApplyService.selectArrearApplyWithInfoByTableId(tableId));
    }

    /**
     * 新增欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:add')")
    @Log(title = "欠缴费申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrearApply arrearApply)
    {
        List<ArrearApply> list = arrearApplyService.selectArrearApplyWithRepeat(arrearApply);
        Long arrearId = arrearApply.getArrearId();
        Long studentId = arrearApply.getStudentId();
        Long batchId = arrearApply.getBatchId();
        System.out.println(arrearId +studentId + batchId);
        int i = 0;
        for(ArrearApply x: list){
            System.out.println(i + " :" + x.toString());
            if(x.getArrearId() == arrearId && x.getStudentId().equals(studentId) && x.getBatchId() == batchId){
                return AjaxResult.error("已经存在相同项目！操作失败！");    // 返回error
            }
        }
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
