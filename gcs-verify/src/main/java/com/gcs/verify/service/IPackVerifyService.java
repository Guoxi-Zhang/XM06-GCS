package com.gcs.verify.service;

import com.gcs.verify.domain.PackVerify;
import com.gcs.verify.domain.VerifyHistory;

import java.util.List;

public interface IPackVerifyService {
    List<PackVerify> selectPackVerifyList(PackVerify packVerify);

    PackVerify selectVerifyListByApplyId(Long applyId);

    int setPackVerify(VerifyHistory verifyHistory);
}
