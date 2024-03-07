package ru.bezborodov.twitter.user.profile.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.usecase.UserProfileRegisterUseCase;
import ru.bezborodov.twitter.user.profile.mapper.UserProfileRegisterRequestToUserProfileMapper;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.profile.service.UserProfileService;
import ru.bezborodov.twitter.user.profile.web.model.UserProfileRegisterRequest;

@Component
public class UserProfileRegisterUseCaseFacade implements UserProfileRegisterUseCase {

    private final UserProfileService userProfileService;
    private final UserProfileRegisterRequestToUserProfileMapper mapper;

    public UserProfileRegisterUseCaseFacade(UserProfileService userProfileService, UserProfileRegisterRequestToUserProfileMapper mapper) {
        this.userProfileService = userProfileService;
        this.mapper = mapper;
    }

    @Override
    public void registerUserProfile(UserProfileRegisterRequest registerRequest) {
        UserProfile userProfile = this.mapper.map(registerRequest);
        this.userProfileService.createUserProfile(userProfile);
    }
}
