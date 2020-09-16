var commentPage = 1;
var commentListEnd = false;
var boardId;
var arrMethod = "";
var urlLink = "";

window.onload = function() {
	boardId = getParameterByName("boardId");
	if (isEmpty(boardId)) {
		boardId = $("#boardId").val();
	}
	arrMethod = getParameterByName("arrMethod");
	arrMethod = (!isEmpty(arrMethod))?arrMethod:"p";
	commentPage = 1;
	getCommentList(boardId,commentPage,arrMethod);
	urlLink = sessionStorage.getItem("urlLink");
	$("#gobackBtn").attr("href", urlLink);
	$("#arraySelect").on("change",function() { //순서정렬 기능
		let method = $("#arraySelect option:selected").val()
		if(method == "p") {
			location.replace("list?arrMethod=p&boardId="+boardId);
		} else {
			location.replace("list?arrMethod=u&boardId="+boardId);
		}
	});
}

window.addEventListener('popstate', function(event) {    
   location.href = urlLink;
   history.pushState(null, document.title, location.href); 
});

$(window).scroll(function() {
	if(!commentListEnd && ($(window).scrollTop()>=$(document).height() - $(window).height())) {
		commentPage++;
		getCommentList(boardId,commentPage);
	}
});

function getCommentList(boardId,commentPage,arrMethod) {
	if(!ajaxClick) {
			return false;
		}
		ajaxClick = false;
		var data = {boardId:boardId,commentPage:commentPage,arrMethod:arrMethod};
		var commentVal = [];
		var commentUDateVal = [];
		$("#loader").removeClass("skip");
		$.ajax({
			url : "list",
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
	       		if (data.code == "OK") {
					commentVal = data.commentList ;
					commentUDateVal = data.commentUDateList;
					memberId = data.memberId;
					for(let i=0; i<commentVal.length; i++) {
						let commentId = commentVal[i].commentId;
						let boardId = commentVal[i].boardId;
						let commentText = commentVal[i].commentText;
						let coMemberId = commentVal[i].memberId;
						let memberNick = commentVal[i].memberNick;
						let recommentCnt = commentVal[i].recommentCnt;
						let likeCnt = commentVal[i].likeCnt;
						let liked = commentVal[i].liked;
						let commentUDate = commentUDateVal[i];
						
						let commentHead = "<li id='commentBox"+commentId+"' class='comment' style='padding: 0; margin-top: 10px; word-break: break-word; font-size:0.9em;'>" 
						let commentMemberNick = "<a href='javascript:return false;' onclick='showMember("+coMemberId+")'>"+
						"<span class='postCommentId' style='font-weight:bold;'>"+memberNick+"</span></a>&nbsp;&nbsp;";
						let commentTextHtml = "<a href='javascript:return false;' onclick='complainComment("+commentId+","+memberId+")'>"+
						"<span class='postComment'>"+commentText+"</span></a>&nbsp;&nbsp;";
						let commentUDateHtml = "<span class='postDate' style='font-size: 0.8em; color: #666'>&nbsp;"+commentUDate+"&nbsp;&nbsp;</span>";
						let commentLikeBoxStart = "<div id='commentLikeBox"+commentId+"' class='optionBox'>";
						let commentLike1 = "<a href='javascript:return false;' onclick='likeComment("+commentId+","+memberId+","+liked+","+likeCnt+")'>";
						let commentLike2;
						if(liked) {
							commentLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
						} else {
							commentLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";						
						}
						let commentLikeBoxEnd = "<em>"+likeCnt+"</em>개</a></div><br>";
						let recommentInsert = "<a href='javascript:return false;' onclick='insertRecommentForm("+commentId+","+memberId+")'>"+
						"<span class='icon fas fa-comment-medical'></span> 답글달기</a>";
						let commentRecommentBox = "&nbsp;&nbsp;<div id='commentRecommentBox"+commentId+"' class='optionBox'>";
						let recommentCntHtml;
						if(recommentCnt>0) {
							recommentCntHtml = "<a href='javascript:return false;' onclick='getRecomment("+commentId+",1)'>"+
						"<span class='icon fas fa-comments'>&nbsp;</span><em id='recommentCnt"+commentId+"'>"+recommentCnt+"</em>개 답글보기</a></div>";
						} else {
							recommentCntHtml = "</div>";
						}
						let commentDelete = "";
						if(memberId == coMemberId || (memberId <= 5 && memberId != 0)) {
							commentDelete = "<button class='button' style='box-shadow: none; border: none;'"+
										" onclick='removeComment("+commentId+", "+boardId+")'>"+
										"<span class='icon fas fa-trash-alt' style='font-weight: normal; font-size: 0.9em; color: #666'>&nbsp;삭제</span></button>";
						}
						let recommentRegBoxStart = "<div style='margin-top: 10px' class='recommentReg"+commentId+" skip'>"+
						"<input style='margin-bottom: 5px;' type='text' name='recommentText"+commentId+"' placeholder='답글을 입력하세요. 최대 250자. 죄송합니다. 현재 이모티콘은 사용할 수 없습니다.'/>"+
						"<ul class='actions fit small'>";
						let recommentRegBtn = "<li><a href='javascript:return false;' class='button fit small'"+
											" onclick='insertRecommentCheck("+commentId+","+memberId+")'>"+
											"<span class='icon fas fa-plus'></span>&nbsp;&nbsp;추가</a></li>";
						let recommentRegCloseBtn = "<li><a href='javascript:return false;' class='button primary fit small'"+
											" onclick='insertRecommentClose("+commentId+")'>"+
											"<span class='icon fas fa-comment-slash'></span>&nbsp;&nbsp;취소</a></li>";
						let recommentRegBoxEnd = "</ul></div>"
						let recommentListBox = "<ul class='recommentList"+commentId+" skip'></ul>"
						$('#commentList').append(commentHead+commentMemberNick+commentTextHtml+commentUDateHtml+
						commentLikeBoxStart+commentLike1+commentLike2+commentLikeBoxEnd+
						recommentInsert+commentRecommentBox+recommentCntHtml+commentDelete+
						recommentRegBoxStart+recommentRegBtn+recommentRegCloseBtn+recommentRegBoxEnd+
						recommentListBox+"</li>");
					}
					if(commentVal.length<10) {
						commentListEnd = true;
					}
					$("#loader").addClass("skip");
	       		} else {
					location.replace("/error/goError");
	       		}
				ajaxClick = true;
	   		}
		});
		return false;
}

