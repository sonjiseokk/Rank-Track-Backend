package com.ssafy.ranktrack.domain.member.controller;

import com.ssafy.ranktrack.domain.member.controller.request.MemberRequest;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.member.service.MemberService;
import com.ssafy.ranktrack.domain.member.service.SolvedApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final SolvedApiService solvedApiService;
    private final MemberHistoryService memberHistoryService;

    @GetMapping("/list")
    public List getAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/history-list")
    public List getAllData() {
        return memberHistoryService.findAll();
    }


    /**
     * 테스트용 API
     * members 에 사용자 핸들 정보를 넣어주면 사용자를 등록해준다.
     *
     * @param members 모든 사용자의 핸들 정보
     */
    @PostMapping("/members")
    public void saveMembers(@RequestBody MemberRequest members) {
        for (String handle : members.getMembers()) {
            System.out.println("Received member ID: " + handle);

            Member makeMember = solvedApiService.getUserDataWithCookies(handle);
            memberService.join(makeMember);
        }
    }
}
