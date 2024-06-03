package com.ssafy.ranktrack.domain.history.controller;

import com.ssafy.ranktrack.domain.api.solved.StageSolvedService;
import com.ssafy.ranktrack.domain.history.entity.dto.ExpectHistoryDto;
import com.ssafy.ranktrack.domain.history.entity.dto.MemberHistoryDto;
import com.ssafy.ranktrack.domain.history.entity.dto.StageSolvedCount;
import com.ssafy.ranktrack.domain.history.service.MemberHistoryService;
import com.ssafy.ranktrack.domain.member.entity.dto.MemberDto;
import com.ssafy.ranktrack.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberHistoryController {
    private final MemberHistoryService memberHistoryService;
    private final MemberService memberService;
    private final StageSolvedService stageSolvedService;
    @GetMapping("/{handle}/chart")
    public ResponseEntity<?> getDailyHistory(@PathVariable("handle") String handle) throws Exception {
        MemberDto memberDto = memberService.findByHandle(handle);
        List<MemberHistoryDto> histories = memberHistoryService.dailyHistory(memberDto.getId());
        List<StageSolvedCount> stageSolved = stageSolvedService.getStageSolvedCount(handle);
        List<ExpectHistoryDto> expectScores = memberHistoryService.expectThreeScore(histories);

        ChartData chartData = new ChartData(histories, stageSolved, expectScores);
        return ResponseEntity.ok(new Result<>(true,"성공", chartData));
    }

    @Data
    @AllArgsConstructor
    static class ChartData{
        List<?> dailyHistories;
        List<?> stageSolvedHistories;
        List<?> expectScores;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        boolean success;
        String message;
        T data;
    }
}
