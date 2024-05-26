package com.ssafy.ranktrack.domain.member.entity;

import com.ssafy.ranktrack.Tier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Member")
@Getter
@NoArgsConstructor
public class Member {
    @Id
    private String id;
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
    private Tier tier;

    @Builder
    public Member(final String name, final String handle, final String profileImageUrl, final Long solvedCount, final Long rating, final Tier tier) {
        this.name = name;
        this.handle = handle;
        this.profileImageUrl = profileImageUrl;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier;
    }
}
