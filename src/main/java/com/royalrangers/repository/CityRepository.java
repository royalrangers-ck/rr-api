package com.royalrangers.repository;

import com.royalrangers.model.City;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByCountryId(Long countryId);
    City findByNameAndCountryId(String cityName, Long countryId);
}
