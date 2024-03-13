package ru.bezborodov.twitter.user.timeline.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeLinePageResponse.class))}),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content)})
    public TimeLinePageResponse findTimeLines(@RequestParam("page") int page,
                                              @RequestParam("limit") int limit) {

        TimeLineFindRequest request = new TimeLineFindRequest(page, limit);
        return timeLineFindUseCase.findTimeLines(request);
    }
}
