package com.hommin.security.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成JWT的增强器
 *
 * @author Hommin
 * 2019年06月28日 5:22 PM
 */
public class MyJwtTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>(1);
		info.put("company", "galaxy");
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}