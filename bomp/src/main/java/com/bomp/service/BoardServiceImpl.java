package com.bomp.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bomp.domain.BoardTagVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;
import com.bomp.persistence.BoardListCategoryMapper;
import com.bomp.persistence.BoardListMapper;
import com.bomp.persistence.BoardMapper;
import com.bomp.persistence.BoardTagMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardListMapper boardListMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardListCategoryMapper boardListCategoryMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardTagMapper boardTagMapper;

	@Override
	public void register(BoardVO board) {
		boardMapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(int boardId) {
		return boardMapper.read(boardId);
	}
	
	@Override
	public int getBoardIdByCommentId(int commentId) {
		return boardMapper.getBoardIdByCommentId(commentId);
	}

	@Transactional
	@Override
	public boolean remove(int boardId) throws Exception {
		if(boardMapper.delete(boardId) == 1) {
			return true;
		} else {
			throw new Exception();			
		}
	}
	
	@Override
	public List<BoardVO> getListBestBoard() {
		return boardMapper.getListBestBoard();
	}

	@Override
	public List<BoardVO> getListLimitAndCategoryWithMemberNick(Criteria cri, String categoryName) {
		return boardMapper.getListLimitAndCategoryWithMemberNick(cri.getFirst(), cri.getAmount(), categoryName);
	}
	
	@Override
	public List<BoardVO> getListLimitWithMemberNick(Criteria cri) {
		return boardMapper.getListLimitWithMemberNick(cri.getFirst(), cri.getAmount());
	}

	@Override
	public int getCountWithCategory(String categoryName) {
		return boardMapper.getCountWithCategory(categoryName);
	}
	
	@Override
	public int getCount() {
		return boardMapper.getCount();
	}
	
	@Override
	public List<BoardVO> getListAllPopular(Criteria cri) {
		return boardListMapper.getListAllPopular(cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListAllUDate(Criteria cri) {
		return boardListMapper.getListAllUDate(cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListCategoryPopular(String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryPopular(categoryName, cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListCategoryUDate(String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryUDate(categoryName, cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListAllTagPopular(int memberId, Criteria cri) {
		return boardListMapper.getListAllTagPopular(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListAllNoneTagPopular(int memberId, Criteria cri) {
		return boardListMapper.getListAllNoneTagPopular(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListAllTagUDate(int memberId, Criteria cri) {
		return boardListMapper.getListAllTagUDate(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListAllNoneTagUDate(int memberId, Criteria cri) {
		return boardListMapper.getListAllNoneTagUDate(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListCategoryTagPopular(int memberId, String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryTagPopular(memberId, categoryName, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListCategoryNoneTagPopular(int memberId, String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryNoneTagPopular(memberId, categoryName, cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListCategoryTagUDate(int memberId, String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryTagUDate(memberId, categoryName, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListCategoryNoneTagUDate(int memberId, String categoryName, Criteria cri) {
		return boardListCategoryMapper.getListCategoryNoneTagUDate(memberId, categoryName, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getSearchTagPopular(String keyword, Criteria cri) {
		return boardListMapper.getSearchTagPopular(keyword, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getSearchNoneTagPopular(String keyword, Criteria cri) {
		return boardListMapper.getSearchNoneTagPopular(keyword, cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getSearchTagUDate(String keyword, Criteria cri) {
		return boardListMapper.getSearchTagUDate(keyword, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getSearchNoneTagUDate(String keyword, Criteria cri) {
		return boardListMapper.getSearchNoneTagUDate(keyword, cri.getFirst(), cri.getAmount());
	}
	
	@Override
	public List<BoardVO> getListMemberPopular(int memberId, Criteria cri) {
		return boardListMapper.getListMemberPopular(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListMemberUDate(int memberId, Criteria cri) {
		return boardListMapper.getListMemberUDate(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListMarkPopular(int memberId, Criteria cri) {
		return boardListMapper.getListMarkPopular(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public List<BoardVO> getListMarkUDate(int memberId, Criteria cri) {
		return boardListMapper.getListMarkUDate(memberId, cri.getFirst(), cri.getAmount());
	}

	@Override
	public int getCountAllTag(int memberId) {
		return boardListMapper.getCountAllTag(memberId);
	}
	
	@Override
	public int getCountSearchTag(String keyword) {
		return boardListMapper.getCountSearchTag(keyword);
	}

	@Override
	public int getCountCategoryTag(int memberId, String categoryName) {
		return boardListCategoryMapper.getCountCategoryTag(memberId, categoryName);
	}
	
	@Transactional
	@Override
	public String posting(BoardVO board, int memberId, String boardTag, MultipartFile imageFile) throws IllegalStateException, IOException {
		//이미지 업로드
		if (!imageFile.isEmpty()) {
			String imageAddress = imgUpload(memberId, imageFile);
			board.setImgAddress(imageAddress);
		}
		//보더  업데이트
		boardMapper.insertSelectKey(board);
		//보더 태그 업데이트
		if (!boardTag.equals("")) {
			boardTagMapper.insert(boardTagRegist(board.getBoardId(), boardTag));
		}
		//주소 반환
		if (board.getCategoryName().equals("토론방")) {
			return "/complete/comp_regist_discussion";
		}
		return "/complete/comp_regist_board";
	}
	
	@Transactional
	@Override
	public String modify(BoardVO board, int memberId, String boardTag, MultipartFile imageFile) throws IllegalStateException, IOException {
		String imageAddress = board.getImgAddress();
		//이미지 업로드
		if (!imageFile.isEmpty()) {
			imageAddress = imgUpload(memberId, imageFile);
			board.setImgAddress(imageAddress);
		}
		//보더  업데이트
		boardMapper.update(board);
		//보더 태그 업데이트
		if (!boardTag.equals("")) {
			BoardTagVO boardTagReg = boardTagRegist(board.getBoardId(), boardTag);
			if(boardTagMapper.read(board.getBoardId()) == null) {
				boardTagMapper.insert(boardTagReg);				
			} else {
				boardTagMapper.update(boardTagReg);				
			}
		}
		//주소 반환
		if (board.getCategoryName().equals("토론방")) {
			return "/complete/comp_regist_discussion";
		}
		return "/complete/comp_regist_board";
	}
	
	public String imgUpload(int memberId, MultipartFile imageFile) throws IllegalStateException, IOException {
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY_MM_dd")); // 폴더를 위한 현재 날짜
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ssSSS")); // 파일명을 위한 현재 시각
		String uploadFolder = "tomcat/webapps/ROOT/resources/images/userImages/"+today;
//notebook		String uploadFolder = "C:\\Users\\hison\\git\\202008bomp_project\\bombom_pedia\\src\\main\\webapp\\resources\\images\\userImages\\"+today;
//PC		String uploadFolder = "C:\\Users\\PC\\git\\202008bomp_project\\bombom_pedia\\src\\main\\webapp\\resources\\images\\userImages\\"+ today;
		String imageName = memberId + "-" + now + ".jpg";
		String imageAddress = "/images/userImages/" + today + "/" + imageName;
		
		File saveFile = new File(uploadFolder, imageName);
		saveFile.mkdirs();
		imageFile.transferTo(saveFile);
		
		return imageAddress;
	}
	
	public BoardTagVO boardTagRegist(int boardId, String boardTag) {
		List<String> tagList = new ArrayList<>();
		String[] tagArr = boardTag.split("#");
		for (int i = 1; i < tagArr.length; i++) {
			tagArr[i] = tagArr[i].replaceAll(" ", "");
			tagArr[i] = tagArr[i].replaceAll("\\p{Z}", "");
			tagArr[i] = tagArr[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			tagList.add(tagArr[i].trim());
		}
		tagList = tagList.stream().distinct().collect(Collectors.toList());
		tagList.remove("");
		BoardTagVO boardTagReg = new BoardTagVO();
		boardTagReg.setBoardId(boardId);
		switch ((tagList.size()>5)?5:tagList.size()) {
		case 5:
			boardTagReg.setTag5(tagList.get(4));
		case 4:
			boardTagReg.setTag4(tagList.get(3));
		case 3:
			boardTagReg.setTag3(tagList.get(2));
		case 2:
			boardTagReg.setTag2(tagList.get(1));
		case 1:
			boardTagReg.setTag1(tagList.get(0));
			break;
		default:
			break;
		}
		return boardTagReg;
	}
}
