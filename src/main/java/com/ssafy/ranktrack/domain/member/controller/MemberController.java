package com.ssafy.ranktrack.domain.member.controller;

import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.member.controller.request.MemberRequest;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.service.MemberService;
import com.ssafy.ranktrack.domain.api.solved.SolvedApiService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final SolvedApiService solvedApiService;
    private final MemberHistoryService memberHistoryService;

    /**
     * 메인 페이지의 순위표 기능을 담당하는 메소드
     *
     * @return 현재까지 업데이트 된 유저의 리스트
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllMembers() throws Exception {
        List<MemberDto> memberDtos = memberService.findAll();
        return ResponseEntity.ok(new Result<>(true, "성공", memberDtos));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSolveData() throws Exception {
        List<MemberDto> all = memberService.findAll();
        for (MemberDto memberDto : all) {
            String handle = memberDto.getHandle();
            SolvedShowDto solvedShowData = solvedApiService.getSolvedShowData(handle);

            Member updatedMember = memberService.update(handle, solvedShowData);
            memberHistoryService.join(updatedMember, solvedShowData);
        }
        return ResponseEntity.ok(new Result<>(true, "성공", "성공"));
    }

    /**
     * 테스트용 API
     * members 에 사용자 핸들 정보가 있는 배열을 넣어주면 사용자를 등록해준다.
     *
     * @param members 모든 사용자의 핸들 정보
     */
    @PostMapping("/members")
    public void saveMembers(@RequestBody List<MemberRequest> members) {
        for (MemberRequest memberRequest : members) {
            System.out.println("Received member ID: " + memberRequest.getHandle());

            SolvedShowDto solvedShowData = solvedApiService.getSolvedShowData(memberRequest.getHandle());
            memberService.join(memberRequest.getHandle(),memberRequest.getName(),solvedShowData);
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        boolean success;
        String message;
        T data;
    }
}
