package com.castor.gestionempleados.controller;

import com.castor.gestionempleados.entity.JobPosition;
import com.castor.gestionempleados.repository.IJobPositionRepository;
import com.castor.gestionempleados.service.interfaces.IJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/api/castor/v1/jobposition")
public class JobPositionController {
    @Autowired
    private IJobPositionService _iJobPositionService;

    @GetMapping("/")
    public ResponseEntity<List<JobPosition>> GetAllJobPositiion(){
        return ResponseEntity.status(HttpStatus.OK).body(_iJobPositionService.GetAllJobsPosition());
    }
}
