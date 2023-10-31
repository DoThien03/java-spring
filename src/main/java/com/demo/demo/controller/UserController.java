package com.demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.User;
import com.demo.demo.service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@GetMapping("/listUser")
	public List<User> listUser() {
		return userService.findAll();
	}

	@DeleteMapping("/delete/{userId}")
	public void delete(@PathVariable("userId") Long userId) {
		userService.delete(userId);
	}

	@PutMapping("/update")
	public User updateUser(@RequestBody  UserDto payload) {
		return userService.update(payload);
	}

	@GetMapping("/query")
	public Page<User> findByPaging(@RequestBody UserDto payload, Pageable pageable) {
		return userService.findByPaging(pageable, payload);
	}

	@GetMapping("/sort/{field}")
	public List<User> sortUser(@PathVariable String field) {
		return userService.sortBasedUponSomeField(field);
	}

	@GetMapping("/search")
	public Page<User> searchByUser(@RequestBody UserDto dto, Pageable pageable) {
		return userService.searchUser(dto, pageable);

	}
	@GetMapping("/searchProcedures")
	public List<User> searchUserProcedures(@RequestBody UserDto dto,
	                                       @RequestParam(defaultValue = "0") int page,
	                                       @RequestParam(defaultValue = "3") int size) {
	    int offset = page * size;
	    int limit = size;
	    return userService.searchUsersPro(dto, offset, limit);
	}
}
