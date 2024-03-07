package ru.bezborodov.twitter.user.tweet.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.user.tweet.mapper.TweetEditRequestToTweetMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;
import ru.bezborodov.twitter.user.tweet.web.model.TweetEditRequest;

@Component
public class TweetEditRequestToTweetMapperImpl implements TweetEditRequestToTweetMapper {

    private final TweetService tweetService;

    public TweetEditRequestToTweetMapperImpl(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public Tweet map(TweetEditRequest request) {
        Tweet currentTweet = this.tweetService
                .findTweetById(request.id())
                .orElseThrow(() -> {
                    String errorMessage = String.format("Tweet с Id = %d не существует.", request.id());
                    return new TwitterException(errorMessage);
                });

        currentTweet.setMessage(request.message());

        return currentTweet;
    }
}
