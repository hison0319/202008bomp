package com.bomp.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private List<Integer> pageList;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total, int nowPage) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 5.0))*5;
		this.startPage = this.endPage - 4;
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.pageList = new ArrayList<>();
		for(int i = this.startPage; i <= this.endPage; i++) {
			this.pageList.add(i);
		}
		
		this.prev = (nowPage!=1);
		this.next = (nowPage!=realEnd);
	}	
}
