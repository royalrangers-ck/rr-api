package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.Test;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepositoryPaged extends PagingAndSortingRepository<Test, Long> {
}
