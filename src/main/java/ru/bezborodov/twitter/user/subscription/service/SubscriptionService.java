package ru.bezborodov.twitter.user.subscription.service;

import ru.bezborodov.twitter.user.subscription.model.Subscription;

public interface SubscriptionService {

    void createSubscription(Subscription subscription);

    void deleteSubscription(Subscription subscription);

    boolean existsSubscription(Subscription subscription);
}
