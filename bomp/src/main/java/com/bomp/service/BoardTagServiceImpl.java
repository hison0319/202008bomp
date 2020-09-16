package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomp.domain.BoardTagVO;
import com.bomp.persistence.BoardTagMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardTagServiceImpl implements BoardTagService{

	@Setter(onMethod_ = @Autowired)
	private BoardTagMapper mapper;
	
	@Override
	public void register(BoardTagVO boardTag) {
		mapper.insertSelectKey(boardTag);
	}

	@Override
	public BoardTagVO get(int boardId) {
		return mapper.read(boardId);
	}

	@Override
	public boolean modify(BoardTagVO boardTag) {
		return mapper.update(boardTag) == 1;
	}

	@Override
	public boolean remove(int boardTagId) {
		return mapper.delete(boardTagId) == 1;
	}

	@Override
	public List<BoardTagVO> getList() {
		return mapper.getList();
	}

}
