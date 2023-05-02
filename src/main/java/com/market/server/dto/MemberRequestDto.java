package com.market.server.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.market.server.entity.Authority;
import com.market.server.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
	private String userId;
    private String password;
    private String name;
    private String email;
    
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .authority(Authority.ROLE_USER)
                .deleted(false)
                .build();
    }
}
