package com.neosoft.mongopoc.service;

import com.neosoft.mongopoc.model.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDto> getAllEmployee();

    String create(EmployeeDto employeeDto);

    String update(EmployeeDto employeeDto);

    EmployeeDto getByEmployeeId(String id);
}
