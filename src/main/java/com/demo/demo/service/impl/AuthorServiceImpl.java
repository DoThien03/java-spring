package com.demo.demo.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.demo.dto.AuthorDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.Author;
import com.demo.demo.entity.User;
import com.demo.demo.repository.AuthorRepository;
import com.demo.demo.service.AuthorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;




@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	protected EntityManager entityManager;
	
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> findAll() {
		return (List<Author>) authorRepository.findAll();
	}

	@Override
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);

	}

	@Override
	public Author update(AuthorDto payload) {
		Optional<Author> authorOptional = authorRepository.findById(payload.getId());
		if (authorOptional.isPresent()) {
			Author exAuthor = authorOptional.get();
			exAuthor.setName(payload.getName());
			exAuthor.setAge(payload.getAge());
			exAuthor.setNickName(payload.getNickName());

			Author updateAuthor = authorRepository.save(exAuthor);
			return updateAuthor;

		}

		return null;
	}

//	@Override
//	public List<Author> searchAuthor(AuthorDto dto) {
//	    List<Author> allAuthors = authorRepository.findAll();
//	    List<Author> filteredAuthors = new ArrayList<>();
//
//	    for (Author author : allAuthors) {
//	        if (matchesCriteria(author, dto)) {
//	            filteredAuthors.add(author);
//	        }
//	    }
//
//	    return filteredAuthors;
//	}
//
//	private boolean matchesCriteria(Author author, AuthorDto dto) {
//	    if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
//	        if (!author.getName().contains(dto.getName())) {
//	            return false;
//	        }
//	    }
//
//	    if (dto.getAge() != null ) {
//	        if (author.getAge() == null || author.getAge() < dto.getAge()) {
//	            return false;
//	        }
//	    }
//	    if (dto.getNickName() != null && !dto.getNickName().trim().isEmpty()) {
//	        if (!author.getNickName().equals(dto.getNickName())) {
//	            return false;
//	        }
//	    }
//
//	    return true;
//	}
//	
	
	

 @Override
	public Page<User> searchAuthor(UserDto dto,Pageable pageable) {
	 // tìm kiếm sử dụng stringbuilder và hashmap
	 HashMap<String, String> queryParams  = new HashMap<String, String>();
	 
		String jpql = "SELECT a FROM Author a WHERE 1=1 ";
		if (dto.getUserName() != null && !dto.getUserName().trim().isEmpty()) {
			jpql += "AND a.name like concat('%',:name,'%') ";
			queryParams .put("name", dto.getUserName());
		}
		if (dto.getUserFirstName()!= null && !dto.getUserFirstName().trim().isEmpty()) {
			jpql += "AND a.user_first_name = :user_first_name ";
			queryParams .put("user_first_name", dto.getUserFirstName());
		}
		if (dto.getUserLastName()!= null && !dto.getUserLastName().trim().isEmpty()) {
			jpql += "AND a.user_last_name = :user_last_name ";
			queryParams .put("last_name", dto.getUserLastName());
		}
		
		if (dto.getUserEmail()!= null && !dto.getUserEmail().trim().isEmpty()) {
			jpql += "AND a.user_email = :user_email ";
			queryParams .put("user_email", dto.getUserEmail());
		}
		
		if (dto.getUserAddress()!= null && !dto.getUserAddress().trim().isEmpty()) {
			jpql += "AND a.user_address = :user_address ";
			queryParams .put("user_address", dto.getUserAddress());
		}

		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	        for (String key : queryParams.keySet()) {
	            query.setParameter(key, queryParams.get(key));
	        }
		
		List<User> users = query.getResultList();
		 int start = (int) pageable.getOffset();
		 int end = Math.min((start + pageable.getPageSize()), users.size());
		 List<User> authorsPage;
		 if (end <= users.size()) {
		     authorsPage = users.subList(start, end);
		 } else {
		     authorsPage = users.subList(start, users.size());
		 }
		return new PageImpl<>(authorsPage, pageable, users.size());
	}

//	 @Override
//	 public Page<Author> findQueryAuthor(AuthorDto dto, Pageable pageable) {
//	
//	 int maxPageSize = 4;
//	 int pageSize = pageable.getPageSize();
//	 if(pageSize > maxPageSize) {
//	 pageSize = maxPageSize;
//	 }
//	
//	 Pageable page = PageRequest.of(pageable.getPageNumber(), pageSize);
//	
//	 Page<Author> author = authorRepository.searchFindByAuthor(dto.getName(),
//	 dto.getAge(), dto.getNickName(),
//	 page);
//	
//	
//	 return author;
//	 }

}
