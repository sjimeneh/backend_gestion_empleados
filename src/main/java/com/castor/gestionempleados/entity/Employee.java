package com.castor.gestionempleados.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
@Valid
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 15,fraction = 0,message = "El campo DNI no tiene formato númerico")
    @Column(length = 20,unique = true,nullable = false)
    private String dni;

    @Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s]+$",message = "El Nombre no tiene un formato Alfanumerico")
    @Column(length = 100,nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateEntry;


    @ManyToOne()
    @JoinColumn(name = "jobposition_id")
    private JobPosition jobPosition;

    @OneToOne()
    @JoinColumn(name = "profilepicture_id")
    private ProfilePicture profilePicture;

}
