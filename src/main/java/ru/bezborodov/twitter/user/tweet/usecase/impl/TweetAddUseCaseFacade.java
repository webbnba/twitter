package ru.bezborodov.twitter.user.tweet.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.tweet.mapper.TweetAddRequestToTweetMapper;
import ru.bezborodov.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;
import ru.bezborodov.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.bezborodov.twitter.user.tweet.web.model.TweetAddRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

@Component
public class TweetAddUseCaseFacade implements TweetAddUseCase {

    private final TweetAddRequestToTweetMapper tweetAddRequestToTweetMapper;
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;
    private final TweetService tweetService;

    public TweetAddUseCaseFacade(TweetAddRequestToTweetMapper tweetAddRequestToTweetMapper,
                                 TweetToTweetResponseMapper tweetToTweetResponseMapper,
                                 TweetService tweetService) {
        this.tweetAddRequestToTweetMapper = tweetAddRequestToTweetMapper;
        this.tweetToTweetResponseMapper = tweetToTweetResponseMapper;
        this.tweetService = tweetService;
    }

    @Override
    public TweetResponse addTweet(TweetAddRequest addRequest) {
        Tweet mappedTweet = this.tweetAddRequestToTweetMapper.map(addRequest);
        Tweet createdTweet = this.tweetService.createTweet(mappedTweet);
        return this.tweetToTweetResponseMapper.map(createdTweet);
    }
}
