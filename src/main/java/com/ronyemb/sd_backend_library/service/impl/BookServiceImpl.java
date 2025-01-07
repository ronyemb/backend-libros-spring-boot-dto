package com.ronyemb.sd_backend_library.service.impl;

import com.ronyemb.sd_backend_library.exception.ImageProcessingException;
import com.ronyemb.sd_backend_library.model.Image;
import com.ronyemb.sd_backend_library.service.BookService;
import com.ronyemb.sd_backend_library.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ronyemb.sd_backend_library.repository.BookRepository;
import com.ronyemb.sd_backend_library.model.Book;
import com.ronyemb.sd_backend_library.dto.BookRequest;
import com.ronyemb.sd_backend_library.dto.BookResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        book.setId(id);  // Actualiza el ID del libro
        return bookRepository.save(book);  // Actualiza el libro
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBookImage(MultipartFile file, Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        try {
            // Eliminar imagen anterior si existe
            if (book.getImage() != null) {
                imageService.deleteImage(book.getImage());
            }

            // Subir nueva imagen
            Image newImage = imageService.uploadImage(file);

            // Asignar nueva imagen al libro
            book.setImage(newImage);

            // Guardar el libro actualizado
            return bookRepository.save(book);

        } catch (ImageProcessingException e) {
            // Manejar excepciones específicas de procesamiento de imágenes
            throw new RuntimeException("Error processing image: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura de cualquier otra excepción no esperada
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }



}
