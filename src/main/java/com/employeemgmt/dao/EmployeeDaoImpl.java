package com.employeemgmt.dao;
 
import java.util.List;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import com.employeemgmt.entity.EmployeeEntity;
 
@Repository
public class EmployeeDaoImpl implements EmployeeDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    public EmployeeEntity getEmployee(Integer employeeId){
    	return (EmployeeEntity) this.sessionFactory.getCurrentSession().get(EmployeeEntity.class, employeeId);
    }
    public void addEmployee(EmployeeEntity employee) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }
    @SuppressWarnings("unchecked")
    public List<EmployeeEntity> getAllEmployees() {
        return this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
    }
    public void deleteEmployee(Integer employeeId) {
        EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(
                EmployeeEntity.class, employeeId);
        if (null != employee) {
            this.sessionFactory.getCurrentSession().delete(employee);
        }
    }
}