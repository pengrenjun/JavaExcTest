package com.ronglian.fssc.webapp.JavaAssist;

import com.deloitte.si.core.domain.GenericEntity;
import com.ronglian.fssc.webapp.Annotation.FieldMeta;

import java.io.Serializable;

@FieldMeta(name = "员工类",editable = true)
public class Emp extends GenericEntity implements Serializable {

    private String empNo;

    private String name;

    private double salary;

    public Emp(String empNo) {
        this.empNo = empNo;
    }

    public Emp(String empNo, String name, double salary) {
        this.empNo = empNo;
        this.name = name;
        this.salary = salary;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
