package ru.bezborodov.twitter.security.services.impl;

import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.security.model.UserRole;
import ru.bezborodov.twitter.security.repository.UserRoleRepository;
import ru.bezborodov.twitter.security.services.UserRoleService;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserRole> findUserRole() {
        return this.userRoleRepository.findByAuthority("ROLE_USER");
    }
}
