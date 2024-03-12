package ru.bezborodov.twitter.user.tweet.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

public interface TweetApiService {

    Page<Tweet> findAllFollowerTweets(UserProfile follower, Pageable pageable);
}
