package dev.iratherfear.vote_app.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.iratherfear.vote_app.dto.PollRequest;
import dev.iratherfear.vote_app.model.Vote;
import dev.iratherfear.vote_app.service.PollService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/poll")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {
    
    private final PollService pollService;

    PollController(PollService pollService) {
        this.pollService = pollService;
    }
   
    @GetMapping
    public List<PollRequest> getAllPolls() {
        System.out.println("Call for request of all poll!!");
        return pollService.getAllPolls();
    }

    // @GetMapping("/{id}")
    // public Poll getTask(@PathVariable Long id) {
    //     return pollService.getPoll(id).orElseThrow();
    // }

    @PostMapping
    public void createAPoll(@RequestBody PollRequest pollRequest) {
        System.out.println("Creating a new poll with request: " + pollRequest);
        pollService.createTask(pollRequest);
    }

    @PostMapping("/vote")
    public void voteToPoll(@RequestBody Vote vote) {
        pollService.vote(vote);
    }
}
