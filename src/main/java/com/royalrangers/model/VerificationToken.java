package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static com.royalrangers.service.TokenService.calculateTokenExpiryDate;

@Getter
@Setter
@Entity
public class VerificationToken extends BaseModel {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private String token;

    private Date expiryDate;

    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateTokenExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateTokenExpiryDate (EXPIRATION);
    }

    public VerificationToken() {
        super();
    }


}