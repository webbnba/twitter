package ru.bezborodov.twitter.user.timeline.mapper.impl;

import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineResponse;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

@Component
public class TweetToTimeLineResponseMapperImpl implements TweetToTimeLineResponseMapper {

    @Override
    public TimeLineResponse map(Tweet item) {
        UserProfile userProfile = item.getUserProfile();
        return new TimeLineResponse(
                item.getId(),
                userProfile.getId(),
                item.getMessage(),
                userProfile.getNickname(),
                userProfile.getImageLink(),
                item.getCreatedTimestamp()
        );
    }
}
