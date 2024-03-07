package ru.bezborodov.twitter.security.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bezborodov.twitter.security.mapper.UserAccountToUserMapper;
import ru.bezborodov.twitter.security.services.UserAccountService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountService userAccountService;
    private final UserAccountToUserMapper userMapper;
    public UserDetailsServiceImpl(UserAccountService userAccountService, UserAccountToUserMapper userMapper) {
        this.userAccountService = userAccountService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userAccountService.findUserByUsername(username)
                .map(this.userMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("Неверные учетные данные пользователя"));
    }
}
