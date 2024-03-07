package ru.bezborodov.twitter.user.tweet.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.tweet.mapper.TweetAddRequestToTweetMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.web.model.TweetAddRequest;

@Component
public class TweetAddRequestToTweetMapperImpl implements TweetAddRequestToTweetMapper {

    private final CurrentUserProfileApiService currentUserProfileApiService;

    public TweetAddRequestToTweetMapperImpl(CurrentUserProfileApiService currentUserProfileApiService) {
        this.currentUserProfileApiService = currentUserProfileApiService;
    }

    @Override
    public Tweet map(TweetAddRequest request) {

        Tweet tweet = new Tweet();
        tweet.setUserProfile(this.currentUserProfileApiService.currentUserProfile());
        tweet.setMessage(request.message());
    return tweet;
    }
}
