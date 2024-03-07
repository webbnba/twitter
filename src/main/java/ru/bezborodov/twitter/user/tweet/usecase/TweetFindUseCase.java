package ru.bezborodov.twitter.user.tweet.usecase;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.bezborodov.twitter.user.tweet.web.model.TweetFindRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetPageResponse;

@Validated
public interface TweetFindUseCase {
    TweetPageResponse findTweets(@Valid TweetFindRequest tweetFindRequest);
}
