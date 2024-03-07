package ru.bezborodov.twitter.user.tweet.usecase.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.common.TwitterException;
import ru.bezborodov.twitter.user.profile.api.service.CurrentUserProfileApiService;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.model.Tweet;
import ru.bezborodov.twitter.user.tweet.service.TweetService;
import ru.bezborodov.twitter.user.tweet.usecase.TweetDeleteUseCase;

@Component
public class TweetDeleteUseCaseFacade implements TweetDeleteUseCase {

    private final TweetService tweetService;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    public TweetDeleteUseCaseFacade(TweetService tweetService,
                                    CurrentUserProfileApiService currentUserProfileApiService) {
        this.tweetService = tweetService;
        this.currentUserProfileApiService = currentUserProfileApiService;
    }

    @Override
    public void deleteTweet(long tweetId) {
        UserProfile actor = this.currentUserProfileApiService.currentUserProfile();
        UserProfile owner = this.tweetService.findTweetById(tweetId)
                .map(Tweet::getUserProfile)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Tweet с Id = %d не существует.", tweetId);
                    return new TwitterException(errorMessage);
                });

        if (!actor.equals(owner)) {
            String errorMessage = String.format("Удаление твита с ID = %d запрещено." +
                    "Рользователь %s не является его владельцем", tweetId, actor.getNickname());
            throw new TwitterException(errorMessage);
        }
        this.tweetService.deleteTweet(tweetId);
    }
}
