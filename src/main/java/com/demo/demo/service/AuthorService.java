package com.demo.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.demo.dto.AuthorDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.Author;
import com.demo.demo.entity.User;

public interface AuthorService {

	Author save(Author author);

	void delete(Long id);

	Author update(AuthorDto payload);

	List<Author> findAll();

	Page<User> searchAuthor(UserDto dto,Pageable pageable);

	//Page<Author> findQueryAuthor(AuthorDto dto, Pageable pageable);

}
