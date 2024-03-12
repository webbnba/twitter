package ru.bezborodov.twitter.user.subscription.web.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record FollowerFindRequest(
        @Min(0)
        int page,
        @Min(20)
        @Max(200)
        int limit
) {
}
