package com.ssafy.ranktrack.domain.member.repository;

import com.ssafy.ranktrack.domain.member.entity.MemberHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberHistoryRepository extends MongoRepository<MemberHistory, String> {
}

