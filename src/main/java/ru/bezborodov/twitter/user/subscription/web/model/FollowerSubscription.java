package ru.bezborodov.twitter.user.subscription.web.model;

import ru.bezborodov.twitter.user.profile.model.UserProfile;

import java.time.Instant;

public interface FollowerSubscription {
    long getId();
    UserProfile getFollower();
    Instant getCreatedTimestamp();
}
