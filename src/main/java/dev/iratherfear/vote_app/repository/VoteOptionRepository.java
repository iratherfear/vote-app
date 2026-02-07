package dev.iratherfear.vote_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.iratherfear.vote_app.model.VoteOption;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {
    
    @Query("Select v.poll.id from VoteOption v where v.id = :voteOptionId")
    Long findPollIdByVoteOptionId(@Param("voteOptionId") Long voteOptionId);
}
