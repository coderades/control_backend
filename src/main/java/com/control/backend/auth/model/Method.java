package com.control.backend.auth.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "[method]")
public class Method implements Serializable {

	private static final long serialVersionUID = -4217949257795664998L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "method_id", nullable = false, unique = true)
	private Long methodId;
	
	@Column(name = "method_is_enabled", nullable = false)
	private Boolean methodIsEnabled;
	
	@Column(name = "method_name", length = 10, nullable = false, unique = true)
	private String methodName;
	
	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "methods_created_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime methodsUpdatedAt;
	
	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "method_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime methodUpdatedAt;	

}
