package com.vti.crudemployee.reponsitory;

import com.vti.crudemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReponsitory extends JpaRepository<Employee,Long> {

}
