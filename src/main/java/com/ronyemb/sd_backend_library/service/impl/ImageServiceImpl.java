package com.ronyemb.sd_backend_library.service.impl;

import com.ronyemb.sd_backend_library.exception.ImageProcessingException;
import com.ronyemb.sd_backend_library.model.Image;
import com.ronyemb.sd_backend_library.repository.ImageRepository;
import com.ronyemb.sd_backend_library.service.CloudinaryService;
import com.ronyemb.sd_backend_library.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Image uploadImage(MultipartFile file) throws ImageProcessingException {
        try {
            Map uploadResult = cloudinaryService.upload(file);

            // Verificar que la respuesta contiene las claves necesarias
            if (uploadResult.containsKey("url") && uploadResult.containsKey("public_id")) {
                String imageUrl = (String) uploadResult.get("url");
                String imageId = (String) uploadResult.get("public_id");

                // Crear la imagen y guardarla
                Image image = new Image(file.getOriginalFilename(), imageUrl, imageId);
                return imageRepository.save(image);
            } else {
                throw new ImageProcessingException("Cloudinary upload result does not contain necessary information.");
            }
        } catch (IOException e) {
            throw new ImageProcessingException("Error uploading image to Cloudinary", e);
        }
    }

    @Override
    public void deleteImage(Image image) throws ImageProcessingException {
        try {
            cloudinaryService.delete(image.getImageId());
            imageRepository.deleteById(image.getId());
        } catch (IOException e) {
            throw new ImageProcessingException("Error deleting image from Cloudinary", e);
        }
    }

    @Override
    public Image getImageById(Long id) {
        // Buscar imagen por ID
        Optional<Image> image = imageRepository.findById(id);
        return image.orElseThrow(() -> new ImageProcessingException("Image not found with ID: " + id));
    }
}
