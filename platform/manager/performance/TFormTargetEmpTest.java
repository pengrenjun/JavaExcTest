package com.ronglian.fssc.webapp.platform.manager.performance;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.deloitte.si.core.dao.GenericDao;
import com.ronglian.fssc.webapp.TestBase;
import com.ronglian.fssc.webapp.platform.domain.performance.TFormTargetEmp;
import com.ronglian.fssc.webapp.platform.utils.ObjectViewer;
import com.ronglian.fssc.webapp.platform.vo.excel.ImportTargetEmpVo;

@Rollback(value = true)
public class TFormTargetEmpTest extends TestBase {

    @Autowired
    private TFormTargetEmpManager manager;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    private GenericDao dao;

    @Test
    public void testJdbcTemplate(){
        String sql = "select 1 from dual ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(ObjectViewer.obj2String(list));
    }
    @Test
    public void testBatchSaveOrUpdate() {
        // List<TFormTargetEmp> datas = manager.findEntityByFilter(new FormSearchCondition());
        // System.out.println(datas.size() + "  ?????????????????");
        // manager.batchSaveOrUpdate(datas);
    }

    @Test
    public void testFindCount() {
        String sql = "select 1 from t_sys_user ";
        int count = dao.findSqlCountByFilter(sql);
        System.out.println(count + "  ================");
    }

    @Test
    public void testImportExcelData() throws Exception {
        File excelFile = new File("F:/员工业绩导入模板.xlsx");
        ImportParams params = new ImportParams();
        params.setKeyIndex(null);

        List<ImportTargetEmpVo> list = ExcelImportUtil.importExcel(new FileInputStream(excelFile), ImportTargetEmpVo.class, params);
        List<TFormTargetEmp> tempList = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<TFormTargetEmp>>() {
        });

        for (TFormTargetEmp temp : tempList) {
            System.out.println(ObjectViewer.obj2String(temp.getStatus()));
        }
    }
}
