package com.market.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@DynamicInsert
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 아이디
	 */
	@NotEmpty 
	@Size(max = 20)
	@Column(name = "userId", nullable = false)
	private String userId;
	
	/**
	 * 비밀번호
	 */
	@NotEmpty 
	@Size(max = 200)
	@Column(nullable = false)
	private String password;
	
	/**
	 * 이름
	 */

	@NotEmpty 
	@Size(max = 20)
	@Column(nullable = false)
	private String name;
	
	/**
	 * 이메일
	 */
	@NotEmpty 
	@Size(max = 200)
	@Column(nullable = false)
	private String email;
	
	/**
	 * 권한
	 */
	@Enumerated(EnumType.STRING)
	private Authority authority;
	
	/**
	 * 삭제여부
	 */
	@Column(nullable = false)
	private Boolean deleted;

	@Builder(builderClassName = "MemberSignUpBuilder", builderMethodName = "MemberSignUpBuilder")
	public Member(Long id, String userId, String password, String name, String email, Authority authority,
			Boolean deleted) {
		Assert.notNull(userId, "userId must not be null");
		Assert.notNull(password, "password must not be null");
		Assert.notNull(name, "name must not be null");
		Assert.notNull(email, "email must not be null");
		Assert.notNull(authority, "authority must not be null");
		Assert.notNull(deleted, "deleted must not be null");
	    
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.authority = authority;
		this.deleted = deleted;
	}
	
	
}
