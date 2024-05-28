package com.ssafy.ranktrack.global.config;

import com.ssafy.ranktrack.global.scheduler.UpdateJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(UpdateJob.class)
            .withIdentity("updateJob")
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity("updateTrigger")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(24)
                .repeatForever())
            .build();
    }
}

