package ru.bezborodov.twitter.user.timeline.web.model;

import java.time.Instant;

public record TimeLineResponse(
        long id,
        long authorId,
        String message,
        String authorNickname,
        String authorImageLink,
        Instant createdTimestamp
) {
}
