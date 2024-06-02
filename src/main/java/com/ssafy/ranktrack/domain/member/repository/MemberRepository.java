package com.ssafy.ranktrack.domain.member.repository;

import com.ssafy.ranktrack.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByHandle(String handle) throws SQLException;

    @Query("SELECT m FROM Member m ORDER BY m.rating DESC")
    List<Member> findAllDesc() throws SQLException;
    @Query(value = "SELECT r.rank FROM (SELECT handle, RANK() OVER (ORDER BY rating DESC) AS `rank` FROM member) r WHERE r.handle = :handle", nativeQuery = true)
    Integer findRankByHandle(@Param("handle") String handle);
    List<Member> findNearByHandle(String handle) throws SQLException;

}
