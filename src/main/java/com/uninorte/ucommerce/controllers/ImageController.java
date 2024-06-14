package com.uninorte.ucommerce.controllers;

import com.uninorte.ucommerce.services.IImageService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor

public class ImageController {

    private final IImageService imageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") @NotNull(message = "File cannot be null") MultipartFile file,
                                     @RequestParam("idProduct") @NotBlank(message = "idProduct cannot be blank") String idProduct) {
        return imageService.saveImage(file, idProduct);
    }


    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        return imageService.getImage(fileName);
    }
}

