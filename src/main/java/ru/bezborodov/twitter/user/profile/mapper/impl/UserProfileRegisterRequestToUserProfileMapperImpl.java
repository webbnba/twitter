package ru.bezborodov.twitter.user.profile.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.TwitterException;
import ru.bezborodov.twitter.security.api.model.CurrentUserApiModel;
import ru.bezborodov.twitter.security.api.service.IdentityApiService;
import ru.bezborodov.twitter.user.profile.mapper.UserProfileRegisterRequestToUserProfileMapper;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.profile.web.model.UserProfileRegisterRequest;

@Component
public class UserProfileRegisterRequestToUserProfileMapperImpl implements UserProfileRegisterRequestToUserProfileMapper {

    private final IdentityApiService identityApiService;

    public UserProfileRegisterRequestToUserProfileMapperImpl(IdentityApiService identityApiService) {
        this.identityApiService = identityApiService;
    }

    @Override
    public UserProfile map(UserProfileRegisterRequest registerRequest) {

        CurrentUserApiModel currentUserApiModel = this.identityApiService.currentUserAccount()
                .orElseThrow(() ->
                        new TwitterException("Для создания профиля пользователь долженбыть авторизован в системе"));

        UserProfile userProfile = new UserProfile();
        userProfile.setId(currentUserApiModel.userAccountId());
        userProfile.setNickname(registerRequest.nickname());
        userProfile.setImageLink(registerRequest.imageLink());

        return userProfile;
    }
}
