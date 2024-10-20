package com.demo.reactive.repository;

import com.demo.reactive.entity.AuthorEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends R2dbcRepository<AuthorEntity, Long> {
    Mono<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
