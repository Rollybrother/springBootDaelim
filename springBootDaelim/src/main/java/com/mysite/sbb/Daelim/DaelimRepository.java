package com.mysite.sbb.Daelim;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface DaelimRepository extends JpaRepository<DaelimVO,String>{
	@Query(value = "SELECT * FROM db_test", nativeQuery = true)
    List<DaelimVO> findAllFromDbTest();
}

