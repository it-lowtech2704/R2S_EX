package com.springboot.mapper;

import com.springboot.dto.EmployeeDTO;
import com.springboot.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstname());
        dto.setLastname(employee.getLastname());
        dto.setBirthdate(employee.getBirthdate());

        return dto;
    }


    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setBirthdate(dto.getBirthdate());

        return employee;
    }
}
