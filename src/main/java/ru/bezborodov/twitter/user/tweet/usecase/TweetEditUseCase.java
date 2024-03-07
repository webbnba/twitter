package ru.bezborodov.twitter.user.tweet.usecase;

import ru.bezborodov.twitter.user.tweet.web.model.TweetEditRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

public interface TweetEditUseCase {
    TweetResponse editTweet(TweetEditRequest editRequest);
}
