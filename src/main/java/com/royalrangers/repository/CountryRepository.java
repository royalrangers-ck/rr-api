package com.royalrangers.repository;

import com.royalrangers.model.Country;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByName(String countryName);
}
