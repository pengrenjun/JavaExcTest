package com.ronglian.fssc.webapp.platform.manager.performance;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import com.deloitte.si.core.dao.GenericDao;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.utils.ObjectViewer;

@Rollback(value = true)
public class TFormPerformanceTest extends TestBase {

    @Autowired
    protected TFormPerformanceEmpManager manager;
    @Autowired
    protected GenericDao dao;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Test
    public void queryForList() {
        String sql = "select * from T_FORM_PERFORMANCE_EMP where status=?";
        // List<TFormPerformanceEmp> list = jdbcTemplate.queryForList(sql, TFormPerformanceEmp.class, "1");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, "1");
        System.out.println(ObjectViewer.obj2String(list));
    }

    @Rollback(value = false)
    @Test
    public void summaryByDepartTest() {
        manager.summaryByDepart("", "201503", "201604");
    }

}
