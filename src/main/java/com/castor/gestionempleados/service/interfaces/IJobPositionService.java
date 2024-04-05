package com.castor.gestionempleados.service.interfaces;

import com.castor.gestionempleados.entity.JobPosition;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IJobPositionService {
    JobPosition GetJobPositionById(Long id);
    List<JobPosition> GetAllJobsPosition();

}
