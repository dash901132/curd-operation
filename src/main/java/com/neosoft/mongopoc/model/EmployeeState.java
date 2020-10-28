package com.neosoft.mongopoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id= UUID.randomUUID().toString();
    protected String empName;
    protected String empLastName;
    protected String dept;
    protected long salary;
    protected String empMobNo;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getEmpMobNo() {
        return empMobNo;
    }

    public void setEmpMobNo(String empMobNo) {
        this.empMobNo = empMobNo;
    }



    public String getId() {
        return this.id;
    }

    public void setId(String empId) {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeState that = (EmployeeState) o;
        return salary == that.salary &&
                Objects.equals(id, that.id) &&
                Objects.equals(empName, that.empName) &&
                Objects.equals(empLastName, that.empLastName) &&
                Objects.equals(dept, that.dept) &&
                Objects.equals(empMobNo, that.empMobNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empName, empLastName, dept, salary, empMobNo);
    }
}
