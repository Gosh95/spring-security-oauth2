package com.example.oauth2.domain;

import static com.example.oauth2.domain.OAuth2Props.EMAIL;
import static com.example.oauth2.domain.OAuth2Props.GOOGLE;
import static com.example.oauth2.domain.OAuth2Props.KAKAO;
import static com.example.oauth2.domain.OAuth2Props.KAKAO_ACCOUNT;
import static com.example.oauth2.domain.OAuth2Props.PROFILE;
import static com.example.oauth2.domain.OAuth2Props.PROFILE_IMAGE;
import static com.example.oauth2.domain.OAuth2Props.THUMBNAIL_IMAGE_URL;
import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class OAuth2PropMapper {

	public static Map<String, String> of(String clientName, Map<String, Object> attributes) {
		return switch (clientName) {
			case KAKAO -> getKakaoProps(attributes);
			case GOOGLE -> getGoogleProps(attributes);
			default -> Collections.emptyMap();
		};
	}

	private static Map<String, String> getKakaoProps(Map<String, Object> attributes) {
		var props = new HashMap<String, String>();
		var kakaoAccount = attributes.get(KAKAO_ACCOUNT);

		if (kakaoAccount instanceof Map<?, ?> account) {
			props.put(EMAIL, account.get(EMAIL).toString());

			if (account.get(PROFILE) instanceof Map<?, ?> profile) {
				props.put(PROFILE_IMAGE, profile.get(THUMBNAIL_IMAGE_URL).toString());
			}
		}

		return props;
	}

	private static Map<String, String> getGoogleProps(Map<String, Object> attributes) {
		return Collections.emptyMap();
	}
}
