package com.gcs.verify.service.impl;

import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.mapper.BenefitVerifyMapper;
import com.gcs.verify.service.IBenefitVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefitVerifyServiceImpl implements IBenefitVerifyService {

    @Autowired
    private BenefitVerifyMapper benefitVerifyMapper;

    @Override
    public List<BenefitVerify> selectBenefitVerifyList(BenefitVerify benefitVerify) {
        return benefitVerifyMapper.selectBenefitVerifyList(benefitVerify);
    }

    @Override
    public List<VerifyHistory> selectVerifyListByApplyId(Long applyId) {
        return benefitVerifyMapper.selectVerifyListByApplyId(applyId);
    }

    @Override
    public int modifyBenefitApply(BenefitVerify benefitVerify) {
        if (benefitVerify.getTableId() == null) return 0;
        return benefitVerifyMapper.modifyBenefitApply(benefitVerify);
    }
}
