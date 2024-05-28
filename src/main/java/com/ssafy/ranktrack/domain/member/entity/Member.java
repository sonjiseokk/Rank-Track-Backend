package com.ssafy.ranktrack.domain.member.entity;

import com.ssafy.ranktrack.Tier;
import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private Tier tier;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberHistory> histories = new ArrayList<>();


    @Builder
    public Member(final String name, final String handle, final String profileImageUrl, final Long solvedCount, final Long rating, final Tier tier) {
        this.name = name;
        this.handle = handle;
        this.profileImageUrl = profileImageUrl;
        this.solvedCount = solvedCount;
        this.rating = rating;
        this.tier = tier;
    }

    public void updateData(final SolvedShowDto solvedShowData) {
        this.profileImageUrl = solvedShowData.getProfileImageUrl();
        this.solvedCount = solvedShowData.getSolvedCount();
        this.rating = solvedShowData.getRating();
        this.tier = solvedShowData.getTier();
    }
}
