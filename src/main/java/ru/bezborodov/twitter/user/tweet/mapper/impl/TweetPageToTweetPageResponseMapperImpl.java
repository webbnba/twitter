package ru.bezborodov.twitter.user.tweet.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.tweet.mapper.TweetPageToTweetPageResponseMapper;
import ru.bezborodov.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetPageResponse;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

import java.util.Collection;

@Component
public class TweetPageToTweetPageResponseMapperImpl implements TweetPageToTweetPageResponseMapper {

    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;

    public TweetPageToTweetPageResponseMapperImpl(TweetToTweetResponseMapper tweetToTweetResponseMapper) {
        this.tweetToTweetResponseMapper = tweetToTweetResponseMapper;
    }

    @Override
    public TweetPageResponse map(Page<Tweet> source) {
        Collection<TweetResponse> tweetPageResponse = source.stream()
                .map(this.tweetToTweetResponseMapper::map)
                .toList();

        return new TweetPageResponse(
                source.getTotalElements(),
                source.isFirst(),
                source.isLast(),
                tweetPageResponse);
    }
}
