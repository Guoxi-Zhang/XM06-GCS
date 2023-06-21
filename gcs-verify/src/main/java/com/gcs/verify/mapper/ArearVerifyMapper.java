package com.gcs.verify.mapper;

import com.gcs.verify.domain.ArearVerify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArearVerifyMapper {

    List<ArearVerify> selectArearVerifyList(ArearVerify arearVerify);

    ArearVerify selectArearVerifyByApplyId(Long applyId);

    void setApplyState(@Param("applyId") Long applyId,
                       @Param("nextStage") Long nextStage, @Param("nextState")Long nextState);

    int modifyArearApply(ArearVerify arearVerify);
}
