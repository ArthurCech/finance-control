package com.github.arthurcech.financecontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.github.arthurcech.financecontrol.config.RolesConstants.CADASTRAR_CATEGORIA;
import static com.github.arthurcech.financecontrol.config.RolesConstants.CADASTRAR_LANCAMENTO;
import static com.github.arthurcech.financecontrol.config.RolesConstants.CADASTRAR_PESSOA;
import static com.github.arthurcech.financecontrol.config.RolesConstants.PESQUISAR_CATEGORIA;
import static com.github.arthurcech.financecontrol.config.RolesConstants.PESQUISAR_LANCAMENTO;
import static com.github.arthurcech.financecontrol.config.RolesConstants.PESQUISAR_PESSOA;
import static com.github.arthurcech.financecontrol.config.RolesConstants.REMOVER_LANCAMENTO;
import static com.github.arthurcech.financecontrol.config.RolesConstants.REMOVER_PESSOA;

@Profile("basic-security")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigBasic extends WebSecurityConfigurerAdapter {

    private static final String CATEGORIAS = "/api/categorias/**";
    private static final String LANCAMENTOS = "/api/lancamentos/**";
    private static final String PESSOAS = "/api/pessoas/**";

    private final UserDetailsService userDetailsService;

    public SecurityConfigBasic(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/**");
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, CATEGORIAS).hasAuthority(CADASTRAR_CATEGORIA)
                .antMatchers(HttpMethod.GET, CATEGORIAS).hasAuthority(PESQUISAR_CATEGORIA)
                .antMatchers(HttpMethod.POST, LANCAMENTOS).hasAuthority(CADASTRAR_LANCAMENTO)
                .antMatchers(HttpMethod.PUT, LANCAMENTOS).hasAuthority(CADASTRAR_LANCAMENTO)
                .antMatchers(HttpMethod.GET, LANCAMENTOS).hasAuthority(PESQUISAR_LANCAMENTO)
                .antMatchers(HttpMethod.DELETE, LANCAMENTOS).hasAuthority(REMOVER_LANCAMENTO)
                .antMatchers(HttpMethod.POST, PESSOAS).hasAuthority(CADASTRAR_PESSOA)
                .antMatchers(HttpMethod.PUT, PESSOAS).hasAuthority(CADASTRAR_PESSOA)
                .antMatchers(HttpMethod.GET, PESSOAS).hasAuthority(PESQUISAR_PESSOA)
                .antMatchers(HttpMethod.DELETE, PESSOAS).hasAuthority(REMOVER_PESSOA)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
