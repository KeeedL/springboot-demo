package com.demo.reactive.controller;

import com.demo.reactive.entity.AuthorEntity;
import com.demo.reactive.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.demo.reactive.constant.RestPathValue.AUTHOR_PATH;
import static com.demo.reactive.constant.RestPathValue.ID_PATH_PARAM;

@RestController
@RequestMapping(
        value = AUTHOR_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Mono<ResponseEntity<AuthorEntity>> postAuthor(@RequestBody final AuthorEntity authorEntity) {
        return authorService.postAuthor(authorEntity)
                .map(createdAuthor -> ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).build()));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<AuthorEntity> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping(ID_PATH_PARAM)
    public Mono<ResponseEntity<AuthorEntity>> getAuthor(@PathVariable final long id) {
        return authorService.getAuthor(id)
                .map(authorFound -> ResponseEntity.status(HttpStatus.OK).body(authorFound))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PutMapping(ID_PATH_PARAM)
    public Mono<ResponseEntity<AuthorEntity>> putAuthor(@PathVariable final long id, @RequestBody final AuthorEntity authorEntity) {
        return authorService.updateAuthor(id, authorEntity)
                .map(updatedAuthor -> ResponseEntity.status(HttpStatus.OK).body(updatedAuthor))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @DeleteMapping(ID_PATH_PARAM)
    public Mono<ResponseEntity<Void>> deleteAuthor(@PathVariable final long id) {
        return authorService.deleteAuthor(id)
                .map(isDeleted -> {
                    if (isDeleted) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                    }
                });
    }
}
