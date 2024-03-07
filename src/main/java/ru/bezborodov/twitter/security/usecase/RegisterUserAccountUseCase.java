package ru.bezborodov.twitter.security.usecase;

import ru.bezborodov.twitter.security.web.model.RegisterRequest;

public interface RegisterUserAccountUseCase {
    void register(RegisterRequest registerRequest);
}
