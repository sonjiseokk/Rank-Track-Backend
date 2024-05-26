package com.ssafy.ranktrack.domain.member.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MemberRequest {
    private List<String> members;
}

