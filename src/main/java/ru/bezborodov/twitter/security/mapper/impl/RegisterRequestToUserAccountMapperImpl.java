package ru.bezborodov.twitter.security.mapper.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.security.mapper.RegisterRequestToUserAccountMapper;
import ru.bezborodov.twitter.security.model.UserAccount;
import ru.bezborodov.twitter.security.model.UserRole;
import ru.bezborodov.twitter.security.services.UserRoleService;
import ru.bezborodov.twitter.security.web.model.RegisterRequest;

import java.util.Locale;
import java.util.Set;

@Component
public class RegisterRequestToUserAccountMapperImpl implements RegisterRequestToUserAccountMapper {

    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public RegisterRequestToUserAccountMapperImpl(UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount map(RegisterRequest registerRequest) {
        UserRole userRole = this.userRoleService
                .findUserRole()
                .orElseThrow(() -> new TwitterException("User role not found"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerRequest.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(this.passwordEncoder.encode(registerRequest.password()));
        userAccount.setAuthorities(Set.of(userRole));

        return userAccount;
    }
}
