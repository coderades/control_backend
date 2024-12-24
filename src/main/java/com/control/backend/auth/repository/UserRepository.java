package com.control.backend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.control.backend.auth.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUserIdIsNotAndUserName(Long userId, String userName);

	Boolean existsByUserName(String userName);
	
	User findByUserName(String userName);

	List<User> findByUserNameIgnoreCaseContaining(String userName);

	@Modifying
	@Transactional
	@Query("UPDATE User user SET userPassword = ?2 WHERE userId = ?1")
	void updateUserPassword(Long userId, String userPassword);

	@Modifying
	@Transactional
	@Query("UPDATE User user SET userLoggedAt = CURRENT_TIMESTAMP WHERE (userId = ?1)")
	void updateLoggedAt(Long userId);

}
