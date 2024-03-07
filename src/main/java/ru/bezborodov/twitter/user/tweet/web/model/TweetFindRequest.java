package ru.bezborodov.twitter.user.tweet.web.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TweetFindRequest(
        @Min(0)
        int page,
        @Min(20)
        @Max(200)
        int limit
) {
}
