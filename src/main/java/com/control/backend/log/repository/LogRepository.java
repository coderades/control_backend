package com.control.backend.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.control.backend.log.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
