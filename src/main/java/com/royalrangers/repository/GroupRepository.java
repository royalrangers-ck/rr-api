package com.royalrangers.repository;

import com.royalrangers.model.Group;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {
    List <Group> findByCity(Long city_id);
}

