package com.zhangjun.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final @NonNull AuthenticationManager authenticationManager;
    private final @NonNull ClientDetails clientDetails;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        String password1 = passwordEncoder.encode("oauth2");
//        builder.withClient("oauth2")
//                .secret(password1)
//                .resourceIds("oauth2")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("ROLE_ADMIN","ROLE_USER")
//                .scopes("all")
//                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .redirectUris("http://example.com");
//
//        String password2 = passwordEncoder.encode("test");
//        builder.withClient("test")
//                .secret(password2)
//                .resourceIds("test")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("ROLE_ADMIN","ROLE_USER")
//                .scopes("all")
//                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .redirectUris("http://example.com");
            InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
            for (BaseClientDetails client:clientDetails.getClient())
            {
                ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder= builder.withClient(client.getClientId());
                clientBuilder
                        .secret(client.getClientSecret())
                        .resourceIds(client.getResourceIds().toArray(new String[0]))
                        .authorizedGrantTypes(client.getAuthorizedGrantTypes().toArray(new String[0]))
                        .authorities(AuthorityUtils.authorityListToSet(client.getAuthorities()).toArray(new String[0]))
                        .scopes(client.getScope().toArray(new String[0]));
                if (client.getAutoApproveScopes() !=null)
                {
                    clientBuilder.autoApprove(client.getAutoApproveScopes().toArray(new String[0]));
                }

                if (client.getAccessTokenValiditySeconds() !=null)
                {
                    clientBuilder.accessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
                }

                if (client.getRefreshTokenValiditySeconds() !=null)
                {
                    clientBuilder.refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
                }

                if (client.getRegisteredRedirectUri() !=null)
                {
                    clientBuilder.redirectUris(client.getRegisteredRedirectUri().toArray(new String[0]));
                }

            }

    }
}
