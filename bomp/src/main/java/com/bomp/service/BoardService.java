package com.bomp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(int boardId);
	
	public List<BoardVO> getListBestBoard();
	
	public int getBoardIdByCommentId(int commentId);

	public boolean remove(int boardId) throws Exception;
	
	public List<BoardVO> getListLimitAndCategoryWithMemberNick(Criteria cri, String categoryName);
	
	public List<BoardVO> getListLimitWithMemberNick(Criteria cri);
	
	public int getCountWithCategory(String categoryName);
	
	public int getCount();
	
	public List<BoardVO> getListAllPopular(Criteria cri);
	
	public List<BoardVO> getListAllUDate(Criteria cri);
	
	public List<BoardVO> getListCategoryPopular(String categoryName, Criteria cri);
	
	public List<BoardVO> getListCategoryUDate(String categoryName, Criteria cri);
	
	public List<BoardVO> getListAllTagPopular(int memberId, Criteria cri);
	
	public List<BoardVO> getListAllNoneTagPopular(int memberId, Criteria cri);
	
	public List<BoardVO> getListAllTagUDate(int memberId, Criteria cri);
	
	public List<BoardVO> getListAllNoneTagUDate(int memberId, Criteria cri);
	
	public List<BoardVO> getListCategoryTagPopular(int memberId, String categoryName, Criteria cri);
	
	public List<BoardVO> getListCategoryNoneTagPopular(int memberId, String categoryName, Criteria cri);
	
	public List<BoardVO> getListCategoryTagUDate(int memberId, String categoryName, Criteria cri);
	
	public List<BoardVO> getListCategoryNoneTagUDate(int memberId, String categoryName, Criteria cri);
	
	public List<BoardVO> getSearchTagPopular(String keyword, Criteria cri);

	public List<BoardVO> getSearchNoneTagPopular(String keyword, Criteria cri);
	
	public List<BoardVO> getSearchTagUDate(String keyword, Criteria cri);

	public List<BoardVO> getSearchNoneTagUDate(String keyword, Criteria cri);
	
	public List<BoardVO> getListMemberPopular(int memberId, Criteria cri);
	
	public List<BoardVO> getListMemberUDate(int memberId, Criteria cri);
	
	public List<BoardVO> getListMarkPopular(int memberId, Criteria cri);
	
	public List<BoardVO> getListMarkUDate(int memberId, Criteria cri);
	
	public int getCountAllTag(int memberId);
	
	public int getCountCategoryTag(int memberId, String categoryName);
	
	public int getCountSearchTag(String keyword);
	
	public String posting(BoardVO board, int memberId, String boardTag, MultipartFile imageFile) throws IllegalStateException, IOException; 
	
	public String modify(BoardVO board, int memberId, String boardTag, MultipartFile imageFile) throws IllegalStateException, IOException;
}
