package com.ronglian.fssc.webapp.external.manager.ebs.view;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 用户部门分配信息
 * @ClassName VFssPerAssignmentManagerTest
 * @Date 2015年12月8日 下午4:33:23
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class VFssPerAssignmentManagerTest extends TestBase {
    @Autowired
    private VFssPerAssignmentManager manager;

    @Test
    public void synchronousData() throws Exception {
        manager.synchronousData();
    }
}
