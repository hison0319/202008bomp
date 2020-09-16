package com.bomp.service;

import java.util.List;

import com.bomp.domain.FollowVO;

public interface FollowService {
	public void register(FollowVO follow);
	
	public FollowVO get(int followId);
	
	public boolean modify(FollowVO follow);
	
	public boolean remove(int followId);
	
	public List<FollowVO> getList();
}
