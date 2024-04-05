package com.castor.gestionempleados.controller;

import com.castor.gestionempleados.entity.ProfilePicture;
import com.castor.gestionempleados.response.ResponseMessage;
import com.castor.gestionempleados.service.interfaces.IProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/castor/v1/profilepicture")
public class ProfilePictureController {
    @Autowired
    private IProfilePictureService _iProfilePictureService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam Long idUser, @RequestParam("file") MultipartFile file) throws IOException {
        _iProfilePictureService.store(idUser,file);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Archivo subido exitosamente"));
    }

    @GetMapping("/files/{source}")
    public ResponseEntity<byte[]> getFile(@PathVariable String source) throws FileNotFoundException {

        Optional<ProfilePicture> optionalProfilePicture= _iProfilePictureService.getFileBySource(source);

        if(optionalProfilePicture.isPresent()){
            ProfilePicture profilePicture = optionalProfilePicture.get();
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_TYPE, profilePicture.getType())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePicture.getName()+"\"")
                    .body(profilePicture.getData());
        }else{
            throw new FileNotFoundException();
        }


    }
}
