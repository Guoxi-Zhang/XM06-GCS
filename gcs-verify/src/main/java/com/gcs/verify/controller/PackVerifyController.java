package com.gcs.verify.controller;

import com.gcs.common.core.controller.BaseController;
import com.gcs.common.core.domain.AjaxResult;
import com.gcs.common.core.page.TableDataInfo;
import com.gcs.verify.domain.PackVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.service.IPackVerifyService;
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
@RequestMapping("/verify/pack_list")
public class PackVerifyController extends BaseController {

    @Autowired
    IPackVerifyService packVerifyService;

    @PreAuthorize("@ss.hasPermi('verify:pack_list:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackVerify packVerify) {
        startPage();
        List<PackVerify> list = packVerifyService.selectPackVerifyList(packVerify);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('verify:pack_list:list')")
    @GetMapping("/getVerifyListByApplyId")
    public TableDataInfo getVerifyListByApplyId(Long applyId) {
        startPage();
        PackVerify item = packVerifyService.selectVerifyListByApplyId(applyId);
        return getDataTable(Stream.of(item).collect(Collectors.toList()));
    }

    @PreAuthorize("@ss.hasPermi('verify:pack_list:edit')")
    @PostMapping("/addPackVerify")
    public AjaxResult addPackVerify(VerifyHistory verifyHistory) {
        return toAjax(packVerifyService.setPackVerify(verifyHistory));
    }

}
