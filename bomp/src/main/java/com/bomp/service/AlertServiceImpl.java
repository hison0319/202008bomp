package com.bomp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.Criteria;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

	@Setter(onMethod_ = @Autowired)
	private AlertMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;

	@Transactional
	@Override
	public void register(AlertVO alert) {
		mapper.insert(alert);
		memberMapper.updateAlertPlus(alert.getAlertMemberId());
	}

	@Override
	public AlertVO get(int alertId) {
		return mapper.read(alertId);
	}

	@Override
	public boolean modifyChecked(int alertId) {
		return mapper.updateChecked(alertId) == 1;
	}

	@Override
	public boolean remove(int alertId) {
		return mapper.delete(alertId) == 1;
	}

	@Override
	public List<AlertVO> getListNoneChecked(int memberId, Criteria cri) {
		return mapper.getListNoneChecked(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<AlertVO> getListChecked(int memberId, Criteria cri) {
		return mapper.getListChecked(memberId, cri.getFirst(), cri.getAmount());
	}
	
	@Transactional
	@Override
	public List<AlertVO> alertViewSet(int memberId, String arrMethod, Criteria cri) {
		List<AlertVO> alertList = new ArrayList<>();
		if(arrMethod.equals("n")) {
			alertList = mapper.getListNoneChecked(memberId, cri.getFirst(), cri.getAmount());
			for(AlertVO aL : alertList) {
				mapper.updateChecked(aL.getAlertId());
				memberMapper.updateAlertMinus(memberId);
			}
		} else {
			alertList = mapper.getListChecked(memberId, cri.getFirst(), cri.getAmount());						
		}
		for(AlertVO aL : alertList) {
			if(aL.getKind() == 0) {
				aL.setAlertUserComment(aL.getAlertComment());
				aL.setBocoreId(0);
			} else {
				String[] strArr = aL.getAlertComment().split("@@@!");
				int bocoreId = Integer.parseInt(strArr[strArr.length-1]);
				aL.setAlertUserComment(aL.getAlertComment().replace("@@@!"+strArr[strArr.length-1], ""));
				aL.setBocoreId(bocoreId);
			}
		}
		return alertList;
	}

	@Override
	public AlertVO alertRegSet(int memberId, String alertOldComment, int alertMemberId, int bocoreId, int method) {
		AlertVO alert = new AlertVO();
		alert.setMemberId(memberId);
		alert.setAlertMemberId(alertMemberId);
		String alertComment = "";
		switch (method) {
		case 0:	//게시판 신고
			alertComment = "신고 접수!!! "+bocoreId+" 번 게시글 신고 접수되었습니다.";
			alert.setKind(0);
			break;
		case 1:	//댓글 신고
			alertComment = "신고 접수!!! "+bocoreId+" 번 댓글 신고 접수되었습니다.";
			alert.setKind(0);
			break;
		case 2:	//답글 신고
			alertComment = "신고 접수!!! "+bocoreId+" 번 답글 신고 접수되었습니다.";
			alert.setKind(0);
			break;
		case 3:	//게시판 댓글
			alertComment = setAlertComment(alertOldComment) + " 게시글에 댓글을 남겼습니다." + "@@@!" + bocoreId;
			alert.setKind(1);
			break;
		case 4:	//댓글 답글
			alertComment = setAlertComment(alertOldComment) + " 댓글에 답글을 남겼습니다." + "@@@!" + bocoreId;
			alert.setKind(2);
			break;
		case 5:	//게시판 좋아요
			alertComment = setAlertComment(alertOldComment) + " 게시글을 좋아합니다."+"@@@!" + bocoreId;
			alert.setKind(1);
			break;
		case 6:	//댓글 좋아요
			alertComment = setAlertComment(alertOldComment) + " 댓글을 좋아합니다."+"@@@!" + bocoreId;
			alert.setKind(2);
			break;
		case 7:	//답글 좋아요
			alertComment = setAlertComment(alertOldComment) + " 답글을 좋아합니다."+"@@@!" + bocoreId;
			alert.setKind(3);
			break;
		case 8:	//메세지
			alertComment = setAlertComment(alertOldComment) + " 내용의 메세지를 보냈습니다."+"@@@!";
			alert.setKind(4);
			break;
		case 9:	//메세지 신고
			alertComment = "신고 접수!!! "+bocoreId+" 번 메세지 신고 접수되었습니다.";
			alert.setKind(0);
			break;
		default:
			break;
		}
		alert.setAlertComment(alertComment);
		return alert;
	}

	public String setAlertComment(String alertText) {
		if (alertText.length() > 20) {
			alertText = alertText.substring(0, 19);
			alertText += "...";
		}
		return alertText;
	}

}
