package com.bomp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	private int first;
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 5;
		this.first = (pageNum-1)*amount;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.first = (pageNum-1)*amount;
	}
	
	public void setRNConAsChangePageNum(int pageNum) {
		this.pageNum = pageNum;
		this.first = (pageNum-1)*amount;
	}
}
