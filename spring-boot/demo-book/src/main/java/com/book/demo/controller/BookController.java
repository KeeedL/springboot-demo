package com.book.demo.controller;

import com.book.demo.dto.BookDto;
import com.book.demo.mapper.BookMapper;
import com.book.demo.service.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.book.demo.constant.RestPathValue.BOOK_PATH;
import static com.book.demo.constant.RestPathValue.ID_PATH_PARAM;

@RestController
@RequestMapping(
        value = BOOK_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookService service, BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // TODO: add pagination
    @Operation(summary = "Get books")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Return books found")
    )
    @GetMapping
    ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(service.getBooks().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Put book - Update book title and handle add, remove or create authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return book updated"),
            @ApiResponse(responseCode = "400", description = "Bad request - param type,  validation...")
    })
    @PutMapping(ID_PATH_PARAM)
    ResponseEntity<BookDto> putBook(@PathVariable final long id, @RequestBody final BookDto dto) {
        return service.updateBook(id, dto)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Post book - Handle existing and new authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return book created"),
            @ApiResponse(responseCode = "400", description = "Bad request - param type,  validation...")
    })
    @PostMapping
    ResponseEntity<BookDto> postBook(@RequestBody final BookDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(service.postBook(dto)));
    }

    @Operation(summary = "Get book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return book found"),
            @ApiResponse(responseCode = "400", description = "Bad request - param type...")
    })
    @GetMapping(ID_PATH_PARAM)
    ResponseEntity<BookDto> getBook(@PathVariable final long id) {
        return service.getBookById(id)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Delete book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Return book found"),
            @ApiResponse(responseCode = "400", description = "Bad request - param type...")
    })
    @DeleteMapping(ID_PATH_PARAM)
    ResponseEntity<BookDto> deleteBook(@PathVariable final long id) {
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
