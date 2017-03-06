package com.royalrangers.configuration;

import com.royalrangers.model.*;
import com.royalrangers.security.repository.AuthorityRepository;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class Bootstrap {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void init() {
        initAuthorities();
        initUsers();
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUsername("username " + element);
            user.setEmail("user" + element + "@mail.test");
            user.setPassword(encoder.encode("password" + element));
            user.setFirstName("first " + element);
            user.setLastName("last " + element);
            user.setGender("gender " + element);
            user.setEnabled(true);
            user.setLastPasswordResetDate(new Date());
            user.setGroup(new Group(user.getCity(), "group " + element));
            user.setPlatoon(new Platoon(user.getGroup(), "platoon " + element));
            user.setSection(new Section(user.getPlatoon(), "section " + element));
            Authority userAuthority = authorityRepository.findOne(1L);
            Authority adminAuthority = authorityRepository.findOne(2L);
            Authority superAdminAuthority = authorityRepository.findOne(3L);
            switch (element) {
                case 1:
                    user.setAuthorities(new HashSet<Authority>() {{ add(userAuthority); }});
                    user.setCountry(new Country("Ukraine"));
                    user.setCity(new City(user.getCountry(), "Cherkasy"));
                    break;
                case 2:
                    user.setAuthorities(new HashSet<Authority>() {{
                        add(userAuthority);
                        add(adminAuthority);
                    }});
                    user.setCountry(new Country("USA"));
                    user.setCity(new City(user.getCountry(), "Miami"));
                    break;
                case 3:
                    user.setAuthorities(new HashSet<Authority>() {{
                        add(userAuthority);
                        add(adminAuthority);
                        add(superAdminAuthority);
                    }});
                    user.setCountry(new Country("Canada"));
                    user.setCity(new City(user.getCountry(), "Montreal"));
                    break;
            }
            users.add(user);
        });
        userRepository.save(users);
    }

    private void initAuthorities() {
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthorityName.ROLE_USER);
        authorityRepository.save(userAuthority);

        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthorityName.ROLE_ADMIN);
        authorityRepository.save(adminAuthority);

        Authority superAdminAuthority = new Authority();
        superAdminAuthority.setName(AuthorityName.ROLE_SUPER_ADMIN);
        authorityRepository.save(superAdminAuthority);
        authorityRepository.save(superAdminAuthority);
    }

}
