package ru.bezborodov.twitter.user.profile.service;

import ru.bezborodov.twitter.user.profile.model.UserProfile;

public interface UserProfileService {

    void createUserProfile(UserProfile userProfile);

    UserProfile findUserProfileByIdRequired(Long userProfileId);
}
