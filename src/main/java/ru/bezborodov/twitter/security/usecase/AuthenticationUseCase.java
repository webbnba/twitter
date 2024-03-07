package ru.bezborodov.twitter.security.usecase;

import ru.bezborodov.twitter.security.web.model.AccessToken;
import ru.bezborodov.twitter.security.web.model.LoginRequest;

public interface AuthenticationUseCase {
    AccessToken authenticate(LoginRequest loginRequest);
}
