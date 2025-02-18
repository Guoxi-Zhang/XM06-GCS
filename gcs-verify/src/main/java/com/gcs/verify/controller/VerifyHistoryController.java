package com.gcs.verify.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
//import javafx.util.Pair;

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
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.service.IVerifyHistoryService;
import com.gcs.common.utils.poi.ExcelUtil;
import com.gcs.common.core.page.TableDataInfo;

/**
 * 审核历史Controller
 * 
 * @author xm06-gcs
 * @date 2023-06-19
 */
@RestController
@RequestMapping("/verify/verify_history")
public class VerifyHistoryController extends BaseController
{
    @Autowired
    private IVerifyHistoryService verifyHistoryService;

    /**
     * 查询审核历史列表
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:list')")
    @GetMapping("/list")
    public TableDataInfo list(VerifyHistory verifyHistory)
    {
        startPage();
        List<VerifyHistory> list = verifyHistoryService.selectVerifyHistoryList(verifyHistory);
        return getDataTable(list);
    }

    //统计图表
    @GetMapping("/chart")
    public AjaxResult chartData(VerifyHistory verifyHistory)
    {
        AjaxResult ajax = AjaxResult.success();
        //申请类型、总数、通过数
        Map<Long, int[]> map = new HashMap<Long, int[]>();

        List<VerifyHistory> list = verifyHistoryService.selectVerifyHistoryList(verifyHistory);
        for(VerifyHistory a : list){
            Long type = a.getApplyType();
            Long passed = a.getVerifyAction();
            int[] pair = new int[2];
            if(map.containsKey(type)){
                pair = map.get(type);
                //通过
                if(passed == 3){
                    pair[1]++;
                }//未通过
                pair[0]++;
                map.replace(type, pair);

            }else{
                if(passed == 3){
                    pair[1] = 1;

                }
                pair[0] = 1;
                map.put(type, pair);

            }

        }

        List<Long> type = new ArrayList<>();
        List<Integer> totalNum = new ArrayList<>();
        List<Integer> passedNum = new ArrayList<>();
        for(Map.Entry<Long, int[]> entry : map.entrySet()){
            type.add(entry.getKey());
            totalNum.add(entry.getValue()[0]);
            passedNum.add(entry.getValue()[1]);
        }

        ajax.put("type",type);
        ajax.put("totalNum",totalNum);
        ajax.put("passedNum", passedNum);
        return ajax;
    }

    /**
     * 导出审核历史列表
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:export')")
    @Log(title = "审核历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VerifyHistory verifyHistory)
    {
        List<VerifyHistory> list = verifyHistoryService.selectVerifyHistoryList(verifyHistory);
        ExcelUtil<VerifyHistory> util = new ExcelUtil<VerifyHistory>(VerifyHistory.class);
        util.exportExcel(response, list, "审核历史数据");
    }

    /**
     * 获取审核历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:query')")
    @GetMapping(value = "/{verifyId}")
    public AjaxResult getInfo(@PathVariable("verifyId") Long verifyId)
    {
        return success(verifyHistoryService.selectVerifyHistoryByVerifyId(verifyId));
    }

    /**
     * 新增审核历史
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:add')")
    @Log(title = "审核历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VerifyHistory verifyHistory)
    {
        return toAjax(verifyHistoryService.insertVerifyHistory(verifyHistory));
    }

    /**
     * 修改审核历史
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:edit')")
    @Log(title = "审核历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VerifyHistory verifyHistory)
    {
        return toAjax(verifyHistoryService.updateVerifyHistory(verifyHistory));
    }

    /**
     * 删除审核历史
     */
    @PreAuthorize("@ss.hasPermi('verify:verify_history:remove')")
    @Log(title = "审核历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{verifyIds}")
    public AjaxResult remove(@PathVariable Long[] verifyIds)
    {
        return toAjax(verifyHistoryService.deleteVerifyHistoryByVerifyIds(verifyIds));
    }
}
