package com.control.backend.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "[user]")
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 2187761020055803199L;

	@Id
	@Column(name = "user_id", columnDefinition = "uuid", nullable = false, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;

	@Column(name = "user_is_enabled", nullable = false)
	private Boolean userIsEnabled;

	@Column(name = "user_is_account_non_expired", nullable = false)
	private Boolean userIsAccountNonExpired;

	@Column(name = "user_is_account_non_locked", nullable = false)
	private Boolean userIsAccountNonLocked;

	@Column(name = "user_is_credentials_non_expired", nullable = false)
	private Boolean userIsCredentialsNonExpired;

	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String userName;

	@Column(name = "user_email", length = 50, nullable = true, unique = true)
	private String userEmail;

	@Column(name = "user_password", length = 70, nullable = false, unique = false, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private String userPassword;

	@Column(name = "user_password_token", columnDefinition = "uuid", nullable = true)
	private String userPasswordToken;

	@Column(name = "user_remember_token", columnDefinition = "uuid", nullable = true)
	private String userRememberToken;

	@Column(name = "user_pin_code", nullable = true)
	private Long userPinCode;

	@Column(name = "user_Logged_At", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime userLoggedAt;

	@Column(name = "user_created_at", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime userCreatedAt;

	@Column(name = "user_updated_at", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime userUpdatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final var authorities = new HashSet<GrantedAuthority>(roles.size());
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase())));
		return authorities;
	}

	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return userIsAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return userIsAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return userIsCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return userIsEnabled;
	}

}
