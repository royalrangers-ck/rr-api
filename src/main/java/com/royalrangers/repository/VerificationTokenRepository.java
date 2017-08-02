package com.royalrangers.repository;

import com.royalrangers.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.stream.Stream;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    void deleteByExpiryDateLessThan(Date now);

}
