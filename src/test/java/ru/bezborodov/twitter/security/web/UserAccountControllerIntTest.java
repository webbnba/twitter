package ru.bezborodov.twitter.security.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bezborodov.twitter.common.exception.TwitterException;
import ru.bezborodov.twitter.security.repository.UserAccountRepository;
import ru.bezborodov.twitter.security.web.model.RegisterRequest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserAccountControllerIntTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    private UserAccountRepository repository;

    @Test
    void shouldCreateUserAccount() throws Exception {
        final String username = validUsername();
        boolean existsBefore = repository.existsByUsername(username);
        assertFalse(existsBefore);

        RegisterRequest registerRequest = new RegisterRequest(
                username,
                "strong_password"
        );

        restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isCreated());

        boolean existsAfter = repository.existsByUsername(username);
        assertTrue(existsAfter);
    }

    @Test
    void shouldNotCreateExistingUserAccount() throws Exception {
        final String username = validUsername();
        boolean existsBefore = repository.existsByUsername(username);
        assertFalse(existsBefore);

        RegisterRequest registerRequest = new RegisterRequest(
                username,
                "strong_password"
        );

        restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isCreated());

        boolean existsAfter = repository.existsByUsername(username);
        assertTrue(existsAfter);

        MvcResult mvcResult = restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        Exception resolvedException = mvcResult.getResolvedException();
        assertNotNull(resolvedException);
        assertThat(resolvedException).isExactlyInstanceOf(TwitterException.class);
        assertEquals("Account with this username already exist", resolvedException.getMessage());
    }

    @Test
    void shouldNotCreateUserAccountWhenInvalidRegisterRequest() throws Exception {
        final String username = invalidUsername();
        boolean existsBefore = repository.existsByUsername(username);
        assertFalse(existsBefore);

        RegisterRequest registerRequest = new RegisterRequest(
                username,
                "strong_password"
        );

        restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isBadRequest());

        boolean existsAfter = repository.existsByUsername(username);
        assertFalse(existsAfter);
    }

    private static String validUsername() {
        String[] uuid = UUID.randomUUID().toString().split("-");
        return String.format("%s-%s@gmail.com", uuid[0], uuid[1]);
    }

    private static String invalidUsername() {
        String[] uuid = UUID.randomUUID().toString().split("-");
        return String.format("%s-%s", uuid[0], uuid[1]);
    }
}