function insertCommentCheck() {
	if(clickCheck(500) == "f") {
			return false;
	}
	if (document.registCommentForm.commentText.value == "") {
        myAlert("댓글을 입력해주세요.");
        return false;
    }
	if (document.registCommentForm.commentText.value.length > 500) {
        myAlert("최대 500 글자 입력 가능합니다.");
        return false;
    }
	document.registCommentForm.submit();
}

function removeComment(commentId, boardId) {
	onConfirm("정말 삭제하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {commentId:commentId,boardId:boardId};
		$.ajax({
			url : "removeComment",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					document.getElementById('commentBox'+commentId).parentNode.removeChild(document.getElementById('commentBox'+commentId));
					document.getElementById('commentCnt').innerText = ""+Number(document.getElementById('commentCnt').innerText)-1;
					$("#loader").addClass("skip");
				} else {
					location.replace("/error/goError");
				}
			}
		});
		return false;
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function insertRecommentForm(commentId,memberId) {
	if(memberId == "0") {
		myAlert("답글 기능은 로그인 시 가능합니다.");
		return false;
	}
	$('.recommentReg'+commentId).removeClass('skip');	
}

function insertRecommentCheck(commentId,memberId) {
	if(memberId == "0") {
		myAlert("답글 기능은 로그인 시 가능합니다.");
		return false;
	}
	commentText = $('input[name=recommentText'+commentId+']').val();
	if (commentText == "") {
        myAlert("답글을 입력해주세요.");
        return false;
    }
	if (commentText.length > 250) {
        myAlert("최대 250 글자 입력 가능합니다.");
        return false;
    }
	if(clickCheck(500) == "f") {
			return false;
	}
	var data = {commentId:commentId,memberId:memberId,commentText:commentText};
	$("#loader").removeClass("skip");
	$.ajax({
		url : "registRecomment",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
       		if (data == 1) {
				let recommentCnt = (document.getElementById('recommentCnt'+commentId) != null)?Number(document.getElementById('recommentCnt'+commentId).innerText):0;
				$('input[name=recommentText'+commentId+']').val('');
				$('.recommentReg'+commentId).addClass('skip');
				let commentRecommentBox = "&nbsp;&nbsp;<div id='commentRecommentBox"+commentId+"' class='optionBox'>";
				let recommentCntHtml = "<a href='javascript:return false;' onclick='getRecomment("+commentId+",1)'>"+
				"<span class='icon fas fa-comments'>&nbsp;</span><em id='recommentCnt"+commentId+"'>"+(recommentCnt+1)+"</em>개 답글보기</a></div>";
				$('#commentRecommentBox'+commentId).empty();
				$('#commentRecommentBox'+commentId).append(commentRecommentBox+recommentCntHtml);
				getRecomment(commentId,memberId,1);
				$("#loader").addClass("skip");
       		} else {
				location.replace("/error/goError");
       		}
   		}
	});
	return false;
}

