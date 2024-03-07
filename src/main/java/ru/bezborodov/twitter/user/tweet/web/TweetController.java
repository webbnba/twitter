package ru.bezborodov.twitter.user.tweet.web;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bezborodov.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.bezborodov.twitter.user.tweet.usecase.TweetDeleteUseCase;
import ru.bezborodov.twitter.user.tweet.usecase.TweetEditUseCase;
import ru.bezborodov.twitter.user.tweet.usecase.TweetFindUseCase;
import ru.bezborodov.twitter.user.tweet.web.model.*;

@RestController
@RequestMapping("/api/v1/tweets")
public class TweetController {

    private final TweetAddUseCase useCase;
    private final TweetEditUseCase editUseCase;

    private final TweetDeleteUseCase tweetDeleteUseCase;
    private final TweetFindUseCase tweetFindUseCase;

    public TweetController(TweetAddUseCase useCase, TweetEditUseCase editUseCase, TweetDeleteUseCase tweetDeleteUseCase, TweetFindUseCase tweetFindUseCase) {
        this.useCase = useCase;
        this.editUseCase = editUseCase;
        this.tweetDeleteUseCase = tweetDeleteUseCase;
        this.tweetFindUseCase = tweetFindUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse addTweet(@Valid @RequestBody TweetAddRequest addRequest) {
        return this.useCase.addTweet(addRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TweetResponse updateTweet(@Valid @RequestBody TweetEditRequest editRequest) {
        return this.editUseCase.editTweet(editRequest);
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTweet(@PathVariable long tweetId) {
        this.tweetDeleteUseCase.deleteTweet(tweetId);
    }

    @GetMapping
    public TweetPageResponse findAllOwnerTweets(@PathParam("page") int page,
                                                @PathParam("limit") int limit) {
        TweetFindRequest findRequest = new TweetFindRequest(page, limit);
        return this.tweetFindUseCase.findTweets(findRequest);
    }
}
