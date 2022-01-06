package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
//                .antMatchers("/v1/cozinhas/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/cozinhas/**").authenticated()
//                .antMatchers(HttpMethod.POST, "/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
//                .antMatchers(HttpMethod.PUT, "/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
//                .anyRequest().denyAll()
//                .anyRequest().authenticated()
                .csrf().disable()
                .cors().and()
                .oauth2ResourceServer()
//                .opaqueToken()
                .jwt()
                .jwtAuthenticationConverter(jwtAutheticationConverter());
    }

    private JwtAuthenticationConverter jwtAutheticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            var scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            Collection<GrantedAuthority> grantedAuthorities = scopesAuthoritiesConverter.convert(jwt);

            Collection<SimpleGrantedAuthority> collectionSimpleGrantedAuthority = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            grantedAuthorities.addAll(collectionSimpleGrantedAuthority);

            return grantedAuthorities;
        });
        return jwtAuthenticationConverter;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
