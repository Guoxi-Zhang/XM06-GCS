package com.gcs.verify.service.impl;

import com.gcs.common.core.domain.entity.SysRole;
import com.gcs.common.utils.SecurityUtils;
import com.gcs.system.mapper.SysRoleMapper;
import com.gcs.verify.domain.PackVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.mapper.PackVerifyMapper;
import com.gcs.verify.mapper.VerifyHistoryMapper;
import com.gcs.verify.service.IPackVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PackVerifyServiceImpl implements IPackVerifyService {

    @Autowired
    PackVerifyMapper packVerifyMapper;

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
    public List<PackVerify> selectPackVerifyList(PackVerify packVerify) {

        Long userId = SecurityUtils.getUserId();
        Long verifyUnit = Long.valueOf(getVerifyUnit(userId));
        Map<String, String> ancestors = verifyHistoryMapper.selectAncestorsByUserId(userId);

        if (verifyUnit.equals(0L)) {
            packVerify.setSchool(ancestors.get("fafa"));
            packVerify.setGrade(ancestors.get("fa"));
        } else if (verifyUnit.equals(1L)) {
            packVerify.setSchool(ancestors.get("fa"));
        } else if (verifyUnit.equals(3L)) {
            packVerify.setStudentId(userId);
        }
        return packVerifyMapper.selectPackVerifyList(packVerify);
    }

    @Override
    public PackVerify selectVerifyListByApplyId(Long applyId) {
        return packVerifyMapper.selectPackVerifyByApplyId(applyId);
    }

    @Override
    public int setPackVerify(VerifyHistory verifyHistory) {
        Long userId = SecurityUtils.getUserId();
        Long verifyUnit = Long.valueOf(getVerifyUnit(userId));
        int curStage = getCurStage(userId).intValue();
        int verifyAction = verifyHistory.getVerifyAction().intValue();

        if (curStage == -1) return 0;

        Long nextStage = Long.valueOf(nextStageArr[curStage][verifyAction]);
        Long nextState = Long.valueOf(nextStateArr[curStage][verifyAction]);


        verifyHistory.setVerifyPersonId(userId);
        verifyHistory.setApplyType(new Long(1));
        verifyHistory.setVerifyUnit(verifyUnit);

        verifyHistoryMapper.insertVerifyHistory(verifyHistory);
        packVerifyMapper.setApplyState(verifyHistory.getApplyId(),
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
