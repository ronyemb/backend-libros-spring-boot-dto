package com.ronyemb.sd_backend_library.service;

import com.ronyemb.sd_backend_library.exception.ImageProcessingException;
import com.ronyemb.sd_backend_library.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws ImageProcessingException;
    void deleteImage(Image image) throws ImageProcessingException;
    Image getImageById(Long id);
}
