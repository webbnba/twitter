package ru.bezborodov.twitter.security.mapper;

import org.springframework.security.core.userdetails.User;
import ru.bezborodov.twitter.security.model.UserAccount;

public interface UserAccountToUserMapper extends Mapper<User, UserAccount> {
}
