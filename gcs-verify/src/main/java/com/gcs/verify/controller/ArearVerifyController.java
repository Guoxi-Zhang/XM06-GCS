package com.gcs.verify.controller;


import com.gcs.common.core.controller.BaseController;
import com.gcs.common.core.domain.AjaxResult;
import com.gcs.common.core.page.TableDataInfo;
import com.gcs.verify.domain.ArearVerify;
import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.service.IArearVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/verify/arear_list")
public class ArearVerifyController extends BaseController {

    @Autowired
    private IArearVerifyService arearVerifyService;

    @GetMapping("/list")
    public TableDataInfo list(ArearVerify arearVerify) {
        startPage();
        List<ArearVerify> list = arearVerifyService.selectArearVerifyList(arearVerify);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('verify:arear_list:detail')")
    @GetMapping("/getArearVerifyByApplyId")
    public TableDataInfo getArearVerifyByApplyId(Long applyId) {
        startPage();
        ArearVerify item = arearVerifyService.selectArearVerifyByApplyId(applyId);
        return getDataTable(Stream.of(item).collect(Collectors.toList()));
    }

    @PreAuthorize("@ss.hasPermi('verify:arear_list:edit')")
    @PostMapping("/addArearVerify")
    public AjaxResult addArearVerify(VerifyHistory verifyHistory) {
        return toAjax(arearVerifyService.setArearVerify(verifyHistory));
    }

    @PreAuthorize("@ss.hasPermi('verify:arear_list:edit')")
    @PostMapping("/modifyArearApply")
    public AjaxResult modifyArearApply(ArearVerify arearVerify) {
        return toAjax(arearVerifyService.modifyBenefitApply(arearVerify));
    }
}
