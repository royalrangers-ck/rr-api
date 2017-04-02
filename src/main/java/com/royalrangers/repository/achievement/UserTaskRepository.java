package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findByUserId(Long id);
}
