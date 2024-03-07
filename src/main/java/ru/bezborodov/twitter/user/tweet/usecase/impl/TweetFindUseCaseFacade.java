package ru.bezborodov.twitter.user.tweet.usecase.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.mapper.TweetPageToTweetPageResponseMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;
import ru.bezborodov.twitter.user.tweet.usecase.TweetFindUseCase;
import ru.bezborodov.twitter.user.tweet.web.model.TweetFindRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetPageResponse;

import static ru.bezborodov.twitter.user.tweet.model.Tweet_.CREATED_TIMESTAMP;

@Component
public class TweetFindUseCaseFacade implements TweetFindUseCase {

    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final TweetService tweetService;
    private final TweetPageToTweetPageResponseMapper tweetPageToTweetPageResponseMapper;

    public TweetFindUseCaseFacade(CurrentUserProfileApiService currentUserProfileApiService,
                                  TweetService tweetService,
                                  TweetPageToTweetPageResponseMapper tweetPageToTweetPageResponseMapper) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.tweetService = tweetService;
        this.tweetPageToTweetPageResponseMapper = tweetPageToTweetPageResponseMapper;
    }

    @Override
    public TweetPageResponse findTweets(TweetFindRequest tweetFindRequest) {
        UserProfile owner = this.currentUserProfileApiService.currentUserProfile();

        Sort sort = Sort.by(Sort.Direction.DESC, CREATED_TIMESTAMP);

        Pageable pageable = PageRequest.of(tweetFindRequest.page(), tweetFindRequest.limit(), sort);

        Page<Tweet> pageableTweetResult = this.tweetService.findAllTweets(owner, pageable);

        return tweetPageToTweetPageResponseMapper.map(pageableTweetResult);
    }
}
