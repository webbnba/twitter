package ru.bezborodov.twitter.user.subscription.web.model;

import java.util.Collection;

public record FollowerPageResponse(
        long totalFollowers,
        boolean isFirstPage,
        boolean isLastPage,
        Collection<FollowerResponse> followers
) {
}
