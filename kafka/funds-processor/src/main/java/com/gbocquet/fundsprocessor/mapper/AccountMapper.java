package com.gbocquet.fundsprocessor.mapper;

import com.gbocquet.fundsprocessor.dto.AccountDto;
import com.gbocquet.fundsprocessor.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<AccountDto, AccountEntity> {
    @Override
    AccountDto toDto(AccountEntity entity);

    @Override
    AccountEntity toEntity(AccountDto dto, @MappingTarget AccountEntity entity);
}
