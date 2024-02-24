package com.control_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.control_backend.component.SecurityFilter;
import com.control_backend.model.dto.ResourcePathDTO;
import com.control_backend.model.dto.RoleNameDTO;
import com.control_backend.service.ResourceService;
import com.control_backend.service.RoleService;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private SecurityFilter securityFilter;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	@Value("${api.security.token.access}")
	private String access;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(CsrfConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		for (var i : HttpMethod.values()) {
			final var patternsPupblic = patterns(HttpMethod.valueOf(i.toString()), true);
			if (patternsPupblic.length > 0) {
				httpSecurity.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.valueOf(i.toString()), patternsPupblic).permitAll());
			}

			final var patternsPrivate = patterns(HttpMethod.valueOf(i.toString()), false);
			if (patternsPrivate.length > 0) {
				final var roles = roles(HttpMethod.valueOf(i.toString()), false);
				if (roles.length > 0) {
					httpSecurity.authorizeHttpRequests(authorize -> authorize
							.requestMatchers(HttpMethod.valueOf(i.toString()), patternsPrivate).hasAnyRole(roles));
				}
			}
		}

		httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
		return httpSecurity.addFilterAfter(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
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

	public String[] patterns(HttpMethod httpMethod, Boolean permissionIsPublic) {
		final var findResourcePath = resourceService.findByResourcePath(access, httpMethod, permissionIsPublic);
		final String[] path = new String[findResourcePath.size()];
		
		Integer i = 0;

		for (ResourcePathDTO resourcePathDTO : findResourcePath) {
			path[i++] = resourcePathDTO.resourcePath();
			System.out.println("--> " + resourcePathDTO.resourcePath());
		}
		
		return path;
	}

	public String[] roles(HttpMethod httpMethod, Boolean permissionIsPublic) {
		final var findByPremissionResourceRole = roleService.findByPremissionResourceRole(access, httpMethod);
		final String[] role = new String[findByPremissionResourceRole.size()];
		
		Integer i = 0;

		for (RoleNameDTO roleNameDTO : findByPremissionResourceRole) {
			role[i++] = roleNameDTO.roleName().toUpperCase();
		}

		return role;
	}

}
