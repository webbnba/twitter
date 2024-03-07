package ru.bezborodov.twitter.security.services.impl;

import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.security.model.UserAccount;
import ru.bezborodov.twitter.security.repository.UserAccountRepository;
import ru.bezborodov.twitter.security.services.UserAccountService;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createUserAccount(UserAccount userAccount) {
        boolean isUsernameExist = userAccountRepository.existsByUsername(userAccount.getUsername());
        if(isUsernameExist) {
            throw new TwitterException("Account with this username already exist");
        }
        this.userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findUserByUsername(String username) {
        return this.userAccountRepository.findByUsername(username);
    }
}
