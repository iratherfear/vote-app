package dev.iratherfear.vote_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.iratherfear.vote_app.model.Poll;
import dev.iratherfear.vote_app.model.Vote;
import dev.iratherfear.vote_app.model.VoteOption;
import dev.iratherfear.vote_app.repository.PollRepository;

@Service
public class PollService {
    
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }

    public void createTask(Poll poll) {
        pollRepository.save(poll);
    }

    public void vote(Vote vote) {
        long pollId = vote.getPollId();
        long optionId = vote.getOptionId();
        Poll poll = pollRepository.findById(pollId).orElseThrow();
        List<VoteOption> voteOptions = poll.getVoteOptions();
        VoteOption voteOption = voteOptions.get((int) optionId);
        voteOption.setVoteNumber(voteOption.getVoteNumber() + 1);
        pollRepository.save(poll);
    }
}
