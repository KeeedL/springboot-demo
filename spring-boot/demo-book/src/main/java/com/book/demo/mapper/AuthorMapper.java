package com.book.demo.mapper;

import com.book.demo.dto.AuthorDto;
import com.book.demo.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(AuthorEntity entity);
    AuthorEntity toEntity(AuthorDto dto);
}
