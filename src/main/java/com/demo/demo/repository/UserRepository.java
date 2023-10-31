package com.demo.demo.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.demo.entity.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

	List<User> findUserByuserNameStartingWith(String userName);

	List<User> findUserByuserRole(String userRole);

	List<User> findAll(Sort sort);

	@Procedure(procedureName = "example.search_user_procedures")
	List<User> searchUserProc(
	    @Param("p_user_name") String userName,
	    @Param("p_user_first_name") String userFirstName,
	    @Param("p_user_last_name") String userLastName,
	    @Param("p_user_email") String userEmail,
	    @Param("p_user_address") String userAddress,
	    @Param("p_user_Role") String userRole,
	    @Param("p_user_phone") String userPhone,
	    @Param("p_date_created") Date dateCreated,
	    @Param("p_date_created_start") LocalDate dateCreatedStart,
	    @Param("p_date_created_end") LocalDate dateCreatedEnd,
	    @Param("p_offset") int offset,
	    @Param("p_limit") int limit
	);



}
