package ru.bezborodov.twitter.user.tweet.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

@Component
public class TweetToTweetResponseMapperImpl implements TweetToTweetResponseMapper {
    @Override
    public TweetResponse map(Tweet model) {

        return new TweetResponse(
                model.getId(),
                model.getMessage(),
                model.getCreatedTimestamp(),
                model.getModifiedTimestamp()
        );
    }
}
