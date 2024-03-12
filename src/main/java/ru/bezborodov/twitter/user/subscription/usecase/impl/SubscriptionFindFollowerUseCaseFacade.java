package ru.bezborodov.twitter.user.subscription.usecase.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.model.Subscription_;
import ru.bezborodov.twitter.user.subscription.service.SubscriptionService;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionFindFollowerUseCase;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerFindRequest;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerPageResponse;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerResponse;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerSubscription;

import java.util.Collection;
import java.util.List;

@Component
public class SubscriptionFindFollowerUseCaseFacade implements SubscriptionFindFollowerUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final SubscriptionService subscriptionService;

    public SubscriptionFindFollowerUseCaseFacade(CurrentUserProfileApiService currentUserProfileApiService, SubscriptionService subscriptionService) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public FollowerPageResponse findFollowers(FollowerFindRequest findRequest) {
        UserProfile author = currentUserProfileApiService.currentUserProfile();

        Pageable pageable = PageRequest
                .of(
                        findRequest.page(),
                        findRequest.limit(),

                        Sort.by(
                                Sort.Direction.DESC,
                                Subscription_.CREATED_TIMESTAMP
                        )
                );

        Page<FollowerSubscription> subscriptions = subscriptionService.findAllFollowerSubscriptions(author, pageable);

        List<FollowerResponse> followers = subscriptions.stream()
                .map(item -> new FollowerResponse(
                                item.getId(),
                                item.getFollower().getId(),
                                item.getFollower().getNickname(),
                                item.getFollower().getImageLink(),
                                item.getCreatedTimestamp()
                        )
                ).toList();

        return new FollowerPageResponse(
                subscriptions.getTotalElements(),
                subscriptions.isFirst(),
                subscriptions.isLast(),
                followers
        );
    }
}
