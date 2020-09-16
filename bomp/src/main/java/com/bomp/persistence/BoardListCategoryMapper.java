package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bomp.domain.BoardVO;

public interface BoardListCategoryMapper {
	public List<BoardVO> getListCategoryPopular(@Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListCategoryUDate(@Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListCategoryTagPopular(@Param("memberId")int memberId, @Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListCategoryNoneTagPopular(@Param("memberId")int memberId, @Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListCategoryTagUDate(@Param("memberId")int memberId, @Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListCategoryNoneTagUDate(@Param("memberId")int memberId, @Param("categoryName")String categoryName, @Param("first")int first, @Param("amount")int amount);
	
	public int getCountCategoryTag(@Param("memberId")int memberId, @Param("categoryName")String categoryName);
}
