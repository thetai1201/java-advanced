package com.vti.crudemployee.service;

import com.vti.crudemployee.model.Employee;
import com.vti.crudemployee.reponsitory.EmployeeReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeReponsitory employeeReponsitory;

    @Override
    public Employee addEmployee(Employee employee) {
        if(employee!=null){
            return employeeReponsitory.save(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        if(employee!=null){
            Employee employee1 = employeeReponsitory.getById(id);
            if(employee1!=null){
                employee1.setName(employee.getName());
                employee1.setAddress(employee.getAddress());

                return employeeReponsitory.save(employee1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(long id) {
        if(id >= 1){
            Employee employee = employeeReponsitory.getById(id);
            if(employee!=null){
                employeeReponsitory.delete(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeReponsitory.findAll();
    }

    @Override
    public Employee getOneEmployee(long id) {
        return employeeReponsitory.getById(id);
    }

}
