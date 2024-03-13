package ru.bezborodov.twitter.security.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bezborodov.twitter.security.web.model.LoginRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerIntTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc restMockMvc;

    @Test
    void shouldReturnAccessToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(
                "rochelle_volkman26@yahoo.com",
                "password"
        );

        restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/authentication/access_token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(loginRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idToken").isNotEmpty())
                .andExpect(jsonPath("$.idToken").isString())
                .andReturn();
    }

    @Test
    void shouldNotReturnAccessTokenWithBadCredential() throws Exception {
        LoginRequest loginRequest = new LoginRequest(
                "rochelle_volkman26@yahoo.com",
                "passw0rd"
        );

        MvcResult mvcResult = restMockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/authentication/access_token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(loginRequest))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        Exception resolvedException = mvcResult.getResolvedException();
        assertNotNull(resolvedException);
        assertThat(resolvedException).isExactlyInstanceOf(BadCredentialsException.class);
        assertEquals("Bad credentials", resolvedException.getMessage());
    }
}
