package com.demo.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.demo.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//	@Query(value = "SELECT * From author au Where "
//     + "((:name is not null and au.name like concat('%', :name, '%' )) or :name is null) and"
//     + " ((:nick_name is not null and au.nick_name like concat('%', :nick_name, '%')) or :nick_name is null))"
//
//	,nativeQuery=true)
//
//	Page<Author> searchFindByAuthor(@Param("name") String name, @Param("age") Integer age,
//			@Param("nick_name") String nick_name, Pageable pageable);

}