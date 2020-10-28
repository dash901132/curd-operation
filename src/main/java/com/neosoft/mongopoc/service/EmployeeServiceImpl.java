package com.neosoft.mongopoc.service;

import com.neosoft.mongopoc.model.EmployeeState;
import com.neosoft.mongopoc.model.dto.EmployeeDto;
import com.neosoft.mongopoc.model.dto.EmployeeDtoConverter;
import com.neosoft.mongopoc.repositroy.EmployeeRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    EmployeeRepositroy repositroy;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeState> employeeStates = repositroy.findAll();
        List<EmployeeDto> employeeDtoList = new EmployeeDtoConverter().convertListOfBeansToDto(employeeStates);
        return employeeDtoList;
    }

    @Override
    public String create(EmployeeDto employeeDto) {
        EmployeeState employeeState = new EmployeeDtoConverter().convertDtoToBean(employeeDto);
        repositroy.save(employeeState);
        return "Employee inserted sucessfully\t\t" + employeeState.getId();
    }

    @Override
    public String update(EmployeeDto employeeDto) {
        EmployeeState employeeState = new EmployeeDtoConverter().convertDtoToBean(employeeDto);
        if (employeeDto.getEmpMobNo().equals(employeeState.getEmpMobNo())) {
            employeeState.setDept(employeeDto.getDept());
            employeeState.setEmpName(employeeDto.getEmpName());
        }
        repository.save(employeeState)
        return "Employee updated sucessfully\t\t" + employeeState.getId();
    }

    @Override
    public EmployeeDto getByEmployeeId(String id) {
        EmployeeState employeeState = repositroy.getById(id);
        EmployeeDto employeeDto = new EmployeeDtoConverter().convertBeanToDto(employeeState);

        return employeeDto;
    }
}
