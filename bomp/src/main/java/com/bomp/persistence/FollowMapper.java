package com.bomp.persistence;

import java.util.List;

import com.bomp.domain.FollowVO;

public interface FollowMapper {
public List<FollowVO> getList();
	
	public void insert(FollowVO follow);
	
	public void insertSelectKey(FollowVO follow);
	
	public FollowVO read(int followId);
	
	public int delete(int followId);
	
	public int update(FollowVO follow);
}
