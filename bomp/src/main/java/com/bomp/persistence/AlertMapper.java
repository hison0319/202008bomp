package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.AlertVO;

public interface AlertMapper {
	public List<AlertVO> getListNoneChecked(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public List<AlertVO> getListChecked(@Param("memberId")int memberId, @Param("first")int first, @Param("amount")int amount);
	
	public void insert(AlertVO alert);
	
	public AlertVO read(int alertId);
	
	public int delete(int alertId);
	
	public int updateChecked(int alertId);
}
