package com.control.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "user_id", columnDefinition = "uuid", nullable = false, unique = true)
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

	@Column(name = "user_password", length = 70, nullable = false, unique = false)
	@JsonIgnore
	@ToString.Exclude
	private String userPassword;

	@Column(name = "user_password_token", columnDefinition = "uuid", nullable = true)
	private String userPasswordToken;

	@Column(name = "user_remember_token", columnDefinition = "uuid", nullable = true)
	private String userRememberToken;

	@Column(name = "user_pin_code", nullable = true)
	private Long userPinCode;

	@Column(name = "user_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime userCreated;

	@Column(name = "user_updated", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime userUpdated;

	@Column(name = "user_accessed", nullable = true, insertable = false, updatable = false)
	@DateTimeFormat
	private LocalDateTime userAccessed;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
