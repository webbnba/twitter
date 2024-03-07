package ru.bezborodov.twitter.user.tweet.mapper;

import org.springframework.data.domain.Page;
import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetPageResponse;

public interface TweetPageToTweetPageResponseMapper extends Mapper<TweetPageResponse, Page<Tweet>> {
}
