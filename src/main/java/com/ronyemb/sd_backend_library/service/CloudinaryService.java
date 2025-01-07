package com.ronyemb.sd_backend_library.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Service interface for handling file operations with Cloudinary.
 */
public interface CloudinaryService {

    /**
     * Uploads a file to Cloudinary.
     *
     * @param multipartFile The file to be uploaded.
     * @return A map containing information about the uploaded file.
     * @throws IOException If an error occurs during the upload process.
     */
    Map<String, Object> upload(MultipartFile multipartFile) throws IOException;

    /**
     * Deletes a file from Cloudinary.
     *
     * @param id The ID of the file to be deleted.
     * @return A map containing the result of the deletion operation.
     * @throws IOException If an error occurs during the deletion process.
     */
    Map<String, Object> delete(String id) throws IOException;
}
