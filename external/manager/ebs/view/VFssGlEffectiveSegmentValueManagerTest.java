package com.ronglian.fssc.webapp.external.manager.ebs.view;

import java.util.List;

import com.ronglian.fssc.webapp.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 有效的段值
 * @ClassName VFssGlEffectiveSegmentValueManagerTest
 * @Date 2015年12月29日 下午4:29:32
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class VFssGlEffectiveSegmentValueManagerTest extends TestBase {
    @Autowired
    private VFssGlCostCenterSegmentValueManager costCenterManager;

    @Autowired
    private VFssGlProjectSegmentValueManager projectManager;

    @Autowired
    private VFssGlKeyAccountSegmentValueManager keyAccountManager;

    /**
     * @Method testCostCenterFindAll
     * @Description 有效的成本中心段值
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testCostCenterFindAll() {
        // 必传
        Object[] values = new Object[] { "101", "101", "101", "101" };
        List<?> list = costCenterManager.findAllByFilter(values);
        System.out.println(list.size());
    }

    /**
     * @Method testProjectFindAll
     * @Description 有效的项目段
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testProjectFindAll() {
        // 必传
        Object[] values = new Object[] { "101", "101", "101", "101" };
        List<?> list = projectManager.findAllByFilter(values);
        System.out.println(list.size());
    }

    /**
     * @Method testKeyAccountFindAll
     * @Description 有效的重点核算段
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testKeyAccountFindAll() {
        // 必传
        Object[] values = new Object[] { "101", "101", "101", "101" };
        List<?> list = keyAccountManager.findAllByFilter(values);
        System.out.println(list.size());
    }
}
