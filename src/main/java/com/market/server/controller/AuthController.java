package com.market.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.server.dto.MemberRequestDto;
import com.market.server.dto.MemberResponseDto;
import com.market.server.service.AuthService;
import com.market.server.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	//private final MailService mailService;
	
	@PostMapping("/signup")
	public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto requestDto){
		authService.createMember(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@GetMapping("/confirmId/{userId}")
	public Boolean confirmId(@PathVariable String userId) {
		return authService.selectexistsById(userId);
	}
}
