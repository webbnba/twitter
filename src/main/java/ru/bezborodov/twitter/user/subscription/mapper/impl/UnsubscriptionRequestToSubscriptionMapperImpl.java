package ru.bezborodov.twitter.user.subscription.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.api.service.UserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.mapper.UnsubscriptionRequestToSubscriptionMapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.web.model.UnsubscribeRequest;

@Component
public class UnsubscriptionRequestToSubscriptionMapperImpl implements UnsubscriptionRequestToSubscriptionMapper {

    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final UserProfileApiService userProfileApiService;

    public UnsubscriptionRequestToSubscriptionMapperImpl(CurrentUserProfileApiService currentUserProfileApiService, UserProfileApiService userProfileApiService) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public Subscription map(UnsubscribeRequest unsubscribeRequest) {
        UserProfile follower = this.currentUserProfileApiService.currentUserProfile();
        UserProfile followed = this.userProfileApiService
                .findUserProfileById(unsubscribeRequest.followedId());

        Subscription subscription = new Subscription();
        subscription.setFollower(follower);
        subscription.setFollowed(followed);
        return subscription;
    }
}
