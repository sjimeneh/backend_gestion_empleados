package com.castor.gestionempleados.entity;

import com.castor.gestionempleados.entity.Enum.JobPositionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "job_positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private JobPositionType name;

    @JsonIgnore
    @OneToMany(mappedBy = "jobPosition")
    private List<Employee> employees;

}
