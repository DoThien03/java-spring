package com.demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.dto.AuthorDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.Author;
import com.demo.demo.entity.User;
import com.demo.demo.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@GetMapping("/listAuthor")
	public List<Author> findAll() {
		return authorService.findAll();

	}

	@PostMapping("/save")
	public Author save(@RequestBody Author author) {
		return authorService.save(author);

	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		authorService.delete(id);
	}

	@PutMapping("/update")
	public Author updateUser(@RequestBody AuthorDto payload) {
		return authorService.update(payload);
	}
//
//	@GetMapping("/searchAuthor")
//	public Page<Author> searchFindByAuthor(@RequestBody AuthorDto dto, Pageable pageable) {
//		return authorService.findQueryAuthor(dto,pageable);
//
//	}
	@GetMapping("/search")
	public Page<User> searchByAuthor(@RequestBody UserDto dto, Pageable pageable) {
		return authorService.searchAuthor(dto,pageable);

	}
}
