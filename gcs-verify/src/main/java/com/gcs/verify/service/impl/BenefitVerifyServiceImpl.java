package com.gcs.verify.service.impl;

import com.gcs.common.core.domain.entity.SysRole;
import com.gcs.common.utils.SecurityUtils;
import com.gcs.system.mapper.SysRoleMapper;
import com.gcs.verify.domain.BenefitVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.mapper.BenefitVerifyMapper;
import com.gcs.verify.mapper.VerifyHistoryMapper;
import com.gcs.verify.service.IBenefitVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BenefitVerifyServiceImpl implements IBenefitVerifyService {

    @Autowired
    private BenefitVerifyMapper benefitVerifyMapper;

    @Autowired
    private VerifyHistoryMapper verifyHistoryMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    // [cur stage: 0 counselor, 1 school, 2 university]
    // [verify action: 0 unverified, 1 deny, 2 reject, 3 pass]
    private long[][] nextStageArr = {
            {0, 0, 0, 1},
            {1, 1, 1, 2},
            {2, 2, 2, 2}
    };
    private long[][] nextStateArr = {
            {0, 1, 2, 0},
            {0, 1, 2, 0},
            {0, 1, 2, 3}
    };

    @Override
    public List<BenefitVerify> selectBenefitVerifyList(BenefitVerify benefitVerify) {

        Long userId = SecurityUtils.getUserId();
        Long verifyUnit = Long.valueOf(getVerifyUnit(userId));
        Map<String, String> ancestors = verifyHistoryMapper.selectAncestorsByUserId(userId);

        if (verifyUnit.equals(0L)) {
            benefitVerify.setSchool(ancestors.get("fafa"));
            benefitVerify.setGrade(ancestors.get("fa"));
        } else if (verifyUnit.equals(1L)) {
            benefitVerify.setSchool(ancestors.get("fa"));
        } else if (verifyUnit.equals(3L)) {
            benefitVerify.setStudentId(userId);
        }
        return benefitVerifyMapper.selectBenefitVerifyList(benefitVerify);
    }

    @Override
    public BenefitVerify selectVerifyListByApplyId(Long applyId) {
        return benefitVerifyMapper.selectVerifyListByApplyId(applyId);
    }

    @Override
    public int modifyBenefitApply(BenefitVerify benefitVerify) {
        Long userId = SecurityUtils.getUserId();
        if (benefitVerify.getTableId() == null) return 0;
        if (isStudent(userId)) {
            benefitVerify.setApplyState(0);
            benefitVerify.setNowStep(0);
        }
        return benefitVerifyMapper.modifyBenefitApply(benefitVerify);
    }

    private boolean isStudent(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolePermissionByUserId(userId);
        if (sysRoles.isEmpty()) return false;

        String roleName = sysRoles.get(0).getRoleKey();
        if ("student".equals(roleName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int setBenefitVerify(VerifyHistory verifyHistory) {
        Long userId = SecurityUtils.getUserId();
        Long verifyUnit = Long.valueOf(getVerifyUnit(userId));
        int curStage = getCurStage(userId).intValue();
        int verifyAction = verifyHistory.getVerifyAction().intValue();

        if (curStage == -1) return 0;

        Long nextStage = Long.valueOf(nextStageArr[curStage][verifyAction]);
        Long nextState = Long.valueOf(nextStateArr[curStage][verifyAction]);


        verifyHistory.setVerifyPersonId(userId);
        verifyHistory.setApplyType(new Long(2));
        verifyHistory.setVerifyUnit(verifyUnit);

        verifyHistoryMapper.insertVerifyHistory(verifyHistory);
        benefitVerifyMapper.setApplyState(verifyHistory.getApplyId(),
                Long.valueOf(nextStage), Long.valueOf(nextState));


        return 1;
    }

    private Long getVerifyUnit(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolePermissionByUserId(userId);
        if (sysRoles.isEmpty()) return null;

        String roleName = sysRoles.get(0).getRoleKey();
        if ("counselor".equals(roleName)) {
            return Long.valueOf(0);
        } else if ("school".equals(roleName)) {
            return Long.valueOf(1);
        } else if ("university".equals(roleName)){
            return Long.valueOf(2);
        } else if ("student".equals(roleName)){
            return Long.valueOf(3);
        } else {
            return Long.valueOf(-1);
        }
    }


    private Long getCurStage(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolePermissionByUserId(userId);
        if (sysRoles.isEmpty()) return null;

        String roleName = sysRoles.get(0).getRoleKey();
        if ("counselor".equals(roleName)) {
            return Long.valueOf(0);
        } else if ("school".equals(roleName)) {
            return Long.valueOf(1);
        } else if ("university".equals(roleName)){
            return Long.valueOf(2);
        } else {
            return Long.valueOf(-1);
        }
    }
}
