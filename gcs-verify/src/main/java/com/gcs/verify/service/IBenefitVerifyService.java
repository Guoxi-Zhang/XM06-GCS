package com.gcs.verify.service;

import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;

import java.util.List;

public interface IBenefitVerifyService {
    List<BenefitVerify> selectBenefitVerifyList(BenefitVerify benefitVerify);

    List<VerifyHistory> selectVerifyListByApplyId(Long applyId);

    int modifyBenefitApply(BenefitVerify benefitVerify);
}
