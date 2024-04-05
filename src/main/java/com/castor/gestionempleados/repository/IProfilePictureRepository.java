package com.castor.gestionempleados.repository;

import com.castor.gestionempleados.entity.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {
    Optional<ProfilePicture> getBySource(String source);
}
