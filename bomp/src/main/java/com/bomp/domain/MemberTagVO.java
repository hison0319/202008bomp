package com.bomp.domain;

import lombok.Data;

@Data
public class MemberTagVO {
	private int memberTagId;
	private int memberId;
	private String tag;
	
	public MemberTagVO(int memberId, String tag) {
		this.memberId = memberId;
		this.tag = tag;
	}
}
