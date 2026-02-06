package dev.iratherfear.vote_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.iratherfear.vote_app.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    
}
