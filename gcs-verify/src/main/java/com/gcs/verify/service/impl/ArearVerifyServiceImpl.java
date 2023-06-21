package com.gcs.verify.service.impl;

import com.gcs.common.core.domain.entity.SysRole;
import com.gcs.common.utils.SecurityUtils;
import com.gcs.system.mapper.SysRoleMapper;
import com.gcs.verify.domain.ArearVerify;
import com.gcs.verify.domain.VerifyHistory;
import com.gcs.verify.mapper.ArearVerifyMapper;
import com.gcs.verify.mapper.VerifyHistoryMapper;
import com.gcs.verify.service.IArearVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArearVerifyServiceImpl implements IArearVerifyService {

    @Autowired
    private ArearVerifyMapper arearVerifyMapper;

    @Autowired
    private VerifyHistoryMapper verifyHistoryMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<ArearVerify> selectArearVerifyList(ArearVerify arearVerify) {
        return arearVerifyMapper.selectArearVerifyList(arearVerify);
    }

    @Override
    public ArearVerify selectArearVerifyByApplyId(Long applyId) {
        return arearVerifyMapper.selectArearVerifyByApplyId(applyId);
    }

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
    public int setArearVerify(VerifyHistory verifyHistory) {
        Long userId = SecurityUtils.getUserId();
        Long verifyUnit = Long.valueOf(getVerifyUnit(userId));
        int curStage = getCurStage(userId).intValue();
        int verifyAction = verifyHistory.getVerifyAction().intValue();

        if (curStage == -1) return 0;

        Long nextStage = Long.valueOf(nextStageArr[curStage][verifyAction]);
        Long nextState = Long.valueOf(nextStateArr[curStage][verifyAction]);



        verifyHistory.setVerifyPersonId(userId);
        verifyHistory.setApplyType(new Long(0));
        verifyHistory.setVerifyUnit(verifyUnit);

        verifyHistoryMapper.insertVerifyHistory(verifyHistory);
        arearVerifyMapper.setApplyState(verifyHistory.getApplyId(),
                Long.valueOf(nextStage), Long.valueOf(nextState));


        return 1;
    }

    @Override
    public int modifyBenefitApply(ArearVerify arearVerify) {
        Long userId = SecurityUtils.getUserId();
        if (arearVerify.getTableId() == null) return 0;
        if (isStudent(userId)) {
            arearVerify.setApplyState(0L);
            arearVerify.setNowStep(0L);
        }
        return arearVerifyMapper.modifyArearApply(arearVerify);
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
