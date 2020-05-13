package org.semenov.votingrest.web.dish;

import org.junit.jupiter.api.Test;
import org.semenov.votingrest.web.AbstractControllerTest;
import org.semenov.votingrest.DishesTestData;
import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.service.DishService;
import org.semenov.votingrest.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.semenov.votingrest.DishesTestData.*;
import static org.semenov.votingrest.RestaurantTestData.REST_1_ID;
import static org.semenov.votingrest.UserTestData.ADMIN;
import static org.semenov.votingrest.web.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishController.REST_URL.replace("{restaurantId}", Integer.toString(REST_1_ID)) + '/';

    private static final String REST_PARAM = "?date=2020-01-31";

    @Autowired
    private DishService service;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_01))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getMenuForDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST_PARAM)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_01, DISH_02, DISH_03));
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_01));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_15_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void update() throws Exception {
        Dish updated = DishesTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(service.get(DISH_1_ID), updated);
    }

    @Test
    public void createMenu() throws Exception {
        List<Dish> newDishes = DishesTestData.getNewDishes();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValues(newDishes))
                .with(userHttpBasic(ADMIN)));

        List<Dish> created = readListFromJson(action, Dish.class);
        int id0 = created.get(0).getId();
        int id1 = created.get(1).getId();
        newDishes.get(0).setId(id0);
        newDishes.get(1).setId(id1);
        DISH_MATCHER.assertMatch(created, newDishes);
        DISH_MATCHER.assertMatch(service.get(id0), newDishes.get(0));
        DISH_MATCHER.assertMatch(service.get(id1), newDishes.get(1));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createInvalid() throws Exception {
        List<Dish> invalid = Collections.singletonList(new Dish(null, null, 2000, LocalDate.now()));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_1_ID, "description", 110, null);
        perform(MockMvcRequestBuilders.put(REST_URL + DISH_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        Dish invalid = new Dish(DISH_1_ID, DISH_02.getDescription(), DISH_02.getPrice(), DISH_02.getDate());

        perform(MockMvcRequestBuilders.put(REST_URL + DISH_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        Dish invalid = new Dish(null, DISH_02.getDescription(), DISH_02.getPrice(), DISH_02.getDate());
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValues(Collections.singletonList(invalid)))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
