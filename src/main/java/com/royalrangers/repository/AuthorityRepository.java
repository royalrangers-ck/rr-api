package com.royalrangers.repository;

import com.royalrangers.model.Authority;
import com.royalrangers.enums.AuthorityName;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findByName(AuthorityName authority);

}
