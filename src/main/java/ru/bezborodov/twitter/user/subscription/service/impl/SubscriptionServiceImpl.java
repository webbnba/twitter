package ru.bezborodov.twitter.user.subscription.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.subscription.model.Subscription;
import ru.bezborodov.twitter.user.subscription.repository.SubscriptionRepository;
import ru.bezborodov.twitter.user.subscription.service.SubscriptionService;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerSubscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void createSubscription(Subscription subscription) {
        this.subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Subscription subscription) {
        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();

        this.subscriptionRepository.findByFollowerAndFollowed(follower, followed)
                .ifPresent(this.subscriptionRepository::delete);
    }

    @Override
    public boolean existsSubscription(Subscription subscription) {
        UserProfile follower = subscription.getFollower();
        UserProfile followed = subscription.getFollowed();
        return this.subscriptionRepository.existsByFollowerAndFollowed(follower, followed);
    }

    @Override
    public Page<FollowerSubscription> findAllFollowerSubscriptions(UserProfile author, Pageable pageable) {
        return this.subscriptionRepository.findAllByFollowed(author, pageable);
    }
}
