package com.castor.gestionempleados.entity;

import com.castor.gestionempleados.entity.Enum.JobPositionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(length = 100,nullable = false)
    @Enumerated(EnumType.STRING)
    private JobPositionType name;
}
