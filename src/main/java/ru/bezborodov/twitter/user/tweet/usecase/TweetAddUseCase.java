package ru.bezborodov.twitter.user.tweet.usecase;

import ru.bezborodov.twitter.user.tweet.web.model.TweetAddRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

public interface TweetAddUseCase {

    TweetResponse addTweet(TweetAddRequest addRequest);
}
