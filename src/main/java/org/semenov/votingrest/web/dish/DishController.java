package org.semenov.votingrest.web.dish;

import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.semenov.votingrest.util.DateUtil.getCurrentDate;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    static final String REST_URL = "/restaurants/{restaurantId}/menu";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> get(@PathVariable int restaurantId) {
        log.info("get all by restaurant {}", restaurantId);
        return service.getAllRest(restaurantId, getCurrentDate());
    }

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get {}", id);
        return service.getByRest(id, restaurantId);
    }
}
