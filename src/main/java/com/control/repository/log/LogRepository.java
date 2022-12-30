package com.control.repository.log;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.control.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

	List<Log> findByLogSessionId(String logSessionId);
	
	@Query("SELECT log FROM Log log WHERE (logSessionId = ?1 OR ?1 = null) AND (logCreated BETWEEN ?2 AND ?3) AND (logLevel = ?4 OR ?4 = '') AND (logClass = ?5 OR ?5 = '') AND (logMethod = ?6 OR ?6 = '') AND (logMessage LIKE %?7%)")
	List<Log> findByLog(String logSessionId, LocalDateTime logCreatedStart, LocalDateTime logCreatedEnd, String logLevel,
			String logClass, String logMethod, String logMessage);

}
