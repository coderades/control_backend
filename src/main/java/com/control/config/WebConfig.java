package com.control.config;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.control.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfig {

	@Autowired
	private final RoleService roleService;

	public WebConfig(RoleService roleService) {
		this.roleService = roleService;
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        roleService.findByHasAnyRole("f9a1da70-68a4-eb11-a3d3-6245b4ea43a3").forEach(method -> {
            try {
                if (Boolean.valueOf(method[2].toString())) {
                    http.authorizeHttpRequests(
                            authorize -> authorize.requestMatchers(HttpMethod.POST, method[1].toString() + "/**")
                                    .hasRole(method[0].toString()));
                    // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
                }

                if (Boolean.valueOf(method[3].toString())) {
                    http.authorizeHttpRequests(
                            authorize -> authorize.requestMatchers(HttpMethod.GET, method[1].toString() + "/**")
                                    .hasRole(method[0].toString()));
                }

                if (Boolean.valueOf(method[4].toString())) {
                    http.authorizeHttpRequests(
                            authorize -> authorize.requestMatchers(HttpMethod.PUT, method[1].toString() + "/**")
                                    .hasRole(method[0].toString()));
                }

                if (Boolean.valueOf(method[5].toString())) {
                    http.authorizeHttpRequests(
                            authorize -> authorize.requestMatchers(HttpMethod.DELETE, method[1].toString() + "/**")
                                    .hasRole(method[0].toString()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            log.info("Permissions: {}{}{}{} {} {}", Boolean.parseBoolean(method[2].toString()) ? "C" : "-",
                    Boolean.parseBoolean(method[3].toString()) ? "R" : "-",
                    Boolean.parseBoolean(method[4].toString()) ? "U" : "-",
                    Boolean.parseBoolean(method[5].toString()) ? "D" : "-", method[0].toString(), method[1].toString());
        });
        
    	http.httpBasic().and().authorizeHttpRequests().and().csrf().disable();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")).invalidateHttpSession(true));
        http.logout(logout -> logout.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));
        http.logout(logout -> logout.addLogoutHandler((request, response, auth) -> {
            MDC.put("sessionId", request.getSession().getId());
            try {
                log.info("OK from username {}", auth.getName());
            } catch (Exception e) {
                log.error("There is no active login for this session");
            }
        }));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
