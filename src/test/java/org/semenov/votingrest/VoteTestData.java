package org.semenov.votingrest;

import org.semenov.votingrest.model.Vote;
import org.semenov.votingrest.web.TestMatcher;

import java.time.LocalDate;

import static org.semenov.votingrest.model.User.START_SEQ;

public class VoteTestData {

    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "restaurant", "user");


    public static final int START_SEQ_REST = START_SEQ + 37;

    public static final int VOTE_1_ID = START_SEQ_REST;
    public static final int VOTE_2_ID = START_SEQ_REST + 1;
    public static final int VOTE_3_ID = START_SEQ_REST + 2;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, LocalDate.of(2020, 1, 31));
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, LocalDate.of(2020, 1, 31));
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, LocalDate.of(2020, 2, 1));

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(VOTE_1.getId(), VOTE_1.getDate());
        updated.setDate(LocalDate.now());
        return updated;
    }
}
