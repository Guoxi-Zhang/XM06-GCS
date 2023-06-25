package com.gcs.apply.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.gcs.common.core.domain.entity.SysUser;
import com.gcs.common.utils.SecurityUtils;
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
public class ArrearApplyController extends BaseController {
    @Autowired
    private IArrearApplyService arrearApplyService;


    /**
     * 查询欠缴费申请列表
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrearApply arrearApply) {
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
    public AjaxResult chartData(ArrearApply arrearApply) {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, BigDecimal> amountMap = new HashMap<String, BigDecimal>();
        List<ArrearApply> list = arrearApplyService.selectArrearApplyListWithInfo(arrearApply);
        for (ArrearApply a : list) {
            BigDecimal amount = a.getArrearAmount();
            String reason = a.getArrearReason();
            if (map.containsKey(reason)) {
                int num = map.get(reason);
                BigDecimal remainingAmount = amountMap.get(reason);
                map.replace(reason, num + 1);
                amountMap.replace(reason, remainingAmount.add(amount));
            } else {
                map.put(reason, 1);
                amountMap.put(reason, amount);
            }

        }

        List<String> reason = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        List<BigDecimal> amount = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            reason.add(entry.getKey());
            num.add(entry.getValue());
        }
        for (Map.Entry<String, BigDecimal> entry : amountMap.entrySet()) {
            amount.add(entry.getValue().divide(new BigDecimal(1000)));
        }
        ajax.put("num", num);
        ajax.put("reason", reason);
        ajax.put("amount", amount);
        return ajax;
    }

    @GetMapping("/user_info")
    public Map<String, Long> getUserRole() {
        Map<String, Long> map = new HashMap<String, Long>();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = SecurityUtils.getUserId();
        Long roleId = user.getRoles().get(0).getRoleId();
        map.put("roleId", roleId);
        map.put("userId", userId);
        map.put("operatorId", userId);
        return map;
    }

    /**
     * 导出欠缴费申请列表
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:export')")
    @Log(title = "欠缴费申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrearApply arrearApply) {
        List<ArrearApply> list = arrearApplyService.selectArrearApplyList(arrearApply);
        ExcelUtil<ArrearApply> util = new ExcelUtil<ArrearApply>(ArrearApply.class);
        util.exportExcel(response, list, "欠缴费申请数据");
    }

    /**
     * 获取欠缴费申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId) {
        //return success(arrearApplyService.selectArrearApplyByTableId(tableId));
        return success(arrearApplyService.selectArrearApplyWithInfoByTableId(tableId));
    }

    /**
     * 新增欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:add')")
    @Log(title = "欠缴费申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrearApply arrearApply) {
        List<ArrearApply> list = arrearApplyService.selectArrearApplyWithRepeat(arrearApply);
        List<Long> batches = arrearApplyService.selectBatchBasedOnCurrentTimestamp();
        Long arrearId = arrearApply.getArrearId();      //项目
        Long studentId = arrearApply.getStudentId();    //学号
        Long batchId = arrearApply.getBatchId();        //批次
        boolean flag = false;
        for (Long x : batches) {
            if (batchId == x) {
                flag = true;
                break;
            }
        }
        if(!flag) {
            return AjaxResult.error("批次不合法！请重新查看批次信息！");    // 返回error
        }
        System.out.println(arrearId + studentId + batchId);
        int i = 0;
        for (ArrearApply x : list) {
            System.out.println(i + " :" + x.toString());
            if (x.getArrearId() == arrearId && x.getStudentId().equals(studentId) && x.getBatchId() == batchId) {
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
    public AjaxResult edit(@RequestBody ArrearApply arrearApply) {
        return toAjax(arrearApplyService.updateArrearApply(arrearApply));
    }

    /**
     * 删除欠缴费申请
     */
    @PreAuthorize("@ss.hasPermi('arrear:arrear_apply:remove')")
    @Log(title = "欠缴费申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds) {
        return toAjax(arrearApplyService.deleteArrearApplyByTableIds(tableIds));
    }
}
