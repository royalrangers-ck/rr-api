package com.royalrangers.configuration;

import com.royalrangers.model.Authority;
import com.royalrangers.model.AuthorityName;
import com.royalrangers.model.User;
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
            authority.setUsers(new HashSet<User>() {{ add(user); }});
            switch (element) {
                case 1:
                    authority.setName(AuthorityName.ROLE_USER);
                    break;
                case 2:
                    authority.setName(AuthorityName.ROLE_ADMIN);
                    break;
                case 3:
                    authority.setName(AuthorityName.ROLE_SUPER_ADMIN);
                    break;
            }
            authorityRepository.save(authority);
            user.setAuthorities(new HashSet<Authority>(){{ add(authority); }});
            users.add(user);
        });
        userRepository.save(users);
    }

}
