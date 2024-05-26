package com.ssafy.ranktrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RankTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankTrackApplication.class, args);
    }

}
