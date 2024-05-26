package com.ssafy.ranktrack.domain.history.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ranktrack.Tier;
import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import com.ssafy.ranktrack.domain.history.entity.dto.StageSolvedCount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StageSolvedService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<StageSolvedCount> getStageSolvedCount(MemberHistory history, String handle) {
        String url = "https://solved.ac/api/v3/user/problem_stats?handle=" + handle;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return parseStageSolvedCounts(history, response.getBody());
    }

    private List<StageSolvedCount> parseStageSolvedCounts(MemberHistory history, String responseBody) {
        List<StageSolvedCount> stageSolvedCounts = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode stages = objectMapper.readTree(responseBody);

            for (JsonNode stage : stages) {
                int level = stage.path("level").asInt();
                int solved = stage.path("solved").asInt();

                Tier tier = Tier.fromValue(level);

                StageSolvedCount stageSolvedCount = StageSolvedCount.builder()
                        .tier(tier)
                        .solved(solved)
                        .build();

                stageSolvedCounts.add(stageSolvedCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stageSolvedCounts;
    }

}
