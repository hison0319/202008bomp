package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomp.domain.FollowVO;
import com.bomp.persistence.FollowMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService{

	@Setter(onMethod_ = @Autowired)
	private FollowMapper mapper;
	
	@Override
	public void register(FollowVO follow) {
		mapper.insertSelectKey(follow);
	}

	@Override
	public FollowVO get(int followId) {
		return mapper.read(followId);
	}

	@Override
	public boolean modify(FollowVO follow) {
		return mapper.update(follow) == 1;
	}

	@Override
	public boolean remove(int followId) {
		return mapper.delete(followId) == 1;
	}

	@Override
	public List<FollowVO> getList() {
		return mapper.getList();
	}

}
