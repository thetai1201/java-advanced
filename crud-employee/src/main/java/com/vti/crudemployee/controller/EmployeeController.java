package com.vti.crudemployee.controller;

import com.vti.crudemployee.model.Employee;
import com.vti.crudemployee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    //Test
    @GetMapping("/")
    public String test (){
        return "timoday";
    }

    //API add employee
    //anocation them @Postmapping
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return  iEmployeeService.addEmployee(employee);
    }

    //API updateemployee
    @PutMapping("/update")
    public Employee updateEmployee(@RequestParam ("id") long id,@RequestBody Employee employee){
        return iEmployeeService.updateEmployee(id,employee);
    }
    //API delete employee
    @DeleteMapping("/delete/{id}")
    public boolean deleteEmployee(@PathVariable("id") long id ){
        return iEmployeeService.deleteEmployee(id);
    }
    //API lay danh sach
    @GetMapping("/list")
    public List<Employee> getAllEmployee(){
        return iEmployeeService.getAllEmployee();
    }
}
