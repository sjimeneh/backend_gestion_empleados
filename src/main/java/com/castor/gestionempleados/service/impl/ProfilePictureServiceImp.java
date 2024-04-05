package com.castor.gestionempleados.service.impl;

import com.castor.gestionempleados.entity.Employee;
import com.castor.gestionempleados.entity.ProfilePicture;
import com.castor.gestionempleados.repository.IEmployeeRepository;
import com.castor.gestionempleados.repository.IProfilePictureRepository;
import com.castor.gestionempleados.service.interfaces.IProfilePictureService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ProfilePictureServiceImp implements IProfilePictureService {
    @Autowired
    IProfilePictureRepository _iProfilePictureRepository;
    @Autowired
    IEmployeeRepository _iEmployeeRepository;

    @Override
    public ProfilePicture store(Long idUser,MultipartFile file) throws IOException {



        Employee employee = _iEmployeeRepository.findById(idUser).orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException("No se encontró un empleado con el ID proporcionado: " + idUser);
        }
        Long idCurrentPicture = 0L;
        if(employee.getProfilePicture() != null){
            employee.setProfilePicture(null);
            idCurrentPicture = employee.getProfilePicture().getId();
        }


        String originalFilename = file.getOriginalFilename(); // Obtén el nombre original del archivo
        String fileExtension = StringUtils.getFilenameExtension(originalFilename); // Obtén la extensión del archivo
        String finalName = employee.getDni()+"."+fileExtension;

        String pathSource = "http://localhost:8080/api/castor/v1/profilepicture/files/";

        ProfilePicture fileEntity = ProfilePicture.builder()
                .name(finalName)
                .type(file.getContentType())
                .data(file.getBytes())
                .source(pathSource + finalName)
                .build();

        ProfilePicture responseProfilePicture = _iProfilePictureRepository.save(fileEntity);

        employee.setProfilePicture(responseProfilePicture);
        _iEmployeeRepository.save(employee);
        if(idCurrentPicture != 0L){
            _iProfilePictureRepository.deleteById(idCurrentPicture);
        }
        return responseProfilePicture;
    }

    @Override
    public Optional<ProfilePicture> getFileBySource(String name) throws FileNotFoundException {
        List<ProfilePicture> profilePictures = _iProfilePictureRepository.findAll();
        ProfilePicture profilePicture = null;

        profilePicture = profilePictures.stream()
                .filter(foto -> foto.getName().equalsIgnoreCase(name))
                .findFirst() // Obtener la primera coincidencia
                .orElse(null); // Si no se encuentra ninguna coincidencia, devolver null

        if(profilePicture != null){
            return Optional.of(profilePicture);
        }
        throw new FileNotFoundException();
    }
}
