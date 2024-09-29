package com.book.demo.service.book;

import com.book.demo.dto.BookDto;
import com.book.demo.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity postBook(BookDto dto);
    Optional<BookEntity> updateBook(long id, BookDto dto);
    void deleteBook(long id);
    List<BookEntity> getBooks();
    Optional<BookEntity> getBookById(long id);
}
