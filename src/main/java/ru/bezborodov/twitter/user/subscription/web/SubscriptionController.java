package ru.bezborodov.twitter.user.subscription.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionAddUseCase;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionDeleteUseCase;
import ru.bezborodov.twitter.user.subscription.usecase.SubscriptionFindFollowerUseCase;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerFindRequest;
import ru.bezborodov.twitter.user.subscription.web.model.FollowerPageResponse;
import ru.bezborodov.twitter.user.subscription.web.model.SubscribeRequest;
import ru.bezborodov.twitter.user.subscription.web.model.UnsubscribeRequest;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionAddUseCase subscriptionAddUseCase;
    private final SubscriptionDeleteUseCase subscriptionDeleteUseCase;
    private final SubscriptionFindFollowerUseCase subscriptionFindFollowerUseCase;

    public SubscriptionController(SubscriptionAddUseCase subscriptionAddUseCase,
                                  SubscriptionDeleteUseCase subscriptionDeleteUseCase, SubscriptionFindFollowerUseCase subscriptionFindFollowerUseCase) {
        this.subscriptionAddUseCase = subscriptionAddUseCase;
        this.subscriptionDeleteUseCase = subscriptionDeleteUseCase;
        this.subscriptionFindFollowerUseCase = subscriptionFindFollowerUseCase;
    }

    @PostMapping("/subscribe")
    public void subscribe(@Valid @RequestBody SubscribeRequest subscribeRequest) {
        this.subscriptionAddUseCase.subscribe(subscribeRequest);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(@Valid @RequestBody UnsubscribeRequest unsubscribeRequest) {
        this.subscriptionDeleteUseCase.unsubscribe(unsubscribeRequest);
    }

    @GetMapping("/followers")
    public FollowerPageResponse allFollowers(@RequestParam("page") int page,
                                             @RequestParam("limit") int limit) {
        FollowerFindRequest findRequest = new FollowerFindRequest(page, limit);
        return this.subscriptionFindFollowerUseCase.findFollowers(findRequest);
    }
}
