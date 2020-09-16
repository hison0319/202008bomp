window.onload = function() {
	startPageNum = getParameterByName("pageNum");
	showBoardId = getParameterByName("boardId");
	arrMethod = getParameterByName("arrMethod");
	downPageNum = (startPageNum!="")?startPageNum:1;
	upPageNum = (startPageNum!="")?startPageNum:1;
	arrMethod = (!isEmpty(arrMethod))?arrMethod:"p";
	getBoardList(downPageNum,showBoardId,true,arrMethod);
	
	$("#arraySelect").on("change",function() { //순서정렬 기능
		let method = $("#arraySelect option:selected").val()
		if(method == "p") {
			location.replace("list?arrMethod=p");
		} else {
			location.replace("list?arrMethod=u");
		}
	});
}

$(window).scroll(function() {
	var currentScrollTop = $(window).scrollTop();
	
	if(currentScrollTop - lastScrollTop > 0) { // 다운스크롤
		lastScrollTop = currentScrollTop;
		if(!boardListDownEnd && (currentScrollTop >= $(document).height() - $(window).height() - 500)) {
			if(!downScrolling) {
			return false;
			}
			downPageNum++;
			getBoardList(downPageNum,"",true,arrMethod);
		}
	} else { // 업스크롤
		lastScrollTop = currentScrollTop;
		if(!boardListUpEnd && currentScrollTop < 800) {
			if(!upScrolling) {
			return false;
			}
			upPageNum--;
			getBoardList(upPageNum,"",false,arrMethod);
		}
	}
});


