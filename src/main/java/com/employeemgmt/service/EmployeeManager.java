package com.employeemgmt.service;
 
import java.util.List;
 
import com.employeemgmt.entity.EmployeeEntity;
 
public interface EmployeeManager {
    public EmployeeEntity getEmployee(Integer employeeId);
	public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
    
}

