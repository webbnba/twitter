package ru.bezborodov.twitter.user.subscription.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.api.service.UserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.mapper.SubscriptionRequestToSubscriptionMapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.web.model.SubscribeRequest;

@Component
public class SubscriptionRequestToSubscriptionMapperImpl implements SubscriptionRequestToSubscriptionMapper {

    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final UserProfileApiService userProfileApiService;

    public SubscriptionRequestToSubscriptionMapperImpl(CurrentUserProfileApiService currentUserProfileApiService, UserProfileApiService userProfileApiService) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public Subscription map(SubscribeRequest subscribeRequest) {
        UserProfile follower = this.currentUserProfileApiService.currentUserProfile();
        UserProfile followed = this.userProfileApiService
                .findUserProfileById(subscribeRequest.followedId());

        Subscription subscription = new Subscription();
        subscription.setFollower(follower);
        subscription.setFollowed(followed);
        return subscription;
    }
}
