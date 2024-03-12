package ru.bezborodov.twitter.user.timeline.mapper;

import org.springframework.data.domain.Page;
import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLinePageResponse;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

public interface TweetPageToTimeLinePageResponseMapper extends Mapper<TimeLinePageResponse, Page<Tweet>> {
}
