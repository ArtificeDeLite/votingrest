package org.semenov.votingrest.service;

import org.semenov.votingrest.model.Dish;
import org.semenov.votingrest.repository.DishesRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.semenov.votingrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishesRepository repository;

    public DishService(DishesRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish create(Dish dish, int restId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Dish getByRest(int id, int restId) {
        return checkNotFoundWithId(repository.getByRest(id, restId), id);
    }

    public List<Dish> getAllRest(int restId, LocalDate date) {
        return repository.getAllRest(restId, date);
    }

    @Cacheable("dishes")
    public List<Dish> getAllDate(LocalDate date) {
        return repository.getAllDate(date);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void update(Dish dish, int restId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restId), dish.getId());
    }
}
