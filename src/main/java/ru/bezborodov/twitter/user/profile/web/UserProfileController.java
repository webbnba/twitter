package ru.bezborodov.twitter.user.profile.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bezborodov.twitter.user.profile.usecase.UserProfileRegisterUseCase;
import ru.bezborodov.twitter.user.profile.web.model.UserProfileRegisterRequest;

@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {

    private final UserProfileRegisterUseCase useCase;

    public UserProfileController(UserProfileRegisterUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserProfile(@Valid @RequestBody UserProfileRegisterRequest registerRequest) {
        this.useCase.registerUserProfile(registerRequest);
    }
}
