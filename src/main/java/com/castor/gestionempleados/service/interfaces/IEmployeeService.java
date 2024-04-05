package com.castor.gestionempleados.service.interfaces;

import com.castor.gestionempleados.entity.Employee;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public interface IEmployeeService {
    Employee GetByIdEmployee(Long id);
    List<Employee> GetAllEmployees();
    Employee SaveEmployee( Employee employee) throws SQLIntegrityConstraintViolationException;
    Employee UpdateEmployee(Long id, Employee employee);
    void DeleteEmployeeById(Long id);
}
