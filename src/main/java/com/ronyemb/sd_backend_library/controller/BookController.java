package com.ronyemb.sd_backend_library.controller;

import com.ronyemb.sd_backend_library.exception.ImageProcessingException;
import com.ronyemb.sd_backend_library.model.Book;
import com.ronyemb.sd_backend_library.dto.BookRequest;
import com.ronyemb.sd_backend_library.dto.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ronyemb.sd_backend_library.service.BookService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
        }
        // Mapear a BookResponse para la respuesta
        List<BookResponse> bookResponses = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);  // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);  // El servicio devuelve un Optional<Book>
        if (book.isPresent()) {
            // Convertir Book a BookResponse (DTO)
            return new ResponseEntity<>(convertToBookResponse(book.get()), HttpStatus.OK);  // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found si no se encuentra
        }
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        try {
            // Convertir BookRequest (DTO) a Book (modelo)
            Book book = convertToBook(bookRequest);
            Book savedBook = bookService.saveBook(book);  // Guardar el libro
            // Convertir la entidad Book guardada a BookResponse (DTO)
            return new ResponseEntity<>(convertToBookResponse(savedBook), HttpStatus.CREATED);  // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // 400 Bad Request si ocurre un error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        if (bookService.getBookById(id).isPresent()) {  // Verificar si el libro existe
            // Convertir BookRequest (DTO) a Book (modelo)
            Book book = convertToBook(bookRequest);
            Book updatedBook = bookService.updateBook(id, book);  // Actualizar el libro
            // Convertir la entidad Book actualizada a BookResponse (DTO)
            return new ResponseEntity<>(convertToBookResponse(updatedBook), HttpStatus.OK);  // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found si no se encuentra
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.getBookById(id).isPresent()) {  // Verificar si el libro existe
            bookService.deleteBook(id);  // Eliminar el libro
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content si fue exitoso
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found si no se encuentra
        }
    }

    // Método para convertir BookRequest (DTO) a Book (modelo)
    private Book convertToBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPages(bookRequest.getPages());
        book.setPrice(bookRequest.getPrice());
        book.setImage(bookRequest.getImage());
        return book;
    }

    // Método para convertir Book (modelo) a BookResponse (DTO)
    private BookResponse convertToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPages(book.getPages());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setImage(book.getImage());
        return bookResponse;
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<BookResponse> updateBookImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            Optional<Book> optionalBook = bookService.getBookById(id); // Obtener el libro por id

            if (optionalBook.isEmpty()) {
                // Si el libro no existe, devolver 404 Not Found
                return ResponseEntity.notFound().build();
            }

            // Obtener el libro y actualizar la imagen
            Book updatedBook = bookService.updateBookImage(file, optionalBook.get());

            // Convertir el libro actualizado a un DTO BookResponse
            BookResponse bookResponse = convertToBookResponse(updatedBook);

            return ResponseEntity.ok(bookResponse);

        } catch (ImageProcessingException e) {
            // En caso de error al procesar la imagen, devolver un error 400 Bad Request
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // En caso de cualquier otro error, devolver un error 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
