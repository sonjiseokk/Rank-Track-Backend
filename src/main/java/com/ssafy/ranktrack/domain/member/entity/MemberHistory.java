package com.ssafy.ranktrack.domain.member.entity;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "MemberHistory")
@Getter
@NoArgsConstructor
public class MemberHistory {
    @Id
    private String id;
    private String handle;
    private Long solvedCount;
    private Long rating;
    private Tier tier;
    private LocalDateTime timestamp;

    @Builder
    public MemberHistory(final String handle, final Long solvedCount, final Long rating, final Tier tier, final LocalDateTime timestamp) {
        this.handle = handle;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier;
        this.timestamp = timestamp;
    }
}
