package com.github.arthurcech.financecontrol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Profile("oauth-security")
@Configuration
@EnableResourceServer
@SuppressWarnings("deprecation")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String CATEGORIAS = "/api/categorias/**";
    private static final String LANCAMENTOS = "/api/lancamentos/**";
    private static final String PESSOAS = "/api/pessoas/**";

    private final TokenStore tokenStore;

    public ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, CATEGORIAS)
                .access("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.GET, CATEGORIAS)
                .access("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, LANCAMENTOS)
                .access("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, LANCAMENTOS)
                .access("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.GET, LANCAMENTOS)
                .access("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.DELETE, LANCAMENTOS)
                .access("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.POST, PESSOAS)
                .access("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, PESSOAS)
                .access("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.GET, PESSOAS)
                .access("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.DELETE, PESSOAS)
                .access("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

}
