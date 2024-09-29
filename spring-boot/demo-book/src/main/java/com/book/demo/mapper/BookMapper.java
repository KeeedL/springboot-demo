package com.book.demo.mapper;

import com.book.demo.dto.BookDto;
import com.book.demo.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "id", target = "id")
    BookDto toDto(BookEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "authors", ignore = true)
    void toEntity(BookDto dto, @MappingTarget BookEntity entity);
}
