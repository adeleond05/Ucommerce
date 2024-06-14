package com.uninorte.ucommerce.services.impl;

import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.repository.ProductRepository;
import com.uninorte.ucommerce.services.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private static final String UPLOAD_DIR = "./uploads/";
    static final String PRODUCT_NOT_FOUND_MESSAGE ="No se encuentra el producto con id ";

    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<Resource> getImage(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public String saveImage(MultipartFile file, String idProduct) {

        if (file.isEmpty()) {
            throw new CustomException("400", "Archivo vacío");
        }

        if (!productRepository.existsById(idProduct)) {
            throw new CustomException("404",PRODUCT_NOT_FOUND_MESSAGE + idProduct);
        }
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName = idProduct + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());
            return path.getFileName().toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500", "Failed to upload file");
        }
    }

    @Override
    public void deleteImage(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500", "Error al eliminar el archivo: " + fileName);
        }
    }
}
