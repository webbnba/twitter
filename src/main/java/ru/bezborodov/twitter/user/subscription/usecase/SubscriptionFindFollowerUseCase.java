package ru.bezborodov.twitter.user.subscription.usecase;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerFindRequest;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerPageResponse;

@Validated
public interface SubscriptionFindFollowerUseCase {
    FollowerPageResponse findFollowers(@Valid FollowerFindRequest findRequest);
}
