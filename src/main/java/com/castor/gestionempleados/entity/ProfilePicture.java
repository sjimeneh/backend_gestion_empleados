package com.castor.gestionempleados.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "profile_pictures")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String source;

    @Lob
    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;
}
