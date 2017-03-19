package com.royalrangers.service;

import com.royalrangers.bean.UserProfile;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.utils.UserProfileFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserRepository userRepository;

    public UserProfile getUserDetailByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserProfileFactory.create(user);
    }

    public UserProfile getUserDetailById(Long id) throws DataAccessException {
        if(!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }
        User user = userRepository.findOne(id);
        return UserProfileFactory.create(user);
    }
}
