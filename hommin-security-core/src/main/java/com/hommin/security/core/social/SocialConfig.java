package com.hommin.security.core.social;

import com.hommin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

/**
 * @author Hommin
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	/**
	 * 使用数据库作为第三方账号的存储, 存储的表结构为JdbcUsersConnectionRepository.sql
	 *
	 * 若想自定义数据库表, 可以自己实现UsersConnectionRepository接口
	 *
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		if(connectionSignUp != null){
			connectionRepository.setConnectionSignUp(connectionSignUp);
		}
		return connectionRepository;
	}

	@Bean
	public SpringSocialConfigurer homminSocialSecurityConfig() {
		String filterProcessesUrl = securityProperties.getSocial().getQq().getFilterProcessesUrl();
		HomminSpringSocialConfigurer configurer = new HomminSpringSocialConfigurer(filterProcessesUrl, socialAuthenticationFilterPostProcessor);
		// 若没有注册, 跳转登录页面
		configurer.signupUrl(securityProperties.getSocial().getQq().getSignUpUrl());
		return configurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
		return new ProviderSignInUtils(connectionFactoryLocator
				, getUsersConnectionRepository(connectionFactoryLocator));
	}

	@Bean("connect/status")
	@ConditionalOnMissingBean(name = "connect/status")
	public View connectStatusView(){
		return new ConnectStatusView();
	}

}
