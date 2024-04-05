package com.castor.gestionempleados.repository;

import com.castor.gestionempleados.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
}
