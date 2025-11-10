package com.springboot.service;

import com.springboot.dto.EmployeeDTO;
import com.springboot.entity.Employee;
import com.springboot.exception.NotFoundException;
import com.springboot.mapper.EmployeeMapper;
import com.springboot.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepo.findAll();
        return employees.stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    public EmployeeDTO getEmployeeById(int id) {
        Employee e = employeeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return EmployeeMapper.toDto(e);
    }

    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        existing.setFirstname(employeeDTO.getFirstname());
        existing.setLastname(employeeDTO.getLastname());
        existing.setBirthdate(employeeDTO.getBirthdate());

        Employee updated = employeeRepo.save(existing);
        return EmployeeMapper.toDto(updated);
    }

    public void deleteEmployee(int id) {
        if (!employeeRepo.existsById(id)) {
            throw new NotFoundException(id);
        }
        employeeRepo.deleteById(id);
    }
}
