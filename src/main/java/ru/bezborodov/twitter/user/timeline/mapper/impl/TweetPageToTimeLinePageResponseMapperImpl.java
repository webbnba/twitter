package ru.bezborodov.twitter.user.timeline.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.bezborodov.twitter.user.timeline.mapper.TweetPageToTimeLinePageResponseMapper;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLinePageResponse;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineResponse;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

import java.util.Collection;

@Component
public class TweetPageToTimeLinePageResponseMapperImpl implements TweetPageToTimeLinePageResponseMapper {

    private final TweetToTimeLineResponseMapper mapper;

    public TweetPageToTimeLinePageResponseMapperImpl(TweetToTimeLineResponseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TimeLinePageResponse map(Page<Tweet> source) {

        Collection<TimeLineResponse> timeLineResponses = source
                .stream()
                .map(mapper::map)
                .toList();

        return new TimeLinePageResponse(
                source.getTotalElements(),
                source.isFirst(),
                source.isLast(),
                timeLineResponses
        );
    }
}
