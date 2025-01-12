package com.control.backend.auth.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "[user]")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 9115536194785563781L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true, updatable = false)
	private Long userId;

	@Column(name = "user_public_id", columnDefinition = "uuid", nullable = false, unique = true, updatable = false)
	private String userPublicId;

	@Column(name = "user_is_enabled", nullable = false)
	private Boolean userIsEnabled;

	@Column(name = "user_is_account_non_expired", nullable = false)
	private Boolean userIsAccountNonExpired;

	@Column(name = "user_is_account_non_locked", nullable = false)
	private Boolean userIsAccountNonLocked;

	@Column(name = "user_is_credentials_non_expired", nullable = false)
	private Boolean userIsCredentialsNonExpired;

	@Column(name = "user_name", length = 30, nullable = false, unique = true)
	private String userName;

	@JsonIgnore
	@ToString.Exclude
	@Column(name = "user_password", length = 70, nullable = false, unique = false, updatable = false)
	private String userPassword;

	@Email
	@Column(name = "user_email", length = 50, nullable = true, unique = true)
	private String userEmail;

	@DateTimeFormat
	@CreationTimestamp
	@Column(name = "user_created_at", nullable = false, insertable = true, updatable = false)
	private LocalDateTime userCreatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "user_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime userUpdatedAt;
	
	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "user_Logged_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime userLoggedAt;	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

}
