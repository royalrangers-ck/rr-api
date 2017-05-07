package com.royalrangers.repository;

import com.royalrangers.model.TempUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TempUserRepository extends CrudRepository<TempUser, Long> {
    TempUser findByUserId(Long id);
}
