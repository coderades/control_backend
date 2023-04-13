package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.User;

//https://www.baeldung.com/spring-data-derived-queries
public interface UserRepository extends JpaRepository<User, String> {

	Boolean existsByUserName(String userName);

	Boolean existsByUserEmail(String userEmail);

	Boolean existsByUserIdIsNotAndUserName(String userId, String userName);

	Boolean existsByUserIdIsNotAndUserEmail(String userId, String userEmail);

	User findByUserName(String userName);

	User findByUserEmail(String userEmail);

	List<User> findByUserNameIgnoreCaseContaining(String userName);

	List<User> findByUserEmailIgnoreCaseContaining(String userName);

	List<User> findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(String userName, String userEmail);

	@Query("SELECT user FROM User user WHERE (userName LIKE %?1% OR userEmail LIKE %?1%) AND (userIsEnabled = ?2 OR NULL = ?2) AND (userIsAccountNonExpired = ?3 OR NULL = ?3) AND (userIsAccountNonLocked = ?4 OR NULL = ?4) AND (userIsCredentialsNonDiscredited = ?5 OR NULL = ?5) ORDER BY userName ASC")
	List<User> findByUser(String userName, Boolean userIsEnabled, Boolean userIsAccountNonExpired,
			Boolean userIsAccountNonLocked, Boolean userIsCredentialsNonDiscredited);

	@Query("SELECT userPassword FROM User user WHERE userId =?1")
	String findByUserPassword(String userId);

	@Modifying
	@Transactional
	@Query("UPDATE User user SET userPassword = ?2 WHERE userId = ?1")
	void updateUserPassword(String userId, String userPassword);

}
