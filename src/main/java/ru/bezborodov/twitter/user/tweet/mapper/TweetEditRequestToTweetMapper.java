package ru.bezborodov.twitter.user.tweet.mapper;

import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetEditRequest;

public interface TweetEditRequestToTweetMapper extends Mapper<Tweet, TweetEditRequest> {
}
