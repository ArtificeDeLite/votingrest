package org.semenov.votingrest.web.vote;

import org.junit.jupiter.api.Test;
import org.semenov.votingrest.VoteTestData;
import org.semenov.votingrest.model.Vote;
import org.semenov.votingrest.service.VoteService;
import org.semenov.votingrest.web.AbstractControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.List;

import static org.semenov.votingrest.RestaurantTestData.*;
import static org.semenov.votingrest.UserTestData.*;
import static org.semenov.votingrest.VoteTestData.*;
import static org.semenov.votingrest.web.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(new org.semenov.votingrest.model.Vote[]{VOTE_1}));

        List<Vote> votes = readListFromJson(action, Vote.class);
        REST_MATCHER.assertMatch(votes.get(0).getRestaurant(), REST_1);
    }

    @Test
    public void update() throws Exception {

        if (LocalTime.now().isBefore(LocalTime.of(11, 0, 0))) {
            perform(MockMvcRequestBuilders.put(REST_URL + VOTE_1_ID + "?restaurantId=" + REST_2_ID)
                    .with(userHttpBasic(USER)))
                    .andExpect(status().isNoContent());
            Vote updated = VoteTestData.getUpdated();
            updated.setRestaurant(REST_2);
            VOTE_MATCHER.assertMatch(service.get(VOTE_1_ID), updated);
        } else {
            perform(MockMvcRequestBuilders.put(REST_URL + VOTE_1_ID + "?restaurantId=" + REST_2_ID)
                    .with(userHttpBasic(USER)))
                    .andExpect(status().isUnprocessableEntity());
        }
    }

    @Test
    public void create() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL + "?restaurantId=" + REST_1_ID)
                .with(userHttpBasic(USER)));

        Vote created = readFromJson(action, Vote.class);
        newVote.setId(created.getId());
        newVote.setRestaurant(REST_1);
        newVote.setUser(USER);

        VOTE_MATCHER.assertMatch(created, newVote);
        USER_MATCHER.assertMatch(created.getUser(), USER);
    }
}
