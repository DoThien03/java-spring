package com.demo.demo.dto;

import java.sql.Date;
import java.time.LocalDate;

public class UserDto {
	private Long userId;

	private String userName;

	private String userFirstName;

	private String userLastName;

	private String userRole;

	private String userEmail;

	private Integer userAge;

	private String userPhone;

	private String userAddress;

	private Date dateCreated;
	
	private LocalDate dateCreatedStart;
	
	private LocalDate dateCreatedEnd;
	
	private LocalDate dateEdit;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateCreatedStart() {
		return dateCreatedStart;
	}

	public void setDateCreatedStart(LocalDate dateCreatedStart) {
		this.dateCreatedStart = dateCreatedStart;
	}

	public LocalDate getDateCreatedEnd() {
		return dateCreatedEnd;
	}

	public void setDateCreatedEnd(LocalDate dateCreatedEnd) {
		this.dateCreatedEnd = dateCreatedEnd;
	}

	public LocalDate getDateEdit() {
		return dateEdit;
	}

	public void setDateEdit(LocalDate dateEdit) {
		this.dateEdit = dateEdit;
	}

	public UserDto(Long userId, String userName, String userFirstName, String userLastName, String userRole,
			String userEmail, Integer userAge, String userPhone, String userAddress, Date dateCreated,
			LocalDate dateCreatedStart, LocalDate dateCreatedEnd, LocalDate dateEdit) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.dateCreated = dateCreated;
		this.dateCreatedStart = dateCreatedStart;
		this.dateCreatedEnd = dateCreatedEnd;
		this.dateEdit = dateEdit;
	}
	
	
	
	
	
	

	

	
}
