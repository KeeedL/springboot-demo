package com.book.demo.controller;

import com.book.demo.dto.AuthorDto;
import com.book.demo.mapper.AuthorMapper;
import com.book.demo.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.book.demo.constant.RestPathValue.AUTHOR_PATH;

@RestController
@RequestMapping(
        value = AUTHOR_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    public AuthorController(AuthorService service, AuthorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // TODO: add pagination
    @Operation(summary = "Get authors")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Return authors found")
    )
    @GetMapping
    ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(
                service.getAuthors().stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Post author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return author created"),
            @ApiResponse(responseCode = "400", description = "Bad request - param type,  validation...")
    })
    @PostMapping
    ResponseEntity<AuthorDto> postAuthor(@RequestBody AuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(service.postAuthor(dto))
        );
    }
}
