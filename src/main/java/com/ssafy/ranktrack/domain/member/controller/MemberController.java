package com.ssafy.ranktrack.domain.member.controller;

import com.ssafy.ranktrack.domain.api.solved.SolvedShowDto;
import com.ssafy.ranktrack.domain.member.controller.request.MemberRequest;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.service.MemberService;
import com.ssafy.ranktrack.domain.api.solved.SolvedApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Member Controller", description = "회원 관련 REST API에 대한 명세를 제공합니다.")
@RequestMapping("/api/member")
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
    @Operation(summary = "회원 리스트(list)",description = "가입된 모든 회원 정보를 제공합니다. 메인 페이지의 순위표 기능으로 사용됩니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getAllMembers() throws Exception {
        List<MemberDto> memberDtos = memberService.findAll();
        return ResponseEntity.ok(new Result<>(true, OK.value(), "성공", memberDtos));
    }
    @Operation(summary = "회원 상세정보(detail)", description = "특정 회원의 정보를 제공합니다.",
                parameters = {@Parameter(name = "handle", description = "회원의 핸들 정보")})
    @GetMapping("/detail/{handle}")
    public ResponseEntity<?> detail(@PathVariable String handle) throws Exception {
        MemberDto memberDto = memberService.findByHandle(handle);
        return ResponseEntity.ok(new Result<>(true, OK.value(),"성공", memberDto));
    }

    @Operation(summary = "solved 기록 갱신",description = "모든 회원의 solved 기록을 갱신합니다. solved.ac API를 사용해 데이터를 갱신합니다.")
    @PostMapping("/update")
    public ResponseEntity<?> updateSolveData() throws Exception {
        List<MemberDto> all = memberService.findAll();
        for (MemberDto memberDto : all) {
            String handle = memberDto.getHandle();
            SolvedShowDto solvedShowData = solvedApiService.getSolvedShowData(handle);

            Member updatedMember = memberService.update(handle, solvedShowData);
            memberHistoryService.join(updatedMember, solvedShowData);
        }
        return ResponseEntity.ok(new Result<>(true, OK.value(),"성공", "성공"));
    }

    /**
     * 테스트용 API
     * members 에 사용자 핸들 정보가 있는 배열을 넣어주면 사용자를 등록해준다.
     *
     * @param members 모든 사용자의 핸들 정보
     */
    @Operation(summary = "테스트용 API",description = "멤버 회원 목록을 모두 업데이트 및 추가 하는 메소드")
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
        int code;
        String message;
        T data;
    }
}
