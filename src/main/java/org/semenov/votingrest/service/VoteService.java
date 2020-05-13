package org.semenov.votingrest.service;

import org.semenov.votingrest.model.Vote;
import org.semenov.votingrest.repository.VotesRepository;
import org.semenov.votingrest.util.exception.IllegalRequestDataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.semenov.votingrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VotesRepository repository;

    public VoteService(VotesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Vote create(Vote vote, int userId, int restId) {
        Assert.notNull(vote, "vote must not be null");
        if (getByDateAndUser(vote.getDate(), userId) != null) {
            throw new IllegalRequestDataException("Vote already exists");
        }
        return repository.save(vote, userId, restId);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Vote getByDateAndUser(LocalDate date, int userId) {
        return repository.getByDateAndUser(date, userId);
    }

    public Vote getByIdAndUserId(int id, int userId) {
        return checkNotFoundWithId(repository.getByIdAndUser(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAllByUser(userId);
    }

    public void update(Vote vote, int userId, int restId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restId), vote.getId());
    }
}
