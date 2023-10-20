package com.control.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.control.component.FilterTokenComponent;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private FilterTokenComponent filterTokenComponent;

//	@Bean
//	WebSecurityCustomizer ignoringCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/api/user/**");
//	}

	private static final String[] AuthPostPermitAll = {
			"/api/auth/*"
	};
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(CsrfConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "").permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, AuthPostPermitAll).permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/api/role/*").permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/api/userRoles/*").permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/api/user/*").permitAll().anyRequest().authenticated())
				.logout((logout) -> logout.logoutSuccessUrl("/").deleteCookies("JSESSIONID"))
				.addFilterBefore(filterTokenComponent, UsernamePasswordAuthenticationFilter.class).build();
		
		
//		roleRepository.findByHasAnyRole("f9a1da70-68a4-eb11-a3d3-6245b4ea43a3").forEach(method -> {	
//		
//		});
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
