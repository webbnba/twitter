package ru.bezborodov.twitter.user.subscription.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.mapper.SubscriptionRequestToSubscriptionMapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.service.SubscriptionService;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionAddUseCase;
import ru.bezborodov.twitter.user.subscription.web.model.SubscribeRequest;

@Component
public class SubscriptionAddUseCaseFacade implements SubscriptionAddUseCase {

    private final SubscriptionRequestToSubscriptionMapper subscriptionMapper;
    private final SubscriptionService subscriptionService;

    public SubscriptionAddUseCaseFacade(SubscriptionRequestToSubscriptionMapper subscriptionMapper,
                                        SubscriptionService subscriptionService) {
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void subscribe(SubscribeRequest request) {
        Subscription subscription = this.subscriptionMapper.map(request);
        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();
        if(follower.equals(followed)) {
           throw new TwitterException("Подписка на самого себя не имеет никакого смысла");
        }

        if(this.subscriptionService.existsSubscription(subscription)) {
            String errorMessage = String.format(
                    "Вы уже подписаны на %s",
                    followed.getNickname());
            throw new TwitterException(errorMessage);
        }
        subscriptionService.createSubscription(subscription);
    }
}
