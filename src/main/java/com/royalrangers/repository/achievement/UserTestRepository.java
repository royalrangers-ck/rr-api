package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long>{
    List<UserTest> findByUserId(Long id);
    List<UserTest> findByUser_PlatoonId(Long id);
    List<UserTest> findAllByTest(Long testId);
}
