package com.demo.reactive.service;

import com.demo.reactive.entity.AuthorEntity;
import com.demo.reactive.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    public DefaultAuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Mono<AuthorEntity> postAuthor(final AuthorEntity authorEntity) {
        return authorRepository.findByFirstNameAndLastName(authorEntity.getFirstName(), authorEntity.getLastName())
                .map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(isExistingAuthor -> {
                    if(isExistingAuthor.isEmpty()) {
                        return authorRepository.save(authorEntity);
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Flux<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Mono<AuthorEntity> getAuthor(final long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Mono<AuthorEntity> updateAuthor(final long id, final AuthorEntity authorInput) {
        return authorRepository.findById(id)
                .map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(authorFound -> {
                    if(authorFound.isPresent()) {
                        //TODO: Should use mapper (DTO <-> Entity)
                        authorFound.get().setFirstName(authorInput.getFirstName());
                        authorFound.get().setLastName(authorInput.getLastName());
                        return authorRepository.save(authorFound.get());
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Boolean> deleteAuthor(final long id) {
        return authorRepository.findById(id)
                .map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(authorFound -> {
                    if(authorFound.isPresent()) {
                        return authorRepository.deleteById(id)
                                .then(Mono.just(true));
                    }
                    return Mono.just(false);
                });
    }


}
