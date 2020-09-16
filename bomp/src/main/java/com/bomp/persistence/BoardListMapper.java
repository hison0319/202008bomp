package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bomp.domain.BoardVO;

public interface BoardListMapper {
	
	public List<BoardVO> getListAllPopular(@Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListAllUDate(@Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListAllTagPopular(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListAllNoneTagPopular(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListAllTagUDate(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListAllNoneTagUDate(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getSearchTagPopular(@Param("keyword")String keyword, @Param("first")int first, @Param("amount")int amount);

	public List<BoardVO> getSearchNoneTagPopular(@Param("keyword")String keyword, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getSearchTagUDate(@Param("keyword")String keyword, @Param("first")int first, @Param("amount")int amount);

	public List<BoardVO> getSearchNoneTagUDate(@Param("keyword")String keyword, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListMemberPopular(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListMemberUDate(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListMarkPopular(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListMarkUDate(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public int getCountAllTag(@Param("memberId")int memberId);

	public int getCountSearchTag(@Param("keyword")String keyword);
}
