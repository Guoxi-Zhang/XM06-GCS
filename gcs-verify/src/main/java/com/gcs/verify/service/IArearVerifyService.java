package com.gcs.verify.service;

import com.gcs.verify.domain.ArearVerify;
import com.gcs.verify.domain.VerifyHistory;

import java.util.List;

public interface IArearVerifyService {
    List<ArearVerify> selectArearVerifyList(ArearVerify arearVerify);

    ArearVerify selectArearVerifyByApplyId(Long applyId);

    int setArearVerify(VerifyHistory verifyHistory);

    int modifyBenefitApply(ArearVerify arearVerify);
}
