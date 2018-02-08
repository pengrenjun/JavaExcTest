package com.ronglian.fssc.webapp.platform.manager.ledger;

import java.util.List;
import java.util.Map;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.si.bpm.core.domain.WfVo;
import com.deloitte.si.core.domain.datasource.FormSearchCondition;
import com.deloitte.si.framework.domain.security.TSysUser;
import com.deloitte.si.framework.domain.task.TFormStep;
import com.google.common.collect.Maps;
import com.ronglian.fssc.webapp.platform.domain.ledger.TFormGLApp;
import com.ronglian.fssc.webapp.platform.domain.workflow.TaskInfoVo;
import com.ronglian.fssc.webapp.platform.manager.task.MyWorkManager;

public class TFormGLAppManagerTest extends TestBase {
    @Autowired
    private TFormGLAppManager gLAppManager;
    @Autowired
    private MyWorkManager myWorkManager;
    
    @Test
    public void testFinishStep() throws Exception {
        TFormGLApp app = this.commonManager.get(TFormGLApp.class, "0CF954FDBA6A420AA5DCAAEC7109B48E");//根据Id获取总账信息
        FormSearchCondition fsc = new FormSearchCondition();
        Map<String, Object> vars = Maps.newTreeMap();
        vars.put("form_no_EQ", app.getGlFormNo());
        fsc.setVars(vars);
        List<TaskInfoVo> taskList = myWorkManager.findTaskInfos(fsc);
        TaskInfoVo taskInfo = taskList.get(0);
        String currentActivityId = taskInfo.getCurrentActivityId();
        WfVo wfVo = new WfVo();
        wfVo.setBusinessKey(app.getGlFormNo());
        wfVo.setProcInstId(taskInfo.getProcInstId());
        wfVo.setTaskId(taskInfo.getTaskId());
        wfVo.setCheckFlag("auditPass");
        wfVo.setDoFlag(0);
        if (currentActivityId != null && !"null".equals(currentActivityId)) {
            wfVo.setActivityInstName(currentActivityId);
        }
        wfVo.setActivityInstName("ReCheckGlFinanceActivity");
        wfVo.setProcDefEnName(taskInfo.getProcDefEnName());
        TSysUser user = this.commonManager.get(TSysUser.class, new Long("1003"));//根据Id获取总账信息
        TFormStep formStep = new TFormStep();
        formStep.setProcInstId(taskInfo.getProcInstId());
        gLAppManager.saveOrUpdateFinishStep(app, wfVo, formStep, user);
    }
}
