package com.castor.gestionempleados.config;

import com.castor.gestionempleados.entity.Enum.JobPositionType;
import com.castor.gestionempleados.entity.JobPosition;
import com.castor.gestionempleados.repository.IJobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner  {
    @Autowired
    private IJobPositionRepository _iJobPositionRepository;
    @Override
    public void run(String... args) throws Exception {
        // Validar que se lleno solo si la tabla no tiene registros
        if(_iJobPositionRepository.count() == 0){
            List<JobPosition> jobPositions = LoadJobProfileEmployee();
            _iJobPositionRepository.saveAll(jobPositions);
        }

    }

    private List<JobPosition> LoadJobProfileEmployee(){
        List<JobPosition> jobPositions = new ArrayList<>();
        jobPositions.add(JobPosition.builder().name(JobPositionType.SCRUM_MASTER).build());
        jobPositions.add(JobPosition.builder().name(JobPositionType.DESARROLLADOR).build());
        jobPositions.add(JobPosition.builder().name(JobPositionType.PO).build());
        jobPositions.add(JobPosition.builder().name(JobPositionType.QA).build());
        return jobPositions;
    }
}
