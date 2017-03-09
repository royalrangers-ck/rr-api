package com.royalrangers.repository;

import com.royalrangers.model.Subscribe;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SubscribeRepository extends CrudRepository<Subscribe, Long> {

}
