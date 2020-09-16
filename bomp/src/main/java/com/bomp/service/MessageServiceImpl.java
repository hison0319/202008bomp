package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MessageVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.MemberMapper;
import com.bomp.persistence.MessageMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

	@Setter(onMethod_ = @Autowired)
	private MessageMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public void register(MessageVO message, AlertVO alert) {
		mapper.insertSelectKey(message);
		alert.setAlertComment(alert.getAlertComment()+message.getMessageId());
		alertMapper.insert(alert);
		memberMapper.updateAlertPlus(alert.getAlertMemberId());
	}

	@Override
	public MessageVO get(int messageId) {
		return mapper.read(messageId);
	}

	@Override
	public boolean remove(int messageId) {
		return mapper.delete(messageId) == 1;
	}

	@Override
	public List<MessageVO> getList(int memberId, Criteria cri) {
		return mapper.getList(memberId, cri.getFirst(), cri.getAmount());
	}

}
