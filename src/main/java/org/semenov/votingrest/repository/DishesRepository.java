package org.semenov.votingrest.repository;

import org.semenov.votingrest.model.Dish;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishesRepository {

    private CrudDishesRepository repository;

    private CrudRestaurantRepository restaurantRepository;

    public DishesRepository(CrudDishesRepository repository, CrudRestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public Dish save(Dish dish, int restId) {
        if (!dish.isNew() && getByRest(dish.getId(), restId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restId));
        return repository.save(dish);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Dish getByRest(int id, int restId) {
        return repository.findByIdAndRestaurantId(id, restId).orElse(null);
    }

    public List<Dish> getAllRest(int restId, LocalDate date) {
        return repository.findAllByRestaurantIdAndDate(restId, date);
    }

    public List<Dish> getAllDate(LocalDate date) {
        return repository.findAllByDate(date);
    }
}