function insertRecommentClose(commentId) {
	$('input[name=recommentText'+commentId+']').val('');
	$('.recommentReg'+commentId).addClass('skip');
}

function removeRecomment(recommentId, commentId) {
	onConfirm("정말 삭제하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {recommentId:recommentId,commentId:commentId};
		$.ajax({
			url : "removeRecomment",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					let recommentCnt = (document.getElementById('recommentCnt'+commentId) != null)?Number(document.getElementById('recommentCnt'+commentId).innerText):0;
					document.getElementById('recommentBox'+recommentId).parentNode.removeChild(document.getElementById('recommentBox'+recommentId));
					let commentRecommentBox = "&nbsp;&nbsp;<div id='commentRecommentBox"+commentId+"' class='optionBox'>";
					let recommentCntHtml;
					if(recommentCnt-1 < 1) {
						recommentCntHtml = "</div>"
						closeRecomment(commentId);
					} else {
						recommentCntHtml = "<a href='javascript:return false;' onclick='getRecomment("+commentId+",1)'>"+
						"<span class='icon fas fa-comments'>&nbsp;</span><em id='recommentCnt"+commentId+"'>"+(recommentCnt-1)+"</em>개 답글보기</a></div>";						
					}
					$('#commentRecommentBox'+commentId).empty();
					$('#commentRecommentBox'+commentId).append(commentRecommentBox+recommentCntHtml);
					$("#loader").addClass("skip");
				} else {
					location.replace("/error/goError");
				}
			}
		});
		return false;
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function getRecomment(commentId,recommentPage) {
	$('.recommentList'+commentId).empty();
	$('.recommentList'+commentId).removeClass('skip');
	
	getRecommentListMore(commentId,recommentPage);
	return false;
}

function closeRecomment(commentId) {
	$('.recommentList'+commentId).addClass('skip');
	$('.recommentListClose'+commentId).addClass('skip');
}

