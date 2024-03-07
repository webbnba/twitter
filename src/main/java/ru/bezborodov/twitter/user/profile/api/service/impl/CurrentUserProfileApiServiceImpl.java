package ru.bezborodov.twitter.user.profile.api.service.impl;

import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.security.api.model.CurrentUserApiModel;
import ru.bezborodov.twitter.security.api.service.IdentityApiService;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.profile.service.UserProfileService;

@Service
public class CurrentUserProfileApiServiceImpl implements CurrentUserProfileApiService {

    private final IdentityApiService identityApiService;
    private final UserProfileService userProfileService;

    public CurrentUserProfileApiServiceImpl(IdentityApiService identityApiService,
                                            UserProfileService userProfileService) {
        this.identityApiService = identityApiService;
        this.userProfileService = userProfileService;
    }

    @Override
    public UserProfile currentUserProfile() {
        CurrentUserApiModel currentUserApiModel = this.identityApiService.currentUserAccount()
                .orElseThrow(() -> new TwitterException("Пользовател должен быть авторизован в системе"));

        return this.userProfileService.findUserProfileByIdRequired(currentUserApiModel.userAccountId());

    }
}
