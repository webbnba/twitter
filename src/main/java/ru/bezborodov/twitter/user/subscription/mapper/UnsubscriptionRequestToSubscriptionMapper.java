package ru.bezborodov.twitter.user.subscription.mapper;

import ru.bezborodov.twitter.security.mapper.Mapper;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.web.model.UnsubscribeRequest;

public interface UnsubscriptionRequestToSubscriptionMapper extends Mapper<Subscription, UnsubscribeRequest> {
}
