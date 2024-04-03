package com.castor.gestionempleados.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 15,fraction = 0)
    @Column(length = 20,unique = true,nullable = false)
    private String dni;

    @Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ]+$")
    @Column(length = 100,nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateEntry;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jonposition_id")
    @Column(nullable = false)
    private JobPosition jobPosition;

}
