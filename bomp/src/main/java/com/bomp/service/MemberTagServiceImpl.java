package com.bomp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.MemberTagVO;
import com.bomp.persistence.MemberTagMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberTagServiceImpl implements MemberTagService{

	@Setter(onMethod_ = @Autowired)
	private MemberTagMapper mapper;
	
	@Transactional
	@Override
	public void register(int memberId, String memberTag) {		
		mapper.deleteWithMemberId(memberId);
		List<String> tagList = new ArrayList<>();
		String[] tagArr = memberTag.split("#");
		int ea = tagArr.length>10?10:tagArr.length;
		for (int i = 0; i < ea; i++) {
			tagArr[i] = tagArr[i].trim();
			tagArr[i] = tagArr[i].replaceAll(" ", "");
			tagArr[i] = tagArr[i].replaceAll("\\p{Z}", "");
			tagArr[i] = tagArr[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			if(!tagArr[i].equals("")) {
				tagList.add(tagArr[i]);
			}
		}
		for(String tL : tagList) {
			mapper.insert(new MemberTagVO(memberId,tL));			
		}
	}

	@Override
	public MemberTagVO get(int memberTagId) {
		return mapper.read(memberTagId);
	}

	@Override
	public boolean modify(MemberTagVO memberTag) {
		return mapper.update(memberTag) == 1;
	}

	@Override
	public boolean remove(int memberTagId) {
		return mapper.delete(memberTagId) == 1;
	}
	
	@Override
	public List<MemberTagVO> getList() {
		return mapper.getList();
	}

	@Override
	public List<MemberTagVO> getListWithMemberId(int memberId) {
		return mapper.getListWithMemberId(memberId);
	}

}
