package com.gcs.verify.controller;


import com.gcs.common.core.controller.BaseController;
import com.gcs.common.core.domain.AjaxResult;
import com.gcs.common.core.page.TableDataInfo;
import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.service.IBenefitVerifyService;
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
@RequestMapping("/verify/benefit_list")
public class BenefitVerifyController extends BaseController
{
    @Autowired
    IBenefitVerifyService benefitVerifyService;



    @PreAuthorize("@ss.hasPermi('verify:benefit_list:list')")
    @GetMapping("/list")
    public TableDataInfo list(BenefitVerify benefitVerify) {
        startPage();
        List<BenefitVerify> list = benefitVerifyService.selectBenefitVerifyList(benefitVerify);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('verify:benefit_list:list')")
    @GetMapping("/getVerifyListByApplyId")
    public TableDataInfo getVerifyListByApplyId(Long applyId) {
        startPage();
        BenefitVerify item = benefitVerifyService.selectVerifyListByApplyId(applyId);
        return getDataTable(Stream.of(item).collect(Collectors.toList()));
    }

    @PreAuthorize("@ss.hasPermi('verify:benefit_list:edit')")
    @PostMapping("/modifyBenefitApply")
    public AjaxResult modifyBenefitApply(BenefitVerify benefitVerify) {
        return toAjax(benefitVerifyService.modifyBenefitApply(benefitVerify));
    }

    @PreAuthorize("@ss.hasPermi('verify:benefit_list:edit')")
    @PostMapping("/addBenefitVerify")
    public AjaxResult addBenefitVerify(VerifyHistory verifyHistory) {
        return toAjax(benefitVerifyService.setBenefitVerify(verifyHistory));
    }


}
