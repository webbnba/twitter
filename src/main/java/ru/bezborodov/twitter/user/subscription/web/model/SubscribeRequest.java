package ru.bezborodov.twitter.user.subscription.web.model;

import jakarta.validation.constraints.NotNull;

public record SubscribeRequest(@NotNull Long followedId) {
}
