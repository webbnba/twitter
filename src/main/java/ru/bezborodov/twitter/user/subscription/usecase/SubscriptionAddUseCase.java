package ru.bezborodov.twitter.user.subscription.usecase;

import ru.bezborodov.twitter.user.subscription.web.model.SubscribeRequest;

public interface SubscriptionAddUseCase {

    void subscribe(SubscribeRequest request);
}
