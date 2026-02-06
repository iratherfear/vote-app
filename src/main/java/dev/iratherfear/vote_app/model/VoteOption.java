package dev.iratherfear.vote_app.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class VoteOption {
    private String optionName;
    private Long voteNumber;
}
