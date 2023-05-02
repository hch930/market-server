package com.market.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.server.dto.MemberRequestDto;
import com.market.server.dto.MemberResponseDto;
import com.market.server.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Swagger")
@RestController
@RequiredArgsConstructor
public class SwaggerController {
	private final AuthService authService;
	
	@ApiOperation(value = "회원 등록", notes = "신규 회원 등록")
    @PostMapping("/signup")
	public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto requestDto){
		authService.createMember(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
