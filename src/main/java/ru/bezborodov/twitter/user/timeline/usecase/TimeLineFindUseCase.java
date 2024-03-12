package ru.bezborodov.twitter.user.timeline.usecase;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLineFindRequest;
import ru.bezborodov.twitter.user.timeline.web.model.TimeLinePageResponse;

@Validated
public interface TimeLineFindUseCase {
    TimeLinePageResponse findTimeLines(@Valid TimeLineFindRequest findRequest);
}
