package com.gcs.verify.mapper;

import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BenefitVerifyMapper {

    

    List<BenefitVerify> selectBenefitVerifyList(BenefitVerify benefitVerify);

    BenefitVerify selectVerifyListByApplyId(Long applyId);

    int modifyBenefitApply(BenefitVerify benefitVerify);

    void setApplyState(@Param("applyId") Long applyId,
                       @Param("nextStage") Long nextStage, @Param("nextState")Long nextState);

}
