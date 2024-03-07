package ru.bezborodov.twitter.user.tweet.mapper;

import ru.bezborodov.twitter.security.mapper.Mapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

public interface TweetToTweetResponseMapper extends Mapper<TweetResponse, Tweet> {
}
