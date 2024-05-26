package com.ssafy.ranktrack.domain.member.service;

import com.ssafy.ranktrack.domain.member.entity.MemberHistory;
import com.ssafy.ranktrack.domain.member.repository.MemberHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberHistoryService {
    private final MemberHistoryRepository memberHistoryRepository;

    public List<MemberHistory> findAll() {
        return memberHistoryRepository.findAll();
    }
}
