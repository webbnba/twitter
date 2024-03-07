package ru.bezborodov.twitter.user.tweet.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.mapper.TweetEditRequestToTweetMapper;
import ru.bezborodov.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;
import ru.bezborodov.twitter.user.tweet.usecase.TweetEditUseCase;
import ru.bezborodov.twitter.user.tweet.web.model.TweetEditRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

@Component
public class TweetEditUseCaseImpl implements TweetEditUseCase {

    private final TweetService tweetService;
    private final TweetEditRequestToTweetMapper tweetEditRequestToTweetMapper;
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    public TweetEditUseCaseImpl(TweetService tweetService,
                                TweetEditRequestToTweetMapper tweetEditRequestToTweetMapper,
                                TweetToTweetResponseMapper tweetToTweetResponseMapper,
                                CurrentUserProfileApiService currentUserProfileApiService) {
        this.tweetService = tweetService;
        this.tweetEditRequestToTweetMapper = tweetEditRequestToTweetMapper;
        this.tweetToTweetResponseMapper = tweetToTweetResponseMapper;
        this.currentUserProfileApiService = currentUserProfileApiService;
    }

    @Override
    public TweetResponse editTweet(TweetEditRequest editRequest) {
        UserProfile actor = this.currentUserProfileApiService.currentUserProfile();
        UserProfile owner = this.tweetService.findTweetById(editRequest.id())
                .map(Tweet::getUserProfile)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Tweet с Id = %d не существует.", editRequest.id());
                    return new TwitterException(errorMessage);
                });

        if(!actor.equals(owner)) {
            String errorMessage = String.format("Редактирование твита с ID = %d запрещено." +
                    "Рользователь %s не является его владельцем", editRequest.id(), actor.getNickname());
            throw new TwitterException(errorMessage);
        }

        Tweet tweet = this.tweetEditRequestToTweetMapper.map(editRequest);
        Tweet updatedTweet = this.tweetService.updateTweet(tweet);

        return this.tweetToTweetResponseMapper.map(updatedTweet);
    }
}
