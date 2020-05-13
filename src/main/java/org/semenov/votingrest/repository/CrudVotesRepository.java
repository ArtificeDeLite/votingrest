package org.semenov.votingrest.repository;

import org.semenov.votingrest.model.Vote;
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
public interface CrudVotesRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    Optional<Vote> findByDateAndUserId(LocalDate date, int userId);

    @EntityGraph(attributePaths = "restaurant")
    @Query("SELECT v FROM Vote v WHERE v.id =?1 AND v.user.id =?2")
    Optional<Vote> findByIdAndUserId(int id, int userId);

    @EntityGraph(attributePaths = "restaurant")
    @Query("SELECT v FROM Vote v WHERE v.user.id =?1")
    List<Vote> findAllByUserId(int userId);
}
