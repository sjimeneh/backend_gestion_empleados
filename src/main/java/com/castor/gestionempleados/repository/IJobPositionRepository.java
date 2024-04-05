package com.castor.gestionempleados.repository;

import com.castor.gestionempleados.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobPositionRepository extends JpaRepository<JobPosition,Long> {
}
