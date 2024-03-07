package ru.bezborodov.twitter.user.profile.api.service;

import ru.bezborodov.twitter.user.profile.model.UserProfile;

public interface UserProfileApiService {
    UserProfile findUserProfileById(Long userProfileId);
}
