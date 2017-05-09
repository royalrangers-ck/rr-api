package com.royalrangers.repository;

import com.royalrangers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findAllByPlatoonId(Long platoonId);

    List<User> findAllByConfirmedTrueAndApprovedFalseAndPlatoonId(Long id);

    List<User> findUsersByApprovedTrueAndPlatoon_Id(Long id);

    List<User> findAllByConfirmedTrueAndApprovedFalse();
}
