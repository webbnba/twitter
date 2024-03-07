package ru.bezborodov.twitter.user.subscription.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.mapper.UnsubscriptionRequestToSubscriptionMapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.service.SubscriptionService;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionDeleteUseCase;
import ru.bezborodov.twitter.user.subscription.web.model.UnsubscribeRequest;

@Component
public class SubscriptionDeleteUseCaseFacade implements SubscriptionDeleteUseCase {

    private final UnsubscriptionRequestToSubscriptionMapper unsubscriptionRequestToSubscriptionMapper;
    private final SubscriptionService subscriptionService;

    public SubscriptionDeleteUseCaseFacade(UnsubscriptionRequestToSubscriptionMapper unsubscriptionRequestToSubscriptionMapper, SubscriptionService subscriptionService) {
        this.unsubscriptionRequestToSubscriptionMapper = unsubscriptionRequestToSubscriptionMapper;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void unsubscribe(UnsubscribeRequest unsubscriberequest) {
        Subscription subscription = this.unsubscriptionRequestToSubscriptionMapper.map(unsubscriberequest);

        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();

        if (follower.equals(followed)) {
            throw new TwitterException("Отписка от самого себя не имеет никакого смысла");
        }

        if (!this.subscriptionService.existsSubscription(subscription)) {
            String errorMessage = String.format(
                "Вы не были родписаны на %s",
                followed.getNickname()
            );
            throw new TwitterException(errorMessage);
        }
        this.subscriptionService.deleteSubscription(subscription);
    }
}
