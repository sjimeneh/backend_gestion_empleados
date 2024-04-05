package com.castor.gestionempleados.service.impl;

import com.castor.gestionempleados.entity.JobPosition;
import com.castor.gestionempleados.repository.IJobPositionRepository;
import com.castor.gestionempleados.service.interfaces.IJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionServiceImpl implements IJobPositionService {
    @Autowired
    private IJobPositionRepository _iJobPositionRepository;
    @Override
    public JobPosition GetJobPositionById(Long id) {
        return _iJobPositionRepository.findById(id).orElse(null);
    }

    @Override
    public List<JobPosition> GetAllJobsPosition() {
        return new ArrayList<>(_iJobPositionRepository.findAll());
    }
}
