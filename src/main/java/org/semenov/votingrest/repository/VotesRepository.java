package org.semenov.votingrest.repository;

import org.semenov.votingrest.model.Vote;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VotesRepository {

    private CrudVotesRepository repository;

    private CrudRestaurantRepository restaurantRepository;

    private CrudUserRepository userRepository;

    public VotesRepository(CrudVotesRepository repository, CrudRestaurantRepository restaurantRepository,
                           CrudUserRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote save(Vote vote, int userId, int restId) {
        if (!vote.isNew() && get(vote.getId()) == null) {
            return null;
        }
        vote.setRestaurant(restaurantRepository.getOne(restId));
        vote.setUser(userRepository.getOne(userId));
        return repository.save(vote);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Vote get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Vote getByDateAndUser(LocalDate date, int userId) {
        return repository.findByDateAndUserId(date, userId).orElse(null);
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }

    public Vote getByIdAndUser(int id, int userId) {
        return repository.findByIdAndUserId(id, userId).orElse(null);
    }

    public List<Vote> getAllByUser(int userId) {
        return repository.findAllByUserId(userId);
    }
}
