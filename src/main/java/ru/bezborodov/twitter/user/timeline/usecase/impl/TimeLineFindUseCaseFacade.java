package ru.bezborodov.twitter.user.timeline.usecase.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.timeline.mapper.TweetPageToTimeLinePageResponseMapper;
import ru.bezborodov.twitter.user.timeline.usecase.TimeLineFindUseCase;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineFindRequest;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLinePageResponse;
import ru.bezborodov.twitter.user.tweet.api.service.TweetApiService;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.model.Tweet_;

@Component
public class TimeLineFindUseCaseFacade implements TimeLineFindUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final TweetApiService tweetApiService;
    private final TweetPageToTimeLinePageResponseMapper mapper;

    public TimeLineFindUseCaseFacade(CurrentUserProfileApiService currentUserProfileApiService, TweetApiService tweetApiService, TweetPageToTimeLinePageResponseMapper mapper) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.tweetApiService = tweetApiService;
        this.mapper = mapper;
    }

    @Override
    public TimeLinePageResponse findTimeLines(TimeLineFindRequest findRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, Tweet_.CREATED_TIMESTAMP);
        Pageable pageable = PageRequest.of(findRequest.page(), findRequest.limit(), sort);

        UserProfile follower = currentUserProfileApiService.currentUserProfile();
        Page<Tweet> tweets = tweetApiService.findAllFollowerTweets(follower, pageable);
        return mapper.map(tweets);
    }
}
