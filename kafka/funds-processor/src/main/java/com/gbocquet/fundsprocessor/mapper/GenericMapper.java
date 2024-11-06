package com.gbocquet.fundsprocessor.mapper;

import org.mapstruct.MappingTarget;

public interface GenericMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto, E entity);
}
