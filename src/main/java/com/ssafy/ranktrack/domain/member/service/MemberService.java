package com.ssafy.ranktrack.domain.member.service;

import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.entity.MemberHistory;
import com.ssafy.ranktrack.domain.member.repository.MemberHistoryRepository;
import com.ssafy.ranktrack.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberHistoryRepository memberHistoryRepository;

    public String join(Member member) {
        memberRepository.save(member);

        MemberHistory memberHistory = MemberHistory.builder()
                .handle(member.getHandle())
                .solvedCount(member.getSolvedCount())
                .rating(member.getRating())
                .tier(member.getTier())
                .timestamp(LocalDateTime.now())
                .build();

        memberHistoryRepository.save(memberHistory);
        return member.getHandle();
    }

    public Member findById(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
