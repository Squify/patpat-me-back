package com.devlp.patpatme.security;

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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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

//    @Autowired
//    private BCryptManagerUtil bCryptManagerUtil;

    @Value("${patpatme.configuration.security.cors.allowed.origins}")
    private List<String> allowedCorsOrigins;

//    // Cannot be private, else the server doesn't start
//    @Bean
//    DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(personService);
////        authenticationProvider.setPasswordEncoder(bCryptManagerUtil.getPasswordEncoder());
//        return authenticationProvider;
//    }

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

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }


    /**
     * Configure Spring Security's session management and filters
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /* We always create a session on receival of the first HTTP request, can only have 1 HTTP session at max, and if a session
        existed before, we migrate all the attributes from the old to the new session
        */
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        httpSecurity.sessionManagement().sessionFixation().none();

        httpSecurity
                .cors()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                // The login page is used for redirecting, since we are using routing on the frontend we will simply return a 200 OK response and let Angular handle redirections
                .loginPage("/api/generic/ok")
                .successForwardUrl("/api/auth/login/success")
                .failureForwardUrl("/api/generic/login/failure")
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .authenticated()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        //httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        httpSecurity.csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
//    }

}
