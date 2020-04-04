package com.nooble.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;


@Configuration
@EnableResourceServer

@Order(-10)
public class GatewayConfiguration
        extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();


    }
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        System.out.println("hello remote token");
        tokenService.setCheckTokenEndpointUrl(
                "http://localhost:8080/oauth/check_token");
        tokenService.setClientId("client");
        tokenService.setClientSecret("secret");
        return tokenService;
    }
//


}