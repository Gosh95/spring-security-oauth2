package com.example.oauth2.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Account {

	@Column(name = "email", unique = true, length = 30, nullable = false, updatable = false)
	private String email;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	private Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public static Account createAccount(String email, String password) {
		return new Account(email, password);
	}
}
