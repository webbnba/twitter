package ru.bezborodov.twitter.security.mapper;

import ru.bezborodov.twitter.common.mapper.Mapper;
import ru.bezborodov.twitter.security.model.UserAccount;
import ru.bezborodov.twitter.security.web.model.RegisterRequest;

public interface RegisterRequestToUserAccountMapper extends Mapper<UserAccount, RegisterRequest> {
}
