package com.devlp.patpatme.security;

import com.devlp.patpatme.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${patpatme.configuration.security.cors.allowed.origins}")
    private List<String> allowedCorsOrigins;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptManagerUtil bCryptManagerUtil;

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptManagerUtil.getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {

        return (request, response, exception) -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * Configure Spring Security's session management and filters
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/api/login")
                    .successForwardUrl("/api/auth/login/success")
                    .failureHandler(authenticationFailureHandler())
                .and()
                    .logout()
                    .logoutUrl("/api/auth/logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/**")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();

        http.csrf().disable();

        /* We create a session if needed, can only have 1 HTTP session at max, and if a session
        existed before, we migrate all the attributes from the old to the new session
        */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.sessionManagement().sessionFixation().none();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Needed for the client to send cookies to the server, like JSESSIONID
        corsConfiguration.setAllowCredentials(true);

        // Allow all headers (including customs like X-XSRF-TOKEN, used by the client to send CSRF tokens)
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setExposedHeaders(Collections.singletonList("filename"));

        // Allow below origins to make call to this server
        corsConfiguration.setAllowedOrigins(allowedCorsOrigins);
        corsConfiguration.setAllowedMethods(Arrays.asList(
                RequestMethod.GET.name(),
                RequestMethod.POST.name(),
                RequestMethod.PUT.name(),
                RequestMethod.PATCH.name(),
                RequestMethod.DELETE.name(),
                RequestMethod.HEAD.name(),
                RequestMethod.OPTIONS.name(),
                RequestMethod.TRACE.name()
        ));

        // Allow CORS for all endpoints if the origin is one defined in setAllowedOrigins
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
