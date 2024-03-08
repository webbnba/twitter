package ru.bezborodov.twitter.user.tweet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.bezborodov.twitter.user.tweet.usecase.TweetAddUseCase;
import ru.bezborodov.twitter.user.tweet.web.model.TweetAddRequest;
import ru.bezborodov.twitter.user.tweet.web.model.TweetResponse;

import java.time.Instant;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TweetControllerUnitTest {

    MockMvc mockMvc;

    @Mock
    private TweetAddUseCase tweetAddUseCase;

    @InjectMocks
    private TweetController tweetController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
    }

    @Test
    void shouldReturnStatusCreated() throws Exception {
        TweetAddRequest tweetAddRequest = new TweetAddRequest("New good Tweet");
        TweetResponse tweetResponse = new TweetResponse(1, "New good Tweet", Instant.now(), Instant.now());

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        Mockito.when(tweetAddUseCase.addTweet(tweetAddRequest)).thenReturn(tweetResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/tweets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(tweetAddRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(tweetResponse)))
                .andReturn();

        Mockito.verify(tweetAddUseCase, times(1)).addTweet(tweetAddRequest);
    }

    @Test
    void shouldReturnStatus4xxInvalidRequest() throws Exception {
        TweetAddRequest tweetAddRequest = new TweetAddRequest("New");
        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/tweets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(tweetAddRequest)))
                .andExpect(status().is4xxClientError())
                .andReturn();

        Exception actualException = mvcResult.getResolvedException();
        Assertions.assertNotNull(actualException);
        Assertions.assertInstanceOf(MethodArgumentNotValidException.class, actualException);
        verify(tweetAddUseCase, never()).addTweet(tweetAddRequest);

    }
}