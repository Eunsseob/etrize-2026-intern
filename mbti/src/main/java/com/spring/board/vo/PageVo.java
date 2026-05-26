package com.spring.board.vo;

import java.util.List;

public class PageVo {
	
	private int pageNo = 0;

	// 필터링을 위해 추가 다중값을 받기 위해 List로 수정
	private List<String> boardTypeList;
	
	public List<String> getBoardTypeList() {
		return boardTypeList;
	}

	public void setBoardTypeList(List<String> boardTypeList) {
		this.boardTypeList = boardTypeList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}