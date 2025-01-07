package com.ronyemb.sd_backend_library.service;


import com.ronyemb.sd_backend_library.dto.BookRequest;
import com.ronyemb.sd_backend_library.dto.BookResponse;
import com.ronyemb.sd_backend_library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);


}
