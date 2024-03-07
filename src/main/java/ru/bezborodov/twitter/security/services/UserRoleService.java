package ru.bezborodov.twitter.security.services;

import ru.bezborodov.twitter.security.model.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRole();
}
