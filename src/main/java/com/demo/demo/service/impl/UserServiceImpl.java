package com.demo.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.User;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	protected EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		user.setDateCreated(LocalDate.now());
		return userRepository.save(user);
	}

	@SuppressWarnings("serial")
	@Override
	public List<User> findAll() {
		return userRepository.findAll(new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return null;
			}
		});
	}

	@Override
	public void delete(long userId) {
		userRepository.deleteById(userId);

	}

	@Override
	public User update(UserDto payload) {
		Optional<User> userOptional = userRepository.findById(payload.getUserId());
		if (userOptional.isPresent()) {
			User existingUser = userOptional.get();
			existingUser.setUserName(payload.getUserName());
			existingUser.setUserRole(payload.getUserRole());
			existingUser.setUserEmail(payload.getUserEmail());
			existingUser.setUserAddress(payload.getUserAddress());
			existingUser.setUserAge(payload.getUserAge());
			existingUser.setUserFirstName(payload.getUserFirstName());
			existingUser.setUserLastName(payload.getUserLastName());
			existingUser.setUserPhone(payload.getUserPhone());
			existingUser.setDateEdit(LocalDate.now());
			User updateUser = userRepository.save(existingUser);

			return updateUser;
		}
		return null;
	}

	@Override
	public Page<User> findByPaging(Pageable pageable, UserDto payload) {
		int maxPageSize = 10;// giới hạn pageSize
		// Kiểm tra và giới hạn pageSize
		int pageSize = pageable.getPageSize();
		if (pageSize > maxPageSize) {
			pageSize = maxPageSize;
		}

		Specification<User> spec = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (payload.getUserName() != null && !payload.getUserName().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("userName"), "%" + payload.getUserName() + "%"));
			}
			if (payload.getUserEmail() != null) {
				predicates.add(criteriaBuilder.like(root.get("userEmail"), "%" + payload.getUserEmail() + "%"));
			}
			if (payload.getUserRole() != null) {
				predicates.add(criteriaBuilder.like(root.get("userRole"), "%" + payload.getUserRole() + "%"));
			}
			if (payload.getUserAddress() != null) {
				predicates.add(criteriaBuilder.like(root.get("userAddress"), "%" + payload.getUserAddress() + "%"));
			}
			if (payload.getUserLastName() != null) {
				predicates.add(criteriaBuilder.like(root.get("userLastName"), "%" + payload.getUserLastName() + "%"));
			}
			if (payload.getUserFirstName() != null) {
				predicates.add(criteriaBuilder.like(root.get("userFirstName"), "%" + payload.getUserFirstName() + "%"));
			}
			if (payload.getUserPhone() != null) {
				predicates
						.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userPhone"), payload.getUserPhone())));
			}
			if (payload.getUserAge() != null) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userAge"), payload.getUserAge())));
			}
			if (payload.getDateCreatedStart() != null && payload.getDateCreatedEnd() != null) {
			    predicates.add(criteriaBuilder.between(root.get("dateCreated"), payload.getDateCreatedStart(), payload.getDateCreatedEnd()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		// tạo đối tượng pageable với pagesize được giới hạn và sort
		Pageable limitedPageable = PageRequest.of(pageable.getPageNumber(), pageSize);
		// thực hiện truy vấn phân trang với với đối tượng pageable đã được giới hạn
		Page<User> page = userRepository.findAll(spec, limitedPageable);
		return page;
	}

	@Override
	public List<User> sortBasedUponSomeField(String field) {

		return userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	@Override
	public Page<User> searchUser(UserDto dto, Pageable pageable) {
	    // Tạo một StringBuilder để xây dựng câu truy vấn JPQL
	    StringBuilder jpqlBuilder = new StringBuilder("SELECT a FROM User a WHERE 1=1 ");

	    // Tạo một HashMap để lưu trữ các tham số và giá trị tương ứng
	    HashMap<String, Object> queryParams = new HashMap<>();

	    if (dto.getUserName() != null && !dto.getUserName().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userName LIKE CONCAT('%', :userName, '%') ");
	        queryParams.put("userName", dto.getUserName());
	    }
	    if (dto.getUserFirstName() != null && !dto.getUserFirstName().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userFirstName LIKE CONCAT('%', :userFirstName, '%') ");
	        queryParams.put("userFirstName", dto.getUserFirstName());
	    }
	    if (dto.getUserLastName() != null && !dto.getUserLastName().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userLastName LIKE CONCAT('%', :userLastName, '%') ");
	        queryParams.put("userLastName", dto.getUserLastName());
	    }
	    if (dto.getUserEmail() != null && !dto.getUserEmail().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userEmail LIKE CONCAT('%', :userEmail, '%') ");
	        queryParams.put("userEmail", dto.getUserEmail());
	    }
	    if (dto.getUserAddress() != null && !dto.getUserAddress().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userAddress LIKE CONCAT('%', :userAddress, '%') ");
	        queryParams.put("userAddress", dto.getUserAddress());
	    }
	    if (dto.getUserRole() != null && !dto.getUserRole().trim().isEmpty()) {
	        jpqlBuilder.append("AND a.userRole LIKE CONCAT('%', :userRole, '%') ");
	        queryParams.put("userRole", dto.getUserRole());
	    }

	    // Thêm điều kiện tìm kiếm theo khoảng ngày
	    if (dto.getDateCreatedStart() != null && dto.getDateCreatedEnd() != null) {
	        jpqlBuilder.append("AND a.dateCreated BETWEEN :dateCreatedStart AND :dateCreatedEnd ");
	        queryParams.put("dateCreatedStart", dto.getDateCreatedStart());
	        queryParams.put("dateCreatedEnd", dto.getDateCreatedEnd());
	    }

	    // Tạo câu truy vấn TypedQuery từ câu truy vấn JPQL
	    TypedQuery<User> query = entityManager.createQuery(jpqlBuilder.toString(), User.class);

	    // Thiết lập các tham số trong câu truy vấn
	    for (String key : queryParams.keySet()) {
	        query.setParameter(key, queryParams.get(key));
	    }

	    // Lấy danh sách kết quả
	    List<User> users = query.getResultList();

	    // Xử lý phân trang
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), users.size());
	    List<User> usersPage;
	    if (end <= users.size()) {
	        usersPage = users.subList(start, end);
	    } else {
	        usersPage = users.subList(start, users.size());
	    }

	    // Trả về trang dữ liệu
	    return new PageImpl<>(usersPage, pageable, users.size());
	}
	
	@Transactional
	@Override
	public List<User> searchUsersPro(UserDto dto, int offset, int limit) {
	    List<User> users = userRepository.searchUserProc(
	        dto.getUserName(),
	        dto.getUserFirstName(),
	        dto.getUserLastName(),
	        dto.getUserEmail(),
	        dto.getUserAddress(),
	        dto.getUserRole(),
	        dto.getUserPhone(),
	        dto.getDateCreated(),
	        dto.getDateCreatedStart(),
	        dto.getDateCreatedEnd(),
	        offset,
	        limit
	    );

	    return users;
	}
}
