package ru.bezborodov.twitter.user.tweet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

import java.util.Optional;

public interface TweetService {

    Tweet createTweet(Tweet tweet);

    Tweet updateTweet(Tweet tweet);

    Optional<Tweet> findTweetById(long tweetId);

    void deleteTweet(long tweetId);

    Page<Tweet> findAllTweets(UserProfile owner, Pageable pageable);
    Page<Tweet> findAllFollowerTweets(UserProfile follower, Pageable pageable);
}
