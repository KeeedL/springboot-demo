package com.book.demo.service.author;

import com.book.demo.dto.AuthorDto;
import com.book.demo.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity postAuthor(AuthorDto dto);
    List<AuthorEntity> getAuthors();
    Optional<AuthorEntity> getAuthor(String firstName, String lastName);
}
