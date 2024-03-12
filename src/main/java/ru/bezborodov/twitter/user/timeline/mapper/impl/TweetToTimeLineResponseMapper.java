package ru.bezborodov.twitter.user.timeline.mapper.impl;

import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineResponse;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

public interface TweetToTimeLineResponseMapper extends Mapper<TimeLineResponse, Tweet> {
}
