package ru.bezborodov.twitter.user.timeline.web.model;

import java.util.Collection;

public record TimeLinePageResponse(
        long totalTweets,
        boolean isFirstPage,
        boolean isLastPage,
        Collection<TimeLineResponse> tweets
) {
}
