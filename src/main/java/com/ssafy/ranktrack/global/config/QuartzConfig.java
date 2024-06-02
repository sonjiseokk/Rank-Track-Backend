package com.ssafy.ranktrack.global.config;

import com.ssafy.ranktrack.global.scheduler.UpdateJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class QuartzConfig {
    @Value("${scheduler.enabled}")
    private boolean isSchedulerEnabled;

    @Bean
    public JobDetail jobDetail() {
        if (!isSchedulerEnabled) {
            return null;
        }
        return JobBuilder.newJob(UpdateJob.class)
                .withIdentity("updateJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        if (!isSchedulerEnabled) {
            return null;
        }
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("updateTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();
    }
}

