package com.ssafy.ranktrack.domain.history.entity.dto;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExpectHistoryDto {
    private Long expectSolvedCount;
    private Long expectRating;
    private Tier expectTier;

    @Builder
    public ExpectHistoryDto(final Long expectSolvedCount, final Long expectRating, final Tier expectTier) {
        this.expectSolvedCount = expectSolvedCount;
        this.expectRating = expectRating;
        this.expectTier = expectTier;
    }
}
