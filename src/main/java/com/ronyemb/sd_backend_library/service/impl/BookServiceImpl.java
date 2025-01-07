package com.ronyemb.sd_backend_library.service.impl;

import com.ronyemb.sd_backend_library.service.BookService;
import com.ronyemb.sd_backend_library.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ronyemb.sd_backend_library.repository.BookRepository;
import com.ronyemb.sd_backend_library.model.Book;
import com.ronyemb.sd_backend_library.dto.BookRequest;
import com.ronyemb.sd_backend_library.dto.BookResponse;

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

}
