package com.bomp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MessageVO {
	private int messageId;
	private int fromMemberId;	//보내는이
	private int toMemberId;	//받는이
	private String messageContent;
	private Timestamp regDate;
	private String memberNick;
	
	public MessageVO(int fromMemberId, int toMemberId, String messageContent) {
		this.fromMemberId = fromMemberId;
		this.toMemberId = toMemberId;
		this.messageContent = messageContent;
	}
}
