package org.semenov.votingrest.repository;

import org.semenov.votingrest.model.Dish;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishesRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish item);

    List<Dish> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @EntityGraph(attributePaths = "restaurant")
    @Query("SELECT d FROM Dish d WHERE d.date =?1")
    List<Dish> findAllByDate(LocalDate date);

    Optional<Dish> findByIdAndRestaurantId(int id, int restaurantId);

}
