package com.vti.crudemployee.service;

import com.vti.crudemployee.model.Employee;
import java.util.List;
public interface IEmployeeService {
    // Hàm thêm nhân viên
    public Employee addEmployee(Employee employee);

    //Chỉnh sửa thông tin nhân viên
    public Employee updateEmployee(long id ,Employee employee);

    //Hàm xóa nhân viên theo mã số trả về kiểu boolean
    public boolean deleteEmployee (long id );

    //Hàm lấy ra danh sách nhân viên
    public List<Employee> getAllEmployee();

    //Hàm lấy ra 1 nhân viên
    public Employee getOneEmployee(long id );


}
