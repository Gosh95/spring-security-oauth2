package com.example.oauth2.domain;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class OAuth2Props {

	/**
	 * common oauth2 properties
	 */
	public static final String EMAIL = "email";
	public static final String PROFILE_IMAGE = "profileImage";

	/**
	 * kakao oauth2 properties
	 */
	public static final String KAKAO = "kakao";
	public static final String PROFILE = "profile";
	public static final String KAKAO_ACCOUNT = "kakao_account";
	public static final String THUMBNAIL_IMAGE_URL = "thumbnail_image_url";

	/**
	 * google oauth2 properties
	 */
	public static final String GOOGLE = "google";
	public static final String PICTURE = "picture";
}
