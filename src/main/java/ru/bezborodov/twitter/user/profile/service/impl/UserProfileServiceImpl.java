package ru.bezborodov.twitter.user.profile.service.impl;

import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.profile.repository.UserProfileRepository;
import ru.bezborodov.twitter.user.profile.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (this.userProfileRepository.existsById(userProfile.getId())) {
            String errorMessage = String.format(
                    "Профиль пользователя с данным Id = %d уже ранее был создан", userProfile.getId());
            throw new TwitterException(errorMessage);
        }
        if (this.userProfileRepository.existsByNickname(userProfile.getNickname())) {
            String errorMessage = String.format(
                    "Профиль пользователя с данным Nickname = %s уже ранее был создан", userProfile.getNickname());
            throw new TwitterException(errorMessage);
        }
        this.userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findUserProfileByIdRequired(Long userProfileId) {
        return this.userProfileRepository.findById(userProfileId)
            .orElseThrow(() -> {
            String errorMessage = String.format("Профиля пользователя с id = %d в системе не существует",
                    userProfileId
            );
            return new TwitterException(errorMessage);
        });
    }
}
