package com.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


public class OAuthConfigurer extends AuthorizationServerConfigurerAdapter {

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                .withClient("acme").secret("acme")
                .authorizedGrantTypes("authorization_code", "refresh_token").scopes("openid");
    }

    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
    }

    //@Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        return converter;
    }
}
