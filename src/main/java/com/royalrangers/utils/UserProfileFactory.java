package com.royalrangers.utils;

import com.royalrangers.dto.user.UserProfile;
import com.royalrangers.model.User;

public class UserProfileFactory {

    public static UserProfile create(User user) {

        UserProfile userProfile = new UserProfile();

        userProfile.setCreateDate(user.getCreateDate());
        userProfile.setUpdateDate(user.getUpdateDate());
        userProfile.setEmail(user.getEmail());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setGender(user.getGender());
        userProfile.setTelephoneNumber(user.getTelephoneNumber());
        userProfile.setBirthDate(user.getBirthDate());
        userProfile.setUserAgeGroup(user.getUserAgeGroup());
        userProfile.setUserRank(user.getUserRank());
        userProfile.setAvatarUrl(user.getAvatarUrl());
        userProfile.setCountryId(user.getCountry().getId());
        userProfile.setCityId(user.getCity().getId());
        userProfile.setGroupId(user.getGroup().getId());
        userProfile.setPlatoonId(user.getPlatoon().getId());
        userProfile.setSectionId(user.getSection().getId());

        return userProfile;
    }
}
