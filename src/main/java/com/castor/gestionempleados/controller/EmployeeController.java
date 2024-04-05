package com.castor.gestionempleados.controller;

import com.castor.gestionempleados.entity.Employee;
import com.castor.gestionempleados.response.ResponseMessage;
import com.castor.gestionempleados.service.interfaces.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/castor/v1/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService _iEmployeeService;
    @GetMapping("/{id}")
    public ResponseEntity<?> GetEmployeeById(@PathVariable Long id){
        Employee employee = _iEmployeeService.GetByIdEmployee(id);
        if(employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("El usuario no existe"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee);

    }
    @PostMapping("/create")
    public ResponseEntity<?> PostEmployee(@RequestBody @Valid Employee employee) throws SQLIntegrityConstraintViolationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(_iEmployeeService.SaveEmployee(employee));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> GetAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(_iEmployeeService.GetAllEmployees());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> DeleteEmployee(@PathVariable Long id){
        _iEmployeeService.DeleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El Empleado se ha eliminado exitosamente");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employee){
        Employee responseEmployee = _iEmployeeService.UpdateEmployee(id,employee);
        return ResponseEntity.status(HttpStatus.OK).body(responseEmployee);
    }
}
