package com.neosoft.mongopoc.model.dto;


import com.neosoft.mongopoc.model.EmployeeState;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoConverter {

    public  EmployeeState convertDtoToBean(EmployeeDto employeedto)
    {
        EmployeeState employeeState = new EmployeeState();
        BeanUtils.copyProperties(employeedto, employeeState);

        return employeeState;
    }


    public  EmployeeDto convertBeanToDto(EmployeeState employee)
    {
        EmployeeDto employeeDto=new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;

    }

    public  List<EmployeeDto> convertListOfBeansToDto(List<EmployeeState> employeeList){
        final List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
        employeeList.stream().forEach(x->{
            employeeDtoList.add(this.convertBeanToDto(x));
        });

        return employeeDtoList;
    }
}
