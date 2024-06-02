package com.ssafy.ranktrack.domain.member.entity.dto;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberDto {
    // 리얼 이름
    private String name;
    // 사용자 핸들
    private String handle;
    // 프로필 이미지
    private String profileImageUrl;
    // 푼 문제 수
    private Long solvedCount;
    // AC 스코어
    private Long rating;
    // 티어
    private String tier;
    // 반 등수
    private Integer classRank;

    @Builder
    public MemberDto(final String name, final String handle, final String profileImageUrl, final Long solvedCount, final Long rating, final Tier tier, final Integer classRank) {
        this.name = name;
        this.handle = handle;
        this.profileImageUrl = profileImageUrl;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier.getName();
        this.classRank = classRank;
    }
}
