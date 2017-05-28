package com.royalrangers.service;

import com.royalrangers.exception.TokenException;
import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private VerificationTokenRepository tokenRepository;


    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        return tokenRepository.save(verificationToken).getToken();
    }

    public VerificationToken getVerificationToken(String token) throws TokenException {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new TokenException(String.format("Verification token '%s' is invalid", token));
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw  new TokenException(String.format("Verification token '%s' is expired", token));
        }
        log.info(String.format("Verification token '%s' is confirmed", token));
        return verificationToken;
    }

    public static Date calculateTokenExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
