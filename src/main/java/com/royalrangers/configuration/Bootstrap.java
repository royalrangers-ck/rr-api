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
        initUsers();
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUsername("username" + element);
            user.setEmail("user" + element + "@mail.test");
            user.setPassword(encoder.encode("password" + element));
            user.setFirstName("first" + element);
            user.setLastName("last" + element);
            user.setEnabled(true);
            user.setLastPasswordResetDate(new Date());
            Authority authority = new Authority();
            authority.setUsers(new HashSet<User>() {{
                add(user);
            }});
            switch (element) {
                case 1:
                    authority.setName(AuthorityName.ROLE_USER);
                    user.setCountry(new Country("Ukraine"));
                    user.setCity(new City(user.getCountry(), "Cherkasy"));
                    user.setGroup(new Group(user.getCity(), "group1"));
                    user.setPlatoon(new Platoon(user.getGroup(), "platoon1"));
                    user.setSection(new Section(user.getPlatoon(), "section1"));
                    break;
                case 2:
                    authority.setName(AuthorityName.ROLE_ADMIN);
                    user.setCountry(new Country("USA"));
                    user.setCity(new City(user.getCountry(), "Miami"));
                    user.setGroup(new Group(user.getCity(), "group2"));
                    user.setPlatoon(new Platoon(user.getGroup(), "platoon2"));
                    user.setSection(new Section(user.getPlatoon(), "section2"));
                    break;
                case 3:
                    authority.setName(AuthorityName.ROLE_SUPER_ADMIN);
                    user.setCountry(new Country("Canada"));
                    user.setCity(new City(user.getCountry(), "Montreal"));
                    user.setGroup(new Group(user.getCity(), "group3"));
                    user.setPlatoon(new Platoon(user.getGroup(), "platoon3"));
                    user.setSection(new Section(user.getPlatoon(), "section3"));
                    break;
            }
            authorityRepository.save(authority);
            user.setAuthorities(new HashSet<Authority>() {{
                add(authority);
            }});
            users.add(user);
        });
        userRepository.save(users);
    }
}
