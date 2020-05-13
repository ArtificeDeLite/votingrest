package org.semenov.votingrest.web.dish;

import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.service.DishService;
import org.semenov.votingrest.to.DishTo;
import org.semenov.votingrest.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.semenov.votingrest.util.DateUtil.getCurrentDate;
import static org.semenov.votingrest.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {

    static final String REST_URL = "/admin/restaurants/{restaurantId}/menu";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> getMenu(@PathVariable int restaurantId, @RequestParam @Nullable LocalDate date) {
        if (date != null) {
            log.info("get menu for restaurant {} for {}", restaurantId, date);
            return service.getAllRest(restaurantId, date);
        }
        log.info("get menu for restaurant {}", restaurantId);
        return service.getAllRest(restaurantId, getCurrentDate());
    }

    @GetMapping("/{dishId}")
    public Dish get(@PathVariable int dishId, @PathVariable int restaurantId) {
        log.info("get {}", dishId);
        return service.getByRest(dishId, restaurantId);
    }

    @PutMapping("/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int dishId, @PathVariable int restaurantId) {
        log.info("update {} with id={}", dishTo, dishId);
        Dish dish = new Dish(dishTo.getId(), dishTo.getDescription(), dishTo.getPrice(), dishTo.getDate());
        assureIdConsistent(dish, dishId);
        service.update(dish, restaurantId);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<List<Dish>> createMenu(@PathVariable int restaurantId, @RequestBody @Valid List<Dish> dishes) {

        dishes.forEach(ValidationUtil::validate);

        log.info("create{}", dishes);

        List<Dish> createdMenu = new ArrayList<>();
        for (Dish dish : dishes) {
            log.info("create{}", dish);
            Dish created = service.create(dish, restaurantId);
            createdMenu.add(created);
        }

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdMenu);
    }
}
