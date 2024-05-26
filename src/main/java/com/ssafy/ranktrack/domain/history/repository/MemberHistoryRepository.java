package com.ssafy.ranktrack.domain.history.repository;

import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}

