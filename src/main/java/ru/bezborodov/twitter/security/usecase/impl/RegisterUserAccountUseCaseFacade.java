package ru.bezborodov.twitter.security.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.security.mapper.impl.RegisterRequestToUserAccountMapperImpl;
import ru.bezborodov.twitter.security.model.UserAccount;
import ru.bezborodov.twitter.security.services.UserAccountService;
import ru.bezborodov.twitter.security.usecase.RegisterUserAccountUseCase;
import ru.bezborodov.twitter.security.web.model.RegisterRequest;

@Component
public class RegisterUserAccountUseCaseFacade implements RegisterUserAccountUseCase {

    private final UserAccountService userAccountService;
    private final RegisterRequestToUserAccountMapperImpl mapper;

    public RegisterUserAccountUseCaseFacade(UserAccountService userAccountService, RegisterRequestToUserAccountMapperImpl mapper) {
        this.userAccountService = userAccountService;
        this.mapper = mapper;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        UserAccount account = mapper.map(registerRequest);
        this.userAccountService.createUserAccount(account);
    }
}
