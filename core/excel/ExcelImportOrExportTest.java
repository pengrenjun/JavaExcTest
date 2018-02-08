package com.ronglian.fssc.webapp.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @Description excel 导入导出测试
 * @ClassName ExcelImportOrExportTest
 * @Date 2015年12月1日 下午2:51:21
 * @Author limliu@deloitte.com.cn
 * @Copyright (c) All Rights Reserved, 2015.
 */
public class ExcelImportOrExportTest {

    private String addr = "/usr/test.xls";

    /**
     * @Method export
     * @Description 导出
     * @return void
     * @since 1.0.0
     */
    @Test
    public void testExcelExport() {
        // String title, String secondTitle, String sheetName
        ExportParams params = new ExportParams("2412312", "测试", "测试");
        // params.setAddIndex(true);
        List<TeacherEntity> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            TeacherEntity te = new TeacherEntity();
            te.setId("id_" + i);
            te.setName("name_" + i);
            list.add(te);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(params, TeacherEntity.class, list);
        OutputStream os = null;
        try {
            os = new FileOutputStream(addr);
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    @Test
    public void testImportExcel() {
        InputStream is = null;
        try {
            is = new FileInputStream(addr);
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setSheetNum(1);
            List<TeacherEntity> list = ExcelImportUtil.importExcel(is, TeacherEntity.class, params);
            System.out.println("size: " + list.size());
            for (TeacherEntity te : list) {
                System.out.println(String.format("{id: %s, name: %s}", te.getId(), te.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }

    }

    @Test
    public void exportTest() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId("11231");
        studentEntity.setName("撒旦法司法局");
        studentEntity.setBirthday(new Date());
        studentEntity.setRegistrationDate(new java.sql.Time(new Date().getTime()));
        studentEntity.setSex(1);
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        studentList.add(studentEntity);
        studentList.add(studentEntity);

        List<ClassName> list = new ArrayList<ClassName>();
        ClassName classes = new ClassName();
        classes.setName("班级1");
        classes.setArrA(studentList);
        classes.setArrB(studentList);
        list.add(classes);
        classes = new ClassName();
        classes.setName("班级2");
        classes.setArrA(studentList);
        classes.setArrB(studentList);
        list.add(classes);
        ExportParams params = new ExportParams();
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(params, ClassName.class, list);
            FileOutputStream fos = new FileOutputStream(addr);
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void importTest() {
        ImportParams params = new ImportParams();
        params.setHeadRows(2);
        long start = new Date().getTime();
        List<ClassName> list = ExcelImportUtil.importExcel(new File(addr), ClassName.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
        System.out.println(ReflectionToStringBuilder.toString(list.get(0).getArrA().get(0)));

    }
}
