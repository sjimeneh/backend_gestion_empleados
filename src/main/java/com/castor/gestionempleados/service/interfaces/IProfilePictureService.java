package com.castor.gestionempleados.service.interfaces;


import com.castor.gestionempleados.entity.ProfilePicture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public interface IProfilePictureService{
    // Permite almacenar o cargar archivos a la base de datos
    ProfilePicture store(Long idUser,MultipartFile file) throws IOException;

    // Permite descargar archivos de nuestra base de datos
    Optional<ProfilePicture> getFileBySource(String source) throws FileNotFoundException;
}
