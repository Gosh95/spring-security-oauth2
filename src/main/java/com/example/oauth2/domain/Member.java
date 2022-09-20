package com.example.oauth2.domain;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "member_id", unique = true, nullable = false, updatable = false)
	private Long memberId;

	@Column(name = "profile_image", length = 200, nullable = false)
	private String profileImage;

	@Embedded
	private Account account;

	@Enumerated(STRING)
	@ElementCollection(fetch = LAZY)
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "member_id"))
	private List<MemberRole> authorities;

	private Member(String profileImage, Account account, List<MemberRole> authorities) {
		this.profileImage = profileImage;
		this.account = account;
		this.authorities = authorities;
	}

	public static Member registerMember(String profileImage, Account account, List<MemberRole> authorities) {
		return new Member(profileImage, account, authorities);
	}
}