function getBoardList(pageNum,showBoardId,toggle,arrMethod) {
	if(!ajaxClick) {
		return false;
	}
	downScrolling = false;
	upScrolling = false;
	ajaxClick = false;
	arrMethod = (!isEmpty(arrMethod))?arrMethod:"p";
	var data = {pageNum:pageNum,arrMethod:arrMethod};
	var boardVal = [];
	var boardUDateVal = [];
	var commentUDateListVal = [];
	var memberTagArr = [];
	var boardListDownEndToggle;
	$("#loader").removeClass("skip");
	$.ajax({
		url : "getList",
		type : "GET",
		data : data,
		dataType : "json",
		success : function(data) {
	       	if (data.code == "OK") {
				if(toggle) {
					$('#boardList').append("<li style='padding: 0;'><ul style='padding: 0;' id='boardListBox"+pageNum+"'></ul></li>");			
				} else {
					$('#boardList').prepend("<li style='padding: 0;'><ul style='padding: 0;' id='boardListBox"+pageNum+"'></ul></li>");								
				}
				boardVal = data.boardList ;
				boardUDateVal = data.boardUDateList;
				commentUDateListVal = data.commentUDateListArr;
				memberId = data.memberId;
				memberTagArr = data.memberTagList;
				boardListDownEndToggle = data.boardListDownEndToggle;
				for(let i=0; i<boardVal.length; i++) {
					let boardId = boardVal[i].boardId;
					let categoryName = boardVal[i].categoryName;
					let title = boardVal[i].title;
					let boMemberId = boardVal[i].memberId;
					let content = boardVal[i].content;
					let imgAddress = boardVal[i].imgAddress;
					let boLikeCnt = boardVal[i].likeCnt;
					let commentCnt = boardVal[i].commentCnt;
					let boMemberNick = boardVal[i].memberNick;
					let imgAlt = boardVal[i].imgAlt;
					let boLiked = boardVal[i].liked;
					let boardTag = boardVal[i].boardTag;
					let marked = boardVal[i].marked;
					let commentList = boardVal[i].commentList;
					let boardUDate = boardUDateVal[i];
					let commentUDateList = commentUDateListVal[i];
					//Board HTML
					let htmlBoardListStart = "<br><li style='padding: 0;' id='boardList"+boardId+"'><div class='post'>"
					let htmlImage = "";
					if(!isEmpty(imgAddress)) {
						htmlImage = "<span class='image'><img src='"+imgAddress+"' alt='"+imgAlt+"' /></span>";				
					}
					let htmlPostStart = "<div class='content'>";
					
					let htmlBookMarkStart = "<div id='boardMarkBox"+boardId+"' class='optionBox' style='float: right;'>";
					let htmlBookMark1 = "<a href='javascript:return false;' onclick='markBoard("+boardId+","+memberId+","+marked+")'>";
					let htmlBookMark2 = "";
					if(marked) {
						htmlBookMark2 = "<span class='icon fas fa-bookmark' style='color:#ffd700; font-size:1.2em;'></span></a>";
					} else {
						htmlBookMark2 = "<span class='icon fas fa-bookmark'></span></a>";
					}
					let htmlBookMarkEnd = "</div>";
					
					let htmlCategory = "<p class='category' style='font-size:0.8em; color:#999'>"+categoryName+"</p>";
					let htmlBoMemberNick = "<a href='javascript:return false;' style='font-weight:bold;' onclick='showMember("+boMemberId+")'><span>"+boMemberNick+"</span></a>";
					let htmlBoardUDate = "<span class='postDate'>&nbsp;&nbsp;최근 활동 시간&nbsp;:&nbsp;"+boardUDate+"</span>";
					let htmlBoardTitle = "<h3 class='comment'>"+title+"</h3>";
					let htmlBoardContent = "<p class='comment'><a href='javascript:return false;' onclick='comment("+boardId+","+pageNum+")'>"+content+"</a></p>";
					let htmlBoardTag = "";
					if(boardTag!=null) {
						let tag1 = boardTag.tag1;
						let tag2 = boardTag.tag2;
						let tag3 = boardTag.tag3;
						let tag4 = boardTag.tag4;
						let tag5 = boardTag.tag5;
						let htmlBoardTagStart = "<ul>";
						let htmlTag1 = "";
						let htmlTag2 = "";
						let htmlTag3 = "";
						let htmlTag4 = "";
						let htmlTag5 = "";
						let dd = "'";
						if(tag1!=null) {
							htmlTag1 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag1+`')"><span class="tag">#`+tag1+`</span></a></li>`;
							for(let j=0;j<memberTagArr.length; j++) {
								if(memberTagArr[j].tag==tag1) {
									htmlTag1 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag1+`')"><span class="tag" style="color: #ffd700; font-size:1.1em;">#`+tag1+`</span></a></li>`;
									break;							
								}
							}
						}
						if(tag2!=null) {
							htmlTag2 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag2+`')"><span class="tag">#`+tag2+`</span></a></li>`;
							for(let j=0;j<memberTagArr.length; j++) {
								if(memberTagArr[j].tag==tag2) {
									htmlTag2 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag2+`')"><span class="tag" style="color: #ffd700; font-size:1.1em;">#`+tag2+`</span></a></li>`;
									break;							
								}
							}
						}		
						if(tag3!=null) {
							htmlTag3 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag3+`')"><span class="tag">#`+tag3+`</span></a></li>`;
							for(let j=0;j<memberTagArr.length; j++) {
								if(memberTagArr[j].tag==tag3) {
									htmlTag3 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag3+`')"><span class="tag" style="color: #ffd700; font-size:1.1em;">#`+tag3+`</span></a></li>`;
									break;							
								}
							}
						}	
						if(tag4!=null) {
							htmlTag4 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag4+`')"><span class="tag">#`+tag4+`</span></a></li>`;
							for(let j=0;j<memberTagArr.length; j++) {
								if(memberTagArr[j].tag==tag4) {
									htmlTag4 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag4+`')"><span class="tag" style="color: #ffd700; font-size:1.1em;">#`+tag4+`</span></a></li>`;
									break;							
								}
							}
						}
						if(tag5!=null) {
							htmlTag5 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag5+`')"><span class="tag">#`+tag5+`</span></a></li>`;
							for(let j=0;j<memberTagArr.length; j++) {
								if(memberTagArr[j].tag==tag5) {
									htmlTag5 = `<li><a href="javascript:return false;" onclick="searchKeyword('`+tag5+`')"><span class="tag" style="color: #ffd700; font-size:1.1em;">#`+tag5+`</span></a></li>`;
									break;							
								}
							}
						}
						let htmlBoardTagEnd = "</ul>"
						htmlBoardTag = htmlBoardTagStart+htmlTag1+htmlTag2+htmlTag3+htmlTag4+htmlTag5+htmlBoardTagEnd;
					}
					let htmlModifyBtn = "";
					let htmlRemoveBtn = "";
					if(memberId == boMemberId || (memberId <= 5 && memberId != 0)) {
						htmlModifyBtn = "<button class='button' style='box-shadow: none; border: none;'"+
									" onclick='update("+boardId+", "+boLikeCnt+", "+commentCnt+")'>"+
									"<span class='icon solid far fa-keyboard'>&nbsp;수정</span></button>";
						htmlRemoveBtn = "<button class='button' style='box-shadow: none; border: none;'"+
									" onclick='confirm("+boardId+")'><span class='icon solid fas fa-trash-alt'"+
									" style='font-weight: normal;'>&nbsp;삭제</span></button>";
					}
					let htmlPostOptionStart = "<hr><div>";
					let htmlBoardLikeBoxStart = "<div id='boardLikeBox"+boardId+"' class='optionBox'>";
					let htmlBoardLike1 = "<a href='javascript:return false;' onclick='likeBoard("+boardId+","+memberId+","+boLiked+","+boLikeCnt+")'>";
					let htmlBoardLike2 = "";
					if(boLiked) {
						htmlBoardLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
					} else {
						htmlBoardLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";
					}
					let htmlBoardLikeBoxEnd = "<span class='postCommentLike'><em>"+boLikeCnt+"</em>개</span></a></div>";
					let htmlBoardCommentBox = "&nbsp;&nbsp; <span class='icon fas fa-comment-dots'></span>&nbsp;"+
										"<span class='commentCnt'><em>"+commentCnt+"</em>개</span>";
					let htmlPostOptionEnd = "<div>";
					let htmlCommentBox = "<ul style='padding: 0;' id='commentBox"+boardId+"'></ul>";
					let htmlGoCommentBox = "<a href='javascript:return false;' onclick='comment("+boardId+","+pageNum+")'"+
									" class='button small fit icon fas fa-pencil-alt'>댓글쓰기/더보기</a><br>";
					let htmlPostEnd = "</div>";
					let htmlBoardListEnd = "</div></li>";
					$('#boardListBox'+pageNum).append(htmlBoardListStart+htmlImage+htmlPostStart+
					htmlBookMarkStart+htmlBookMark1+htmlBookMark2+htmlBookMarkEnd+htmlCategory+htmlBoMemberNick+
					htmlBoardUDate+htmlBoardTitle+htmlBoardContent+htmlBoardTag+htmlModifyBtn+htmlRemoveBtn+
					htmlPostOptionStart+htmlBoardLikeBoxStart+htmlBoardLike1+htmlBoardLike2+htmlBoardLikeBoxEnd+
					htmlBoardCommentBox+htmlPostOptionEnd+htmlCommentBox+htmlGoCommentBox+htmlPostEnd+htmlBoardListEnd);
					if(commentList!=null) {
						for(let j=0; j<commentList.length; j++) {
							let commentId = commentList[j].commentId;
							let commentText = commentList[j].commentText;
							let coMemberId = commentList[j].memberId;
							let coLikeCnt = commentList[j].likeCnt;
							let recommentCnt = commentList[j].recommentCnt;
							let coMemberNick = commentList[j].memberNick;
							let coLiked = commentList[j].liked;
							let commentUDate = commentUDateList[j];
							//Comment HTML
							let htmlCommentListStart = "<li style='padding: 0;' class='comment'>";
							let htmlCoMemberNick = "<a href='javascript:return false;' onclick='showMember("+coMemberId+")'><span class='postCommentId' style='font-weight:bold;'>"+coMemberNick+"</span></a>";
							let htmlCommentText = "&nbsp;&nbsp;<a href='javascript:return false;' onclick='comment("+boardId+","+pageNum+")'><span class='postComment'>"+commentText+"</span></a>";
							let htmlCommentUDate = "<span class='postDate'>&nbsp;&nbsp;"+commentUDate+"&nbsp;&nbsp;</span>";
							let htmlCommentLikeBoxStart = "<div id='commentLikeBox"+commentId+"' class='optionBox'>";
							let htmlCommentLike1 = "<a href='javascript:return false;' onclick='likeComment("+commentId+","+memberId+","+coLiked+","+coLikeCnt+")'>";
							let htmlCommentLike2 = "";
							if(coLiked) {
								htmlCommentLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
							} else {
								htmlCommentLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";
							}
							let htmlCommentLikeBoxEnd = "<span class='postCommentLike'><em>"+coLikeCnt+"</em>개</span></a></div>";
							let htmlCommentRecomment = "&nbsp;&nbsp;<a href='javascript:return false;' onclick='comment("+boardId+","+pageNum+")'>"+
													"<span class='icon fas fa-comment-dots'></span> <span class='reCommentCnt'><em>"+recommentCnt+"</em>개</span></a>";
							let htmlCommentListEnd = "</li>"
							$('#commentBox'+boardId).append(htmlCommentListStart+htmlCoMemberNick+htmlCommentText+htmlCommentUDate+
							htmlCommentLikeBoxStart+htmlCommentLike1+htmlCommentLike2+htmlCommentLikeBoxEnd+htmlCommentRecomment+htmlCommentListEnd);
						}
					}
				}
				if(!toggle) {
					var scrollPosition = $('#boardListBox'+(Number(pageNum)+1)).offset().top;
					$("html").animate({
						scrollTop: scrollPosition
					});			
				}
				if(!isEmpty(showBoardId)) {
					var scrollPosition = $("#boardList" + showBoardId).offset().top;
					$("html").animate({
						scrollTop: scrollPosition
					}, 1000);
				}
				if(boardListDownEndToggle == "t" && boardVal.length < 5) {
					boardListDownEnd = true;
				}
				if(pageNum == 1) {
					boardListUpEnd = true;
				}
				ajaxClick = true;
				downScrolling = true;
				upScrolling = true;
				$("#loader").addClass("skip");
	   		} else {
				location.replace("/error/goError");
	   		}
		}
	});
	return false;
};