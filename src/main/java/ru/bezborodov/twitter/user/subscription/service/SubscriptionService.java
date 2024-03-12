package ru.bezborodov.twitter.user.subscription.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerSubscription;

public interface SubscriptionService {

    void createSubscription(Subscription subscription);

    void deleteSubscription(Subscription subscription);

    boolean existsSubscription(Subscription subscription);

    Page<FollowerSubscription> findAllFollowerSubscriptions(UserProfile author, Pageable pageable);
}
