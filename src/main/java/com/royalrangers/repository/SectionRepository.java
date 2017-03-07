package com.royalrangers.repository;

import com.royalrangers.model.Section;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SectionRepository extends CrudRepository<Section, Long> {
    List<Section> findByPlatoon(Long platoon_id);

}
