package com.control.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.log.model.Log;

public interface LogRepository extends JpaRepository<Log, String> {

}
