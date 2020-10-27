package com.neosoft.mongopoc.controller;


import com.neosoft.mongopoc.model.dto.EmployeeDto;
import com.neosoft.mongopoc.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;


    @GetMapping
    @ApiOperation(value = "Get All employee", notes = "URI to get all employee.", produces = "application/json", consumes = "application/json", response = List.class)
    public ResponseEntity<?> retriveAllemployees() {

        try {
            List<EmployeeDto> employeeDtoList = employeeService.getAllEmployee();
            return ResponseEntity.ok(employeeDtoList);

        } catch (Exception e) {
            return new ResponseEntity(e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get employee", notes = "URI to get employee By Id.", produces = "application/json", consumes = "application/json", response = EmployeeDto.class)
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") String id) {
        EmployeeDto employeeDto = employeeService.getByEmployeeId(id);
        return (ResponseEntity<?>) ResponseEntity.ok(employeeDto);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Register employee", notes = "URI to register/add employee.", produces = "application/json", consumes = "application/json", response = EmployeeDto.class)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {

        try {
            String employee = employeeService.create(employeeDto);
            return (ResponseEntity<?>) ResponseEntity.ok(employee);
        } catch (Exception exception) {

            return new ResponseEntity(exception,
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody EmployeeDto employeeDto) {
        try {
            String result = employeeService.update(employeeDto);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {

            return new ResponseEntity(exception,
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

