package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.Criteria;

public interface AlertService {
	public void register(AlertVO alert);
	
	public AlertVO get(int alertId);
	
	public boolean modifyChecked(int alertId);
	
	public boolean remove(int alertId);
	
	public List<AlertVO> getListNoneChecked(int memberId, Criteria cri);
	
	public List<AlertVO> getListChecked(int memberId, Criteria cri);
	
	public  List<AlertVO> alertViewSet(int memberId, String arrMethod, Criteria cri);
	
	public  AlertVO alertRegSet(int memberId, String alertOldComment, int alertMemberId, int bocoreId, int method);
}
