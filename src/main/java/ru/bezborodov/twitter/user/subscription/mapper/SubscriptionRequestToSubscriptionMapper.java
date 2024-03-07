package ru.bezborodov.twitter.user.subscription.mapper;

import ru.bezborodov.twitter.security.mapper.Mapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.web.model.SubscribeRequest;

public interface SubscriptionRequestToSubscriptionMapper extends Mapper<Subscription, SubscribeRequest> {
}
