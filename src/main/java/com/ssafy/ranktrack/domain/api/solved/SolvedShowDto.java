package com.ssafy.ranktrack.domain.api.solved;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SolvedShowDto {
    private String handle;
    private String profileImageUrl;
    private Long solvedCount;
    private Long rating;
    private Tier tier;

    @Builder
    public SolvedShowDto(final String handle, final String profileImageUrl, final Long solvedCount, final Long rating, final Tier tier) {
        this.handle = handle;
        this.profileImageUrl = profileImageUrl;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier;
    }
}
