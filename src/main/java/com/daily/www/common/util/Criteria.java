package com.daily.www.common.util;


//======================================================
// public class Criteria
//======================================================
public class Criteria {
	
	private int page;	// 현재 페이지 번호
	private int perPageNum;	// 한 페이지당 보여줄 글의 개수
	
	private String id; // 회원 아이디
	
	private String list;	// 카테고리에 따른 리스트
	
	private String name;	// 경로이름
	
	private String detail;	// 상품 디테일
	
	public String getDetail() {
		return detail;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public int getPageStart() {
		return (this.page - 1) + perPageNum;
	}
	
	public Criteria() {			// 생성자
		this.page		= 1;	// 시작 페이지 번호
		this.perPageNum	= 20;	// 한 페이지당 보여줄 게시글의 수
	}
	
	
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	// 생성자에서 생성된 perPageNum 값이 아닌 다른 값으로 변경이 오면
	// 생성자에서 생성된 값으로 유지시킨다.
	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		
		if(pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}

	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
	
}
