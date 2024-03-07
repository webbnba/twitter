package ru.bezborodov.twitter.user.profile.api.service.impl;

import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.user.profile.api.service.UserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.profile.service.UserProfileService;

@Service
public class UserProfileApiServiceImpl implements UserProfileApiService {

    private final UserProfileService userProfileService;

    public UserProfileApiServiceImpl(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public UserProfile findUserProfileById(Long userProfileId) {
        return this.userProfileService.findUserProfileByIdRequired(userProfileId);
    }
}
