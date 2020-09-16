package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.MessageVO;

public interface MessageMapper {
	public List<MessageVO> getList(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public MessageVO read(@Param("messageId")int messageId);

	public void insertSelectKey(MessageVO message);
	
	public int delete(int messageId);
}
