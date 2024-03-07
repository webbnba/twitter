package ru.bezborodov.twitter.user.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bezborodov.twitter.user.profile.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByNickname(String nickname);
}
