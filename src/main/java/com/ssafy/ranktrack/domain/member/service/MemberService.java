package com.ssafy.ranktrack.domain.member.service;

import com.ssafy.ranktrack.domain.api.solved.SolvedApiService;
import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.history.service.StageSolvedService;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.repository.MemberRepository;
import com.ssafy.ranktrack.global.exception.DBException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 가입이 진행되면 회원의 현재 상태를 member 에 저장
     * member history도 현재 상태를 스냅샷 찍어 저장
     * 이때 단계별 푼 문제 수도 api를 통해 가져와서 같이 저장
     */
    @Transactional
    public String join(String handle, String name, SolvedShowDto solvedShowDto) {
        Member member = Member.builder()
                .handle(handle)
                .name(name)
                .solvedCount(solvedShowDto.getSolvedCount())
                .profileImageUrl(solvedShowDto.getProfileImageUrl())
                .rating(solvedShowDto.getRating())
                .tier(solvedShowDto.getTier())
                .build();
        memberRepository.save(member);
        return member.getHandle();
    }


    /**
     * 전체 멤버 리스트를 반환하는 메소드
     *
     * @return
     * @throws Exception
     */
    public List<MemberDto> findAll() throws Exception {
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

    public MemberDto findByHandle(String handle) throws Exception {
        try {
            Member findMember = memberRepository.findByHandle(handle).orElseThrow();
            return MemberDto.builder()
                    .handle(findMember.getHandle())
                    .profileImageUrl(findMember.getProfileImageUrl())
                    .name(findMember.getName())
                    .solvedCount(findMember.getSolvedCount())
                    .tier(findMember.getTier())
                    .rating(findMember.getRating())
                    .build();
        } catch (SQLException e) {
            e.fillInStackTrace();
            throw new DBException("DB 조회에서 에러가 발생했습니다.");
        }
    }

    public Member update(final String handle, SolvedShowDto solvedShowData) {
        try {
            Member member = memberRepository.findByHandle(handle).orElseThrow();
            member.updateData(solvedShowData);
            return member;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
