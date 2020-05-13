package org.semenov.votingrest.web.vote;

import org.semenov.votingrest.web.SecurityUtil;
import org.semenov.votingrest.model.Vote;
import org.semenov.votingrest.service.VoteService;
import org.semenov.votingrest.util.DateUtil;
import org.semenov.votingrest.util.exception.IllegalRequestDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    static final String REST_URL = "/votes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    VoteService service;

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        return service.getAll(SecurityUtil.authUserId());
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get {}", id);
        return service.getByIdAndUserId(id, SecurityUtil.authUserId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestParam int restaurantId) {
        log.info("update with restId{} with id={}", restaurantId, id);
        if (LocalTime.now().isBefore(LocalTime.of(11, 0, 0))) {
            service.update(new Vote(id, DateUtil.getCurrentDate()), SecurityUtil.authUserId(), restaurantId);
        } else {
            throw new IllegalRequestDataException("it is too late, vote can't be changed");
        }
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Vote> create(@RequestParam int restaurantId) {
        Vote created = service.create(new Vote(null, DateUtil.getCurrentDate()), SecurityUtil.authUserId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
