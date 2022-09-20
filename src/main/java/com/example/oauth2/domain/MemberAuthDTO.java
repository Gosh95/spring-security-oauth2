package com.example.oauth2.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class MemberAuthDTO implements OAuth2User {

	private Long memberId;
	private String email;
	private String password;
	private String profileImage;
	private List<MemberRole> authorities;

	private MemberAuthDTO(
		Long memberId,
		Account account,
		String profileImage,
		List<MemberRole> authorities
	) {
		this.memberId = memberId;
		this.email = account.getEmail();
		this.password = account.getPassword();
		this.profileImage = profileImage;
		this.authorities = authorities;
	}

	public static MemberAuthDTO from(Member member) {
		return new MemberAuthDTO(
			member.getMemberId(), member.getAccount(), member.getProfileImage(), member.getAuthorities());
	}

	@Override
	public Map<String, Object> getAttributes() {
		return Collections.emptyMap();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).toList();
	}

	@Override
	public String getName() {
		return this.email;
	}
}
