package org.semenov.votingrest.web.restaurant;

import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.model.Restaurant;
import org.semenov.votingrest.service.DishService;
import org.semenov.votingrest.service.RestaurantService;
import org.semenov.votingrest.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.semenov.votingrest.util.DateUtil.getCurrentDate;
import static org.semenov.votingrest.util.RestaurantUtil.createTo;


@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    static final String REST_URL = "/restaurants";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get {}", id);
        return createTo(service.get(id));
    }

    @GetMapping
    public List<Restaurant> getWthTodayMenu() {
        log.info("get all");
        List<Restaurant> restaurants = service.getAll();
        List<Dish> dishes = dishService.getAllDate(getCurrentDate());

        Map<Restaurant, List<Dish>> map = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getRestaurant));

        restaurants.forEach(r -> r.setDishes(map.getOrDefault(r, List.of())));
        return restaurants;
    }
}
