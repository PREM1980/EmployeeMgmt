package com.employeemgmt.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.employeemgmt.dao.EmployeeDAO;
import com.employeemgmt.entity.EmployeeEntity;
 
@Service
public class EmployeeManagerImpl implements EmployeeManager
{
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Transactional
    public EmployeeEntity getEmployee(Integer employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }
    
    @Transactional
    public void addEmployee(EmployeeEntity employee) {
        employeeDAO.addEmployee(employee);
    }
    @Transactional
    public List<EmployeeEntity> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}