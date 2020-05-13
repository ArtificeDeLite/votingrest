package org.semenov.votingrest.web.dish;

import org.junit.jupiter.api.Test;
import org.semenov.votingrest.web.AbstractControllerTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.semenov.votingrest.DishesTestData.*;
import static org.semenov.votingrest.RestaurantTestData.REST_1_ID;
import static org.semenov.votingrest.UserTestData.USER;
import static org.semenov.votingrest.web.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.REST_URL.replace("{restaurantId}", Integer.toString(REST_1_ID)) + '/';

    private static final String REST_PARAM = "?date=2020-01-31";

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_16, DISH_17, DISH_18/*, DISH_NOW*/));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_01))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getDish() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_01));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_15_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
