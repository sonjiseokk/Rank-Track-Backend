package com.ssafy.ranktrack.domain.history.service;

import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import com.ssafy.ranktrack.domain.history.repository.MemberHistoryRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void join(final MemberHistory memberHistory) {
        memberHistoryRepository.save(memberHistory);
    }
}
