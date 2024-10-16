package com.book.demo.service.author;

import com.book.demo.dto.AuthorDto;
import com.book.demo.entity.AuthorEntity;
import com.book.demo.mapper.AuthorMapper;
import com.book.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public DefaultAuthorService(AuthorRepository repository, AuthorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AuthorEntity postAuthor(AuthorDto dto) {
        final var entity = mapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<AuthorEntity> getAuthorByNames(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Optional<AuthorEntity> getAuthorById(long id) {
        return repository.findById(id);
    }

}
