package dev.iratherfear.vote_app.dto;

import java.util.ArrayList;
import java.util.List;

import dev.iratherfear.vote_app.model.VoteOption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PollRequest {

    private Long id;
    private String question;
    private List<VoteOption> voteOptions = new ArrayList<>();

    public void addOptionToOptions(VoteOption option) {
        voteOptions.add(option);
    }
}
