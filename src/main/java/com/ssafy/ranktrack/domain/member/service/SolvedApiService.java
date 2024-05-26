package com.ssafy.ranktrack.domain.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ranktrack.Tier;
import com.ssafy.ranktrack.domain.member.entity.Member;
import com.ssafy.ranktrack.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SolvedApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("solvedac.cookie")
    private String cookieValue;

    public Member getUserDataWithCookies(String handle) {
        String url = "https://solved.ac/api/v3/user/show?handle=" + handle;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "auth-token=" + cookieValue);
        headers.add("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return parseMemberFromResponse(response.getBody());
    }

        private Member parseMemberFromResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);

            String handle = root.path("handle").asText();
            String profileImageUrl = root.path("profileImageUrl").asText("");
            Long solvedCount = root.path("solvedCount").asLong();
            Long rating = root.path("rating").asLong();
            int tierValue = root.path("tier").asInt();
            Tier tier = Tier.fromValue(tierValue);

            return Member.builder()
                    .handle(handle)
                    .profileImageUrl(profileImageUrl)
                    .solvedCount(solvedCount)
                    .rating(rating)
                    .tier(tier)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
