package com.employeemgmt.dao;
 
import java.util.List;
import com.employeemgmt.entity.EmployeeEntity;
 
public interface EmployeeDAO
{
    public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
    public EmployeeEntity getEmployee(Integer employeeId);
}