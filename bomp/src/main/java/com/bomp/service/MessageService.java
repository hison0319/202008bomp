package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MessageVO;

public interface MessageService {
	public void register(MessageVO message, AlertVO alert);
	
	public MessageVO get(int messageId);
	
	public boolean remove(int messageId);
	
	public List<MessageVO> getList(int memberId, Criteria cri);
}
