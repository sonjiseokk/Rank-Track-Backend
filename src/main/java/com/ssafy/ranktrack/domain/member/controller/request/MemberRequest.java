package com.ssafy.ranktrack.domain.member.controller.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String handle;
    private String name;
}

