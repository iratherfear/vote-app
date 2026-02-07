package dev.iratherfear.vote_app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Poll {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;

    @OneToMany(
        mappedBy = "poll",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<VoteOption> voteOptions = new ArrayList<>();

    public void addVoteOption(VoteOption voteOption) {
        voteOptions.add(voteOption);
        voteOption.setPoll(this);
    }
}
