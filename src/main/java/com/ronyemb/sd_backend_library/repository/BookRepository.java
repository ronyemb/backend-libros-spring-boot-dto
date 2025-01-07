package com.ronyemb.sd_backend_library.repository;

import com.ronyemb.sd_backend_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
