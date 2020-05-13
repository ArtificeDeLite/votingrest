package org.semenov.votingrest;

import org.semenov.votingrest.model.Restaurant;
import org.semenov.votingrest.web.TestMatcher;

import static org.semenov.votingrest.model.User.START_SEQ;

public class RestaurantTestData {

    public static TestMatcher<Restaurant> REST_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "dishes");

    public static final int START_SEQ_REST = START_SEQ + 2;

    public static final int REST_1_ID = START_SEQ_REST;
    public static final int REST_2_ID = START_SEQ_REST + 1;
    public static final int REST_3_ID = START_SEQ_REST + 2;
    public static final int REST_4_ID = START_SEQ_REST + 3;
    public static final int REST_5_ID = START_SEQ_REST + 4;

    public static final Restaurant REST_1 = new Restaurant(REST_1_ID, "Moskva", null);
    public static final Restaurant REST_2 = new Restaurant(REST_2_ID, "Rostov", null);
    public static final Restaurant REST_3 = new Restaurant(REST_3_ID, "Tbilisi", null);
    public static final Restaurant REST_4 = new Restaurant(REST_4_ID, "St.Petersburg", null);
    public static final Restaurant REST_5 = new Restaurant(REST_5_ID, "Stavropol", null);

    public static Restaurant getNew() {
        return new Restaurant(null, "New one", null);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(REST_1.getId(), REST_1.getDescription(), REST_1.getDishes());
        updated.setDescription("Updated Description");
        return updated;
    }
}
