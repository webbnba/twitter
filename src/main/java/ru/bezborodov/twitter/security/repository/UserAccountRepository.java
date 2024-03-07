package ru.bezborodov.twitter.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bezborodov.twitter.security.model.UserAccount;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByUsername(String username);

    Optional<UserAccount> findByUsername(String username);
}
