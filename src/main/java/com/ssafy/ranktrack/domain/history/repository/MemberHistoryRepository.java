package com.ssafy.ranktrack.domain.history.repository;

import com.ssafy.ranktrack.domain.history.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
    @Query(value = "SELECT mh.* FROM member_history mh " +
                   "JOIN ( " +
                   "    SELECT member_id, DATE(timestamp) AS date, MIN(timestamp) AS min_timestamp " +
                   "    FROM member_history " +
                   "    WHERE member_id = :memberId " +
                   "    GROUP BY member_id, DATE(timestamp) " +
                   ") sub ON mh.member_id = sub.member_id AND mh.timestamp = sub.min_timestamp " +
                   "WHERE mh.member_id = :memberId", nativeQuery = true)
    List<MemberHistory> findDailyMemberHistory(@Param("memberId") Long memberId) throws SQLException;
}

