package com.royalrangers.repository;

import com.royalrangers.model.Platoon;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PlatoonRepository extends CrudRepository<Platoon, Long> {
    List <Platoon> findByGroup(Long group_id);
}
