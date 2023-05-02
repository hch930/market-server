package com.market.server.dto;

import com.market.server.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String userId;
    private String email;

    public static MemberResponseDto responseMember(Member member) {
        return MemberResponseDto.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .build();
    }
}