package com.royalrangers.repository;

import com.royalrangers.model.Region;
import com.royalrangers.model.Region;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findByCountryId(Long countryId);
    Region findByNameAndCountryId(String regionName, Long countryId);
}
