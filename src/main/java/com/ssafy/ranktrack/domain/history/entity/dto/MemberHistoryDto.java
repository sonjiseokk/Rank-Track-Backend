package com.ssafy.ranktrack.domain.history.entity.dto;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberHistoryDto {
    private String handle;
    private Long solvedCount;
    private Long rating;
    private String tier;
    private LocalDate timestamp;

    @Builder
    public MemberHistoryDto(final String handle, final Long solvedCount, final Long rating, final Tier tier, final LocalDate timestamp) {
        this.handle = handle;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier.getName();
        this.timestamp = timestamp;
    }
}
