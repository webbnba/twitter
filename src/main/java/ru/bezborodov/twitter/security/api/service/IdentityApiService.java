package ru.bezborodov.twitter.security.api.service;

import ru.bezborodov.twitter.security.api.model.CurrentUserApiModel;

import java.util.Optional;

public interface IdentityApiService {

    Optional<CurrentUserApiModel> currentUserAccount();
}
