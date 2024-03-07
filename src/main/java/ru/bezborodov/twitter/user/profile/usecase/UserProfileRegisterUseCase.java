package ru.bezborodov.twitter.user.profile.usecase;

import ru.bezborodov.twitter.user.profile.web.model.UserProfileRegisterRequest;

public interface UserProfileRegisterUseCase {
    void registerUserProfile(UserProfileRegisterRequest registerRequest);
}
