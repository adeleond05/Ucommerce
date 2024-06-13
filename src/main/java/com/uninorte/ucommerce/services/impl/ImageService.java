package com.uninorte.ucommerce.services.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.uninorte.ucommerce.services.IImageService;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

  private final BlobServiceClient blobServiceClient;

  @Override
  public String saveImage(String base64Image) {
    // Decodificar la imagen base64
    byte[] imageBytes = Base64.getDecoder().decode(base64Image);

    // Generar un nombre único para la imagen
    String imageName = UUID.randomUUID() + ".jpg";

    // Subir la imagen al contenedor de Azure Blob Storage
    BlobClient blobClient = blobServiceClient.getBlobContainerClient("nombre-del-contenedor")
        .getBlobClient(imageName);
    blobClient.upload(new ByteArrayInputStream(imageBytes), imageBytes.length);

    // Obtener la URL pública de la imagen
    return blobClient.getBlobUrl();
  }

}
