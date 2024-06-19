package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<LoginVO, String> {
	@Query(value = "SELECT"
			+ "	IFNULL(EMPNO,'') EMPNO FROM XMT310"
			+ "	WHERE (EMPNO = :empno"
			+ "	AND USERPSWD = "
			+ "	:userpswdu OR USERPSWD = :userpswdl) "
			+ "	AND DEPTCOD IN ('1120')", nativeQuery = true)
	String login(@Param("empno") String empno, @Param("userpswdu") String userpswdu, @Param("userpswdl") String userpswdl);
}
