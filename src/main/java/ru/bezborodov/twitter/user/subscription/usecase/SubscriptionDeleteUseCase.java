package ru.bezborodov.twitter.user.subscription.usecase;

import ru.bezborodov.twitter.user.subscription.web.model.UnsubscribeRequest;

public interface SubscriptionDeleteUseCase {
    void unsubscribe(UnsubscribeRequest unsubscriberequest);
}
