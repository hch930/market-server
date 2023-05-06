package com.market.server.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.market.server.dto.MemberRequestDto;
import com.market.server.dto.MemberResponseDto;
import com.market.server.entity.Member;
import com.market.server.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public MemberResponseDto createMember(MemberRequestDto requestDto) {
		if (memberRepository.existsByUserId(requestDto.getUserId())) {
            throw new RuntimeException("이미 가입된 아이디입니다");
        }
		
		Member member = requestDto.toMember(passwordEncoder);
		return MemberResponseDto.responseMember(memberRepository.save(member));
	}
	
	public Boolean selectexistsById(String userId) {
		Boolean existById = false;
		
		if (memberRepository.existsByUserId(userId)) {
			existById = true;
        }
		
		return existById;
	}
}
