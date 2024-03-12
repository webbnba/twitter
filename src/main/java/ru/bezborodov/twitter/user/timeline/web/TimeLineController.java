package ru.bezborodov.twitter.user.timeline.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bezborodov.twitter.user.timeline.usecase.TimeLineFindUseCase;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineFindRequest;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLinePageResponse;

@RestController
@RequestMapping("/api/v1/timelines")
public class TimeLineController {
    private final TimeLineFindUseCase timeLineFindUseCase;

    public TimeLineController(TimeLineFindUseCase timeLineFindUseCase) {
        this.timeLineFindUseCase = timeLineFindUseCase;
    }

    @GetMapping
    public TimeLinePageResponse findTimeLines(@RequestParam("page") int page,
                                              @RequestParam("limit") int limit) {

        TimeLineFindRequest request = new TimeLineFindRequest(page, limit);
        return timeLineFindUseCase.findTimeLines(request);
    }
}
