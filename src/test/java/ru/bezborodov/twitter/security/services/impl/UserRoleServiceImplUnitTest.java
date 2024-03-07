package ru.bezborodov.twitter.security.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bezborodov.twitter.security.model.UserRole;
import ru.bezborodov.twitter.security.repository.UserRoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplUnitTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Test
    void shouldReturnUserRole() {
        UserRole userRole = new UserRole();
        userRole.setAuthority("ROLE_USER");

        when(userRoleRepository.findByAuthority("ROLE_USER"))
                .thenReturn(Optional.of(userRole));

        Optional<UserRole> result = userRoleService.findUserRole();

        assertEquals(userRole, result.orElse(null));
        verify(userRoleRepository, times(1))
                .findByAuthority(any());
    }

    @Test
    void userRoleDoesNotExist() {
        when(userRoleRepository.findByAuthority("ROLE_USER"))
                .thenReturn(Optional.empty());

        Optional<UserRole> result = userRoleService.findUserRole();

        assertEquals(Optional.empty(), result);
        verify(userRoleRepository, times(1))
                .findByAuthority(any());
    }
}