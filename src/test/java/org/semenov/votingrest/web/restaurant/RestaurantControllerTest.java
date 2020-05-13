package org.semenov.votingrest.web.restaurant;

import org.junit.jupiter.api.Test;
import org.semenov.votingrest.model.Restaurant;
import org.semenov.votingrest.service.RestaurantService;
import org.semenov.votingrest.web.AbstractControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.semenov.votingrest.DishesTestData.*;
import static org.semenov.votingrest.RestaurantTestData.*;
import static org.semenov.votingrest.UserTestData.USER;
import static org.semenov.votingrest.web.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantController.REST_URL + '/';


    @Autowired
    private RestaurantService service;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_MATCHER.contentJson(REST_1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getWthTodayMenu() throws Exception {
        ResultActions actions = perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_MATCHER.contentJson(REST_1, REST_2, REST_4, REST_5, REST_3));

        List<Restaurant> restaurants = readListFromJson(actions, Restaurant.class);
        DISH_MATCHER_NO_DATE.assertMatch(restaurants.get(0).getDishes(), DISH_16, DISH_17, DISH_18);
        DISH_MATCHER_NO_DATE.assertMatch(restaurants.get(1).getDishes(), DISH_19, DISH_20, DISH_21);
        DISH_MATCHER_NO_DATE.assertMatch(restaurants.get(2).getDishes(), DISH_25, DISH_26, DISH_27);
        DISH_MATCHER_NO_DATE.assertMatch(restaurants.get(3).getDishes(), DISH_28, DISH_29, DISH_30);
        DISH_MATCHER_NO_DATE.assertMatch(restaurants.get(4).getDishes(), DISH_22, DISH_23, DISH_24);
    }
}
