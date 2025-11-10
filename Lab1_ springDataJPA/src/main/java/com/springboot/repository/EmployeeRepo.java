package com.springboot.repository;

import com.springboot.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends org.springframework.data.repository.CrudRepository<Employee, Integer> {

}
