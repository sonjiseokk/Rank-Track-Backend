package com.ssafy.ranktrack.domain.history.service;

import com.ssafy.ranktrack.Tier;
import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import com.ssafy.ranktrack.domain.history.entity.dto.ExpectHistoryDto;
import com.ssafy.ranktrack.domain.history.entity.dto.MemberHistoryDto;
import com.ssafy.ranktrack.domain.history.repository.MemberHistoryRepository;
import com.ssafy.ranktrack.domain.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            MemberHistory memberHistory2 = MemberHistory.builder()
                    .member(member)
                    .solvedCount(solvedShowDto.getSolvedCount())
                    .rating(solvedShowDto.getRating())
                    .tier(solvedShowDto.getTier())
                    .timestamp(LocalDateTime.now())
                    .build();
            memberHistoryRepository.save(memberHistory2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 일별 solved 정보
     *
     * @param memberId
     * @return
     */
    public List<MemberHistoryDto> dailyHistory(Long memberId) {
        try {
            List<MemberHistory> dailyMemberHistory = memberHistoryRepository.findDailyMemberHistory(memberId);
            return dailyMemberHistory.stream().map(MemberHistory::toDto).toList();
        } catch (SQLException e) {
            throw new RuntimeException("MemberHistoryService dailyHistory 에러 발생");
        }
    }

    /**
     * 평균치를 계산해서 3일 예측치를 계산
     * @param histories
     * @return
     */
    public List<ExpectHistoryDto> expectThreeScore(List<MemberHistoryDto> histories) {
        MemberHistoryDto start = histories.get(0);
        MemberHistoryDto end = histories.get(histories.size() - 1);

        long avgRating = getAvgRating(start, end);
        long avgSolvedCount = getAvgSolvedCount(start, end);

        List<ExpectHistoryDto> result = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            long expectRating = start.getRating() + (avgRating * i);
            long expectSolvedCount = start.getSolvedCount() + (avgSolvedCount * i);
            Tier expectTier = Tier.fromScore(expectRating);
            ExpectHistoryDto expectDto = ExpectHistoryDto.builder()
                    .expectRating(expectRating)
                    .expectSolvedCount(expectSolvedCount)
                    .expectTier(expectTier)
                    .build();
            result.add(expectDto);
        }

        return result;
    }

    private Long getAvgSolvedCount(final MemberHistoryDto start, final MemberHistoryDto end) {
        return (end.getSolvedCount() - start.getSolvedCount()) / 7;
    }

    private Long getAvgRating(final MemberHistoryDto start, final MemberHistoryDto end) {
        long gap = end.getRating() - start.getRating();
        return gap / 7;
    }


}
