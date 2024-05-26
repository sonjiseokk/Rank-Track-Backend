package com.ssafy.ranktrack.domain.member.service;

import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.history.service.StageSolvedService;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.repository.MemberRepository;
import com.ssafy.ranktrack.global.exception.DBException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberHistoryService memberHistoryService;
    private final StageSolvedService stageSolvedService;

    /**
     * 가입이 진행되면 회원의 현재 상태를 member 에 저장
     * member history도 현재 상태를 스냅샷 찍어 저장
     * 이때 단계별 푼 문제 수도 api를 통해 가져와서 같이 저장
     *
     * @param member
     * @return
     */
    @Transactional
    public String join(Member member) {
        memberRepository.save(member);

        MemberHistory memberHistory = MemberHistory.builder()
                .member(member)
                .solvedCount(member.getSolvedCount())
                .rating(member.getRating())
                .tier(member.getTier())
                .timestamp(LocalDateTime.now())
                .build();
        memberHistoryService.join(memberHistory);

        return member.getHandle();
    }

//    public Member detail(String handle) {
//        try {
//            Member findMember = memberRepository.findByHandle(handle).orElseThrow();
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<MemberDto> findAll() throws Exception{
        try {
            List<Member> allMember = memberRepository.findAllDesc();

            List<MemberDto> result = new ArrayList<>();
            for (Member member : allMember) {
                MemberDto memberDto = MemberDto.builder()
                        .handle(member.getHandle())
                        .profileImageUrl(member.getProfileImageUrl())
                        .name(member.getName())
                        .solvedCount(member.getSolvedCount())
                        .tier(member.getTier())
                        .rating(member.getRating())
                        .build();
                result.add(memberDto);
            }

            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
            throw new DBException("DB 조회에서 에러가 발생했습니다.");
        }
    }
}
