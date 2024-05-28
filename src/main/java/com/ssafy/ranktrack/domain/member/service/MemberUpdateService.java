package com.ssafy.ranktrack.domain.member.service;

import com.ssafy.ranktrack.domain.api.solved.SolvedApiService;
import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {
    private final MemberService memberService;
    private final MemberHistoryService memberHistoryService;
    private final SolvedApiService solvedApiService;

    public void updateSolveData() throws Exception {
        List<MemberDto> all = memberService.findAll();
        for (MemberDto memberDto : all) {
            String handle = memberDto.getHandle();
            SolvedShowDto solvedShowData = solvedApiService.getSolvedShowData(handle);

            Member updatedMember = memberService.update(handle, solvedShowData);
            memberHistoryService.join(updatedMember, solvedShowData);
        }
    }
}
