package com.bomp.service;

import java.util.List;

import com.bomp.domain.BoardVO;

public interface BoardViewService {
	public String boardSetView(List<BoardVO> boardList, int memberSId, String boardListDownEndToggle);
}
