package com.ssafy.ranktrack.domain.history.entity.dto;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StageSolvedCount {
    private Tier tier;
    private int solved;

    @Builder
    public StageSolvedCount(final Tier tier, final int solved) {
        this.tier = tier;
        this.solved = solved;
    }
}
