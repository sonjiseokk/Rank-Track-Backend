package com.ssafy.ranktrack.domain.history.service;

import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import com.ssafy.ranktrack.domain.history.repository.MemberHistoryRepository;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberHistoryService {
    private final MemberHistoryRepository memberHistoryRepository;

    public List<MemberHistory> findAll() {
        return memberHistoryRepository.findAll();
    }

    @Transactional
    public void join(Member member, SolvedShowDto solvedShowDto) {
        try {
            MemberHistory memberHistory = MemberHistory.builder()
                    .member(member)
                    .solvedCount(solvedShowDto.getSolvedCount())
                    .rating(solvedShowDto.getRating())
                    .tier(solvedShowDto.getTier())
                    .timestamp(LocalDateTime.now())
                    .build();
            memberHistoryRepository.save(memberHistory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
