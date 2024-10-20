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

    public DefaultAuthorService(final AuthorRepository repository, final AuthorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AuthorEntity> postAuthor(final AuthorDto dto) {

        final var isExisting = repository.existsByFirstNameAndLastName(dto.firstName(), dto.lastName());
        if(!isExisting) {
            final var entity = mapper.toEntity(dto);
            return Optional.of(repository.save(entity));
        }
        return Optional.empty();
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<AuthorEntity> getAuthorByNames(final String firstName, final String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Optional<AuthorEntity> getAuthorById(final long id) {
        return repository.findById(id);
    }

    @Override
    public AuthorEntity createIfAbsent(final AuthorDto dto) {
        final var existingAuthor = repository.findByFirstNameAndLastName(dto.firstName(), dto.lastName());
        if(existingAuthor.isEmpty()) {
            final var entity = mapper.toEntity(dto);
            return repository.save(entity);
        }
        return existingAuthor.get();
    }

}
