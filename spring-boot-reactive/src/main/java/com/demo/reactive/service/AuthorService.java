package com.demo.reactive.service;

import com.demo.reactive.entity.AuthorEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Mono<AuthorEntity> postAuthor(AuthorEntity authorEntity);
    Flux<AuthorEntity> getAuthors();
    Mono<AuthorEntity> getAuthor(long id);
    Mono<AuthorEntity> updateAuthor(long id, AuthorEntity authorInput);
    Mono<Boolean> deleteAuthor(long id);
}