function getRecommentListMore(commentId,recommentPage) {
	$('.recommentListBtn'+commentId).detach();
	
	if(!ajaxClick) {
		return false;
	}
	ajaxClick = false;
	var data = {commentId:commentId,recommentPage:recommentPage};
	var recommentVal = [];
	var recoUDateVal = [];
	$("#loader").removeClass("skip");
	$.ajax({
		url : "listRecomment",
		type : "get",
		data : data,
		dataType : "json",
		success : function(data) {
       		if (data.code == "OK") {
				recommentVal = data.recommentList ;
				recoUDateVal = data.recommentUDateList;
				memberId = data.memberId;
				for(let i=0; i<recommentVal.length; i++) {
					let recommentId = recommentVal[i].recommentId;
					let recoCommentId = recommentVal[i].commentId;
					let recommentText = recommentVal[i].recommentText;
					let recoMemberId = recommentVal[i].memberId;
					let memberNick = recommentVal[i].memberNick;
					let likeCnt = recommentVal[i].likeCnt;
					let liked = recommentVal[i].liked;
					let recoUDate = recoUDateVal[i];
					
					let recommentHead = "<li style='padding: 0; color:#666; font-size:0.9em;' class='comment' id='recommentBox"+recommentId+"'>" 
					let recommentMemberNick = "<a href='javascript:return false;' onclick='showMember("+recoMemberId+")'>"+
					"<span class='reCommentId'>"+memberNick+"</span></a>&nbsp;&nbsp;";
					let recommentRecoText = "<a href='javascript:return false;' onclick='complainRecomment("+recommentId+","+memberId+")'>"+
					"<span class='reComment'>"+recommentText+"</span></a>&nbsp;&nbsp;";
					let recommentUDate = "<span class=''postDate' style='font-size: 0.8em; color: #666'>&nbsp;"+recoUDate+"&nbsp;&nbsp;</span>";
					let recommentLikeBox = "<div id='recommentLikeBox"+recommentId+"' class='optionBox'>";
					let recommentLike1 = "<a href='javascript:return false;' onclick='likeRecomment("+recommentId+","+memberId+","+liked+","+likeCnt+")'>";
					let recommentLike2;
					if(liked) {
						recommentLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
					} else {
						recommentLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";						
					}
					let recommentLike3 = "<span class='postCommentLike'><em>"+likeCnt+"</em>개</span></a></div>";
					let recoDelete = "";
					if(memberId == recoMemberId || (memberId <= 5 && memberId != 0)) {
						recoDelete = "&nbsp;&nbsp<a href='javascript:return false;' onclick='removeRecomment("+recommentId+","+commentId+")'>"+
					"<span class='icon fas fa-trash-alt' style='font-weight: normal; font-size: 0.7em; color: #666'> 삭제</span></a>";
					}
					$('.recommentList'+commentId).append(recommentHead+recommentMemberNick+recommentRecoText+recommentUDate+recommentLikeBox+recommentLike1+recommentLike2+recommentLike3+recoDelete+"</li>");
				}
				let recommentListMoreBtn = "<a style='font-size:0.9em;' href='javascript:return false;' onclick='getRecommentListMore("+commentId+","+(Number(recommentPage)+1)+")'>"+
				"<span class='icon fas fa-plus-square'></span> 더보기</a>&nbsp;&nbsp;";
				let recommentListCloseBtn = "<a	style='font-size:0.9em;' href='javascript:return false;' onclick='closeRecomment("+commentId+")'>"+
				"<span class='icon far fa-window-close'></span> 닫기</a>";
				if(recommentVal.length < 10) {
					$('.recommentList'+commentId).append("<li class='recommentListBtn"+commentId+" comment'>"+recommentListCloseBtn+"</li>");
				} else {
					$('.recommentList'+commentId).append("<li class='recommentListBtn"+commentId+" comment'>"+recommentListMoreBtn+recommentListCloseBtn+"</li>");					
				}
				$("#loader").addClass("skip");
       		} else {
				location.replace("/error/goError");
       		}
			ajaxClick = true;
   		}
	});
	return false;
}

//태그클릭
function searchKeyword(keyword) {
	location.href="/board/search/list?keyword="+encodeURI(keyword);
}