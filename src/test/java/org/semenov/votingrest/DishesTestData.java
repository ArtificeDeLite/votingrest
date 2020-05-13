package org.semenov.votingrest;

import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.web.TestMatcher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.semenov.votingrest.model.User.START_SEQ;

public class DishesTestData {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator(Dish.class, "restaurant");

    public static TestMatcher<Dish> DISH_MATCHER_NO_DATE = TestMatcher.usingFieldsComparator(Dish.class, "restaurant", "date");

    public static final int START_SEQ_REST = START_SEQ + 7;

    public static final int DISH_1_ID = START_SEQ_REST;
    public static final int DISH_2_ID = START_SEQ_REST + 1;
    public static final int DISH_3_ID = START_SEQ_REST + 2;
    public static final int DISH_4_ID = START_SEQ_REST + 3;
    public static final int DISH_5_ID = START_SEQ_REST + 4;
    public static final int DISH_6_ID = START_SEQ_REST + 5;
    public static final int DISH_7_ID = START_SEQ_REST + 6;//22
    public static final int DISH_8_ID = START_SEQ_REST + 7;
    public static final int DISH_9_ID = START_SEQ_REST + 8;
    public static final int DISH_10_ID = START_SEQ_REST + 9;
    public static final int DISH_11_ID = START_SEQ_REST + 10;
    public static final int DISH_12_ID = START_SEQ_REST + 11;
    public static final int DISH_13_ID = START_SEQ_REST + 12;
    public static final int DISH_14_ID = START_SEQ_REST + 13;
    public static final int DISH_15_ID = START_SEQ_REST + 14;
    public static final int DISH_16_ID = START_SEQ_REST + 15;
    public static final int DISH_17_ID = START_SEQ_REST + 16;
    public static final int DISH_18_ID = START_SEQ_REST + 17;

    public static final int DISH_NOW_ID = START_SEQ_REST + 30;

    public static final Dish DISH_01 = new Dish(DISH_1_ID, "soup #1", 25000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_02 = new Dish(DISH_2_ID, "main dish #1", 45000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_03 = new Dish(DISH_3_ID, "tea #1", 5000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_04 = new Dish(DISH_4_ID, "soup #2", 15000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_05 = new Dish(DISH_5_ID, "main dish #2", 35000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_06 = new Dish(DISH_6_ID, "coffee #2", 5000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_07 = new Dish(DISH_7_ID, "soup #3", 20000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_08 = new Dish(DISH_8_ID, "main dish #3", 30000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_09 = new Dish(DISH_9_ID, "coffee #3", 5000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_10 = new Dish(DISH_10_ID, "soup #4", 20000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_11 = new Dish(DISH_11_ID, "main dish #4", 30000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_12 = new Dish(DISH_12_ID, "coffee #4", 35000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_13 = new Dish(DISH_13_ID, "soup #5", 20000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_14 = new Dish(DISH_14_ID, "main dish #5", 30000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_15 = new Dish(DISH_15_ID, "coffee #5", 35000, LocalDate.of(2020, 1, 31));
    public static final Dish DISH_16 = new Dish(DISH_16_ID, "soup #11", 25000, LocalDate.now());
    public static final Dish DISH_17 = new Dish(DISH_17_ID, "main dish #11", 45000, LocalDate.now());
    public static final Dish DISH_18 = new Dish(DISH_18_ID, "tea #11", 5000, LocalDate.now());
    public static final Dish DISH_19 = new Dish(START_SEQ_REST + 18, "soup #21", 15000, LocalDate.now());
    public static final Dish DISH_20 = new Dish(START_SEQ_REST + 19, "main dish #21", 35000, LocalDate.now());
    public static final Dish DISH_21 = new Dish(START_SEQ_REST + 20, "coffee #21", 5000, LocalDate.now());
    public static final Dish DISH_22 = new Dish(START_SEQ_REST + 21, "soup #31", 20000, LocalDate.now());
    public static final Dish DISH_23 = new Dish(START_SEQ_REST + 22, "main dish #31", 30000, LocalDate.now());
    public static final Dish DISH_24 = new Dish(START_SEQ_REST + 23, "coffee #31", 5000, LocalDate.now());
    public static final Dish DISH_25 = new Dish(START_SEQ_REST + 24, "soup #41", 20000, LocalDate.now());
    public static final Dish DISH_26 = new Dish(START_SEQ_REST + 25, "main dish #41", 30000, LocalDate.now());
    public static final Dish DISH_27 = new Dish(START_SEQ_REST + 26, "coffee #41", 35000, LocalDate.now());
    public static final Dish DISH_28 = new Dish(START_SEQ_REST + 27, "soup #51", 20000, LocalDate.now());
    public static final Dish DISH_29 = new Dish(START_SEQ_REST + 28, "main dish #51", 30000, LocalDate.now());
    public static final Dish DISH_30 = new Dish(START_SEQ_REST + 29, "coffee #51", 35000, LocalDate.now());

    public static final LocalDate DATE_1 = LocalDate.of(2020, 1, 31);
    public static final LocalDate DATE_2 = LocalDate.of(2020, 2, 1);

    public static Dish getNew() {
        return new Dish(null, "New one", 9900, LocalDate.now());
    }

    public static List<Dish> getNewDishes() {
        return Arrays.asList(new Dish(null, "New one", 9900, LocalDate.now()), new Dish(null, "Second one", 1100, LocalDate.now()));

    }

    public static Dish getUpdated() {
        Dish updated = new Dish(DISH_01.getId(), DISH_01.getDescription(), DISH_01.getPrice(), DISH_01.getDate());
        updated.setDescription("Updated Description");
        updated.setPrice(55000);
        return updated;
    }
}
