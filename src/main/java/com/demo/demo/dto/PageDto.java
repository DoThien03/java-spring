package com.demo.demo.dto;

import com.demo.demo.entity.User;

public class PageDto extends User {
	private int pageNumber;
	private int pageSize;
	private int totalPage;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNum(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public PageDto(int pageNumber, int pageSize, int totalPage) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
	}

}
