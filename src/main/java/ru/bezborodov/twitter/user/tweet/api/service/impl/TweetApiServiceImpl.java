package ru.bezborodov.twitter.user.tweet.api.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.api.service.TweetApiService;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;

@Service
public class TweetApiServiceImpl implements TweetApiService {

    private final TweetService tweetService;

    public TweetApiServiceImpl(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public Page<Tweet> findAllFollowerTweets(UserProfile follower, Pageable pageable) {
        return tweetService.findAllFollowerTweets(follower, pageable);
    }
}
