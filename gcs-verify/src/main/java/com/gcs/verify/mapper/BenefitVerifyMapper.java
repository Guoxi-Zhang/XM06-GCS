package com.gcs.verify.mapper;

import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;

import java.util.List;

public interface BenefitVerifyMapper {
    List<BenefitVerify> selectBenefitVerifyList(BenefitVerify benefitVerify);

    List<VerifyHistory> selectVerifyListByApplyId(Long applyId);

    int modifyBenefitApply(BenefitVerify benefitVerify);
}
