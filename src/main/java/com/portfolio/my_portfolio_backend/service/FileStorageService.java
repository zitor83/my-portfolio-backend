package com.portfolio.my_portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}") // Añadir en el application.properties
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null) {
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = originalFilename.substring(dotIndex);
            }
        }

        // Generar un nombre único para el archivo del estilo UUID
        // Ejemplo: 123e4567-e89b-12d3-a456-426655440000.jpg
        String fileName = UUID.randomUUID().toString() + extension;

        //creacion de la ruta
        Path filePath = Paths.get(uploadDir, fileName).normalize();

        // copia del archivo al destino
        Files.copy(file.getInputStream(), filePath);

        //retorno de la url relativa
        return "/img/projects/" + fileName;

    }
    /**
     * Elimina el archivo físico del servidor basado en su URL relativa.
     */
    public void deleteFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return; // No hay imagen que borrar
        }

        try {
            // 1. Extraemos solo el nombre del archivo de la URL (ej: de "/img/projects/123.jpg" a "123.jpg")
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

            // 2. Construimos la ruta física absoluta en el servidor
            Path filePath = Paths.get(uploadDir, fileName).normalize();

            // 3. Borramos el archivo si existe
            Files.deleteIfExists(filePath);



        } catch (IOException e) {
            System.err.println("❌ Error al intentar eliminar la imagen física: " + imageUrl);
            e.printStackTrace();
        }
    }
}