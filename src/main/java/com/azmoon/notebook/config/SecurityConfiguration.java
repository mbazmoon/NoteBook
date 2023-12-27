package com.azmoon.notebook.config;

import com.azmoon.notebook.filter.CustomAuthenticationFilter;
import com.azmoon.notebook.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    private static final String[] AUTH_WHITELIST = {
            "/v1/login/**",
            "/v1/token/refresh/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };


    @Bean()
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationConfiguration.getAuthenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/v1/login");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> {
                            authz.requestMatchers(AUTH_WHITELIST).permitAll();
                            authz.requestMatchers(HttpMethod.GET, "/v1/user/**").hasAnyAuthority("ROLE_USER");
                            authz.requestMatchers(HttpMethod.POST, "/v1/user/**").hasAnyAuthority("ROLE_ADMIN");
                            authz.anyRequest().authenticated();
                        }
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(customAuthenticationFilter);

        return http.build();
    }

}
