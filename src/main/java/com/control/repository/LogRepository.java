package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.control.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

	List<?> findByLogSessionId(String logSessionId);

	List<?> findByLogLevel(String logLevel);

}
