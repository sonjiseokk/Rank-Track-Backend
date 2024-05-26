package com.ssafy.ranktrack.domain.history.entity;

import com.ssafy.ranktrack.Tier;
import com.ssafy.ranktrack.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class MemberHistory {
    @Id
    @GeneratedValue
    @Column(name = "member_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long solvedCount;
    private Long rating;
    @Enumerated(EnumType.STRING)
    private Tier tier;
    private LocalDateTime timestamp;

    @Builder
    public MemberHistory(final Member member, final Long solvedCount, final Long rating,
                         final Tier tier, final LocalDateTime timestamp) {
        this.member = member;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier;
        this.timestamp = timestamp;
    }

}
