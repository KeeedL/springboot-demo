package com.gbocquet.fundsprocessor.repository;

import com.gbocquet.fundsprocessor.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
}
