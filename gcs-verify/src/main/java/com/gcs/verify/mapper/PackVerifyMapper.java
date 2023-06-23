package com.gcs.verify.mapper;

import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.PackVerify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackVerifyMapper {
    List<PackVerify> selectPackVerifyList(PackVerify packVerify);

    PackVerify selectPackVerifyByApplyId(Long applyId);

    void setApplyState(@Param("applyId") Long applyId,
                       @Param("nextStage") Long nextStage, @Param("nextState")Long nextState);
}
