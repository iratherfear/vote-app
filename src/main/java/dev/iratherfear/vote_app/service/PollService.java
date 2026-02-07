package dev.iratherfear.vote_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import dev.iratherfear.vote_app.dto.PollRequest;
import dev.iratherfear.vote_app.model.Poll;
import dev.iratherfear.vote_app.model.Vote;
import dev.iratherfear.vote_app.model.VoteOption;
import dev.iratherfear.vote_app.repository.PollRepository;
import dev.iratherfear.vote_app.repository.VoteOptionRepository;

@Service
public class PollService {
    
    private final PollRepository pollRepository;
    private final VoteOptionRepository voteOptionRepository;

    public PollService(PollRepository pollRepository, VoteOptionRepository voteOptionRepository) {
        this.pollRepository = pollRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    public List<PollRequest> getAllPolls() {

        List<Poll> polls = pollRepository.findAll();
        List<PollRequest> pollRequests = new ArrayList<>();
        List<VoteOption> voteOptions = voteOptionRepository.findAll();
        
        Map<Long, Long> map = new HashMap<>();
        long index = 0;
        for(Poll poll: polls) {
            PollRequest pollRequest = new PollRequest();
            pollRequest.setId(poll.getId());
            pollRequest.setQuestion(poll.getQuestion());
            pollRequests.add(pollRequest);
            map.put(poll.getId(), index++);
        }

        for(VoteOption voteOption: voteOptions) {
            long pollId = voteOption.getPoll().getId();
            long mapId = map.get(pollId);
            pollRequests.get((int) mapId).addOptionToOptions(voteOption);
        }

        return pollRequests;
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }

    public void createTask(PollRequest pollRequest) {
        Poll poll = new Poll();
        poll.setQuestion(pollRequest.getQuestion());

        for(VoteOption voteOption: pollRequest.getVoteOptions()) {
            voteOption.setId(null);
            poll.addVoteOption(voteOption);
        }

        pollRepository.save(poll);
    }

    public void vote(Vote vote) {
        System.out.println("Voting for " + vote);
        VoteOption voteOption = voteOptionRepository.findById(vote.getVoteOptionId()).orElseThrow();
        long pollId = vote.getPollId();

        if(pollId != vote.getPollId()) {
            throw new IllegalArgumentException("Poll does not have this option");
        }
        voteOption.setVoteNumber(voteOption.getVoteNumber() + 1);
        voteOptionRepository.save(voteOption);
    }
}
