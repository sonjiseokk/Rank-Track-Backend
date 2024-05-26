package com.ssafy.ranktrack.domain.member.repository;

import com.ssafy.ranktrack.domain.member.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {

}
