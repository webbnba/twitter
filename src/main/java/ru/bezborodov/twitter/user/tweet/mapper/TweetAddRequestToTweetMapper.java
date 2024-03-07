package ru.bezborodov.twitter.user.tweet.mapper;

import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetAddRequest;

public interface TweetAddRequestToTweetMapper extends Mapper<Tweet, TweetAddRequest> {
}
