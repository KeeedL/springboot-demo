package com.book.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookDto(Long id, String title, List<AuthorDto> authors) {
}
