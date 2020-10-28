package com.neosoft.mongopoc.service;

import com.neosoft.mongopoc.model.EmployeeState;
import com.neosoft.mongopoc.model.dto.EmployeeDto;
import com.neosoft.mongopoc.model.dto.EmployeeDtoConverter;
import com.neosoft.mongopoc.repositroy.EmployeeRepositroy;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    EmployeeRepositroy repositroy;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeState> employeeStates = repositroy.findAll();
        List<EmployeeDto> employeeDtoList = this.filterEmpSal(employeeStates);
        return employeeDtoList;
    }

    @Override
    public String create(EmployeeDto employeeDto) {
        //EmployeeState employeeState = new EmployeeDtoConverter().convertDtoToBean(employeeDto);
        //repositroy.save(employeeState);

        EmployeeState employeeState = new EmployeeDtoConverter().convertDtoToBean(employeeDto);
        List<EmployeeState> employees = repositroy.findAll();

        //var studentFeedbackList=studentRepo!!.allStudents
      //  EmployeeState emp = repositroy.getById(employeeDto.getId());

        boolean flag= false;
        try {
            flag = this.createFilter(employees,employeeDto);
        } catch (Exception exception) {
            exception.printStackTrace();
            return exception.getMessage();
        }
        if(flag==true)
            repositroy.save(employeeState);
        return "Employee registration successful";
    }

    @Override
    public String update(EmployeeDto employeeDto) {
        EmployeeState employeeState = new EmployeeDtoConverter().convertDtoToBean(employeeDto);
        if (employeeDto.getEmpMobNo().equals(employeeState.getEmpMobNo())) {
            employeeState.setDept(employeeDto.getDept());
            employeeState.setEmpName(employeeDto.getEmpName());
        }
        repositroy.save(employeeState);
        return "Employee updated sucessfully\t\t" + employeeState.getId();
    }

    @Override
    public  EmployeeDto getByEmployeeId(String id) {
        EmployeeState employeeState = repositroy.getById(id);
        EmployeeDto employeeDto=null;
        if (employeeState != null) {
             employeeDto = new EmployeeDtoConverter().convertBeanToDto(employeeState);
        }
        return employeeDto;
    }

    public List<EmployeeDto> filterEmpSal(List<EmployeeState> employeeStates) {
        List<EmployeeState> empFilterList = employeeStates.stream().filter(x -> x.getSalary() > 20000).collect(Collectors.toList());
        return new EmployeeDtoConverter().convertListOfBeansToDto(empFilterList);
    }

    public Boolean createFilter(List<EmployeeState> employeeStates, EmployeeDto e)throws  Exception {
        boolean flag=true;
        if(flag) {
            if (employeeStates.stream().filter(x -> x.getEmpMobNo().equals(e.getEmpMobNo())).findAny().isPresent()) {
               throw new Exception("employee alredy register");
            }
        }
        return false;

    }
}
