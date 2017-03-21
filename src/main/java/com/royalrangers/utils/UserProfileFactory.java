package com.royalrangers.utils;

import com.royalrangers.bean.UserProfile;
import com.royalrangers.model.User;

public class UserProfileFactory {

    public static UserProfile create(User user) {

        UserProfile userProfile = new UserProfile();

        userProfile.setEmail(user.getEmail());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setGender(user.getGender());
        userProfile.setTelephoneNumber(user.getTelephoneNumber());
        userProfile.setBirthDate(user.getBirthDate());
        userProfile.setUserAgeGroup(user.getUserAgeGroup());
        userProfile.setCountry(user.getCountry().getName());
        userProfile.setCity(user.getCity().getName());
        userProfile.setGroup(user.getGroup().getName());
        userProfile.setPlatoon(user.getPlatoon().getName());
        userProfile.setSection(user.getSection().getName());

        return userProfile;
    }
}
