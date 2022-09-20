package com.example.oauth2.domain;

import static com.example.oauth2.domain.Account.createAccount;
import static com.example.oauth2.domain.Member.registerMember;
import static com.example.oauth2.domain.MemberRole.ROLE_MEMBER;
import static com.example.oauth2.domain.OAuth2Props.EMAIL;
import static com.example.oauth2.domain.OAuth2Props.PROFILE_IMAGE;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		var clientRegistration = userRequest.getClientRegistration();
		var clientName = clientRegistration.getClientName();
		var oauth2User = super.loadUser(userRequest);
		var oauth2Props = OAuth2PropMapper.of(clientName, oauth2User.getAttributes());

		if (oauth2Props.isEmpty()) {
			throw new OAuth2AuthenticationException("Couldn't find any props...");
		}

		var email = oauth2Props.get(EMAIL);
		var member = memberRepository.findByEmail(email)
			.orElseGet(() -> memberRepository.save(
				registerMember(
					oauth2Props.get(PROFILE_IMAGE),
					createAccount(email, passwordEncoder.encode(RandomString.make(8))),
					List.of(ROLE_MEMBER))));

		return MemberAuthDTO.from(member);
	}
}
