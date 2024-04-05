package com.castor.gestionempleados.service.impl;

import com.castor.gestionempleados.entity.Employee;
import com.castor.gestionempleados.entity.JobPosition;
import com.castor.gestionempleados.repository.IEmployeeRepository;
import com.castor.gestionempleados.repository.IJobPositionRepository;
import com.castor.gestionempleados.repository.IProfilePictureRepository;
import com.castor.gestionempleados.service.interfaces.IEmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    IEmployeeRepository _iEmployeeRepository;
    @Autowired
    IProfilePictureRepository _iProfilePictureRepository;

    @Autowired
    private IJobPositionRepository jobPositionRepository;

    @Override
    public Employee GetByIdEmployee(Long id) {
        return _iEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> GetAllEmployees() {
        return _iEmployeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee SaveEmployee(Employee employee) throws SQLIntegrityConstraintViolationException {
        // Verificar si la JobPosition ya está persistida
        if (employee.getJobPosition() != null && employee.getJobPosition().getId() != null) {
            // La JobPosition no está persistida, por lo tanto, persistirla primero
            JobPosition jobPosition = jobPositionRepository.findById(employee.getJobPosition().getId()).orElse(null);
            if(jobPosition == null){
                throw new SQLIntegrityConstraintViolationException("El Cargo con ID = "+employee.getJobPosition().getId()+" No existe");
            }
            employee.setJobPosition(jobPosition);
        }
        return _iEmployeeRepository.save(employee);
    }

    @Override
    public Employee UpdateEmployee(Long id, Employee employee) {
        if(_iEmployeeRepository.existsById(id)){
            employee.setId(id);
            return _iEmployeeRepository.save(employee);
        }else{
            throw new EntityNotFoundException("El Usuario con ID = "+id+" No existe");
        }
    }

    @Override
    public void DeleteEmployeeById(Long id) {
        Employee employee = _iEmployeeRepository.findById(id).orElse(null);
        Long idPicture = 0L;

        if(employee != null){
            if(employee.getProfilePicture() != null) {
                idPicture = employee.getProfilePicture().getId();
            }
            _iEmployeeRepository.deleteById(id);
            if(idPicture != 0L){
                _iProfilePictureRepository.deleteById(idPicture);
            }
        }else{
            throw new RuntimeException("El usuario No existe");
        }
    }
}
