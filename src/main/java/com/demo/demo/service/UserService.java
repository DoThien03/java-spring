package com.demo.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.User;



public interface UserService {
	User save(User user);

	void delete(long id);
	User update(UserDto payload);
	Page<User> findByPaging(Pageable pageable ,UserDto payload);
	List<User> sortBasedUponSomeField(String field);
	Page<User> searchUser(UserDto dto, Pageable pageable);
	List<User> searchUsersPro(UserDto dto, int offset, int limit);
	List<User> findAll();
}
