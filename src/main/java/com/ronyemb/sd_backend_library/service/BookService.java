package com.ronyemb.sd_backend_library.service;


import com.ronyemb.sd_backend_library.dto.BookRequest;
import com.ronyemb.sd_backend_library.dto.BookResponse;
import com.ronyemb.sd_backend_library.exception.ImageProcessingException;
import com.ronyemb.sd_backend_library.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book updateBookImage(MultipartFile file, Book book) ;

}
