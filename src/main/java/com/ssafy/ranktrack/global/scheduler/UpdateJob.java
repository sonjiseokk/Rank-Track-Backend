package com.ssafy.ranktrack.global.scheduler;

import com.ssafy.ranktrack.domain.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateJob implements Job {
    private final MemberUpdateService memberUpdateService;

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            memberUpdateService.updateSolveData();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
