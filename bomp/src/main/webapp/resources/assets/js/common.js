//크롬 확인
window.addEventListener('load', function() {
	//https 로 이동
	if (location.protocol === 'http:' && location.host.indexOf('cafe24.com') === -1) {
	    var sUrl = 'https://' + location.host + location.pathname + location.search;
	
	    window.location.replace(sUrl);
	}
	
	//이미지 박스 스킵
	let imgBox = document.getElementsByTagName('img');
	for(let i =0; i<imgBox.length; i++) {
		if(isEmpty(imgBox[i].getAttribute('src'))) {
			imgBox[i].setAttribute('class','skip');
		}
	}
	
	if(isEmpty(sessionStorage.getItem("cromeCheck"))) {
		sessionStorage.setItem("cromeCheck", 1); 
		var agt = navigator.userAgent.toLowerCase();
		if (!(agt.indexOf("chrome") != -1)) {
			document.getElementById('my_banner').className = "my_banner";
			document.getElementById('alert').className = "alert";
			document.getElementById('alert_comment').innerText = " "+"본 메세지는 5초 후 자동 종료됩니다. 죄송합니다. 저희 홈페이지는 크롬 브라우저를 기본으로 만들어졌습니다. 다른 브라우저로 접속 시 비정상적 동작이 나타날 수 있습니다.";
			document.getElementById('okBtn').className = "skip"
			document.getElementById('cancelBtn').className = "skip"
			var time;
		    let cnt=5;
		    clearInterval(time);
		    time = setInterval(function adTimer(){
		    	cnt = cnt - 1;
		   		if (cnt == 0) {
				clearInterval(time);
				document.getElementById('my_banner').className = "skip";
				document.getElementById('alert').className = "skip";
				document.getElementById('alert_comment').innerText = ""
				document.getElementById('okBtn').className = "skip"
				document.getElementById('cancelBtn').className = "skip"
				}
		   	}, 1000);
		}
	}
});


// 중복클릭 방지
var click = true;
var ajaxClick = true;

function clickCheck(time) {
	if(click) {
		click = !click;
		setTimeout(function () {
                click = true;
        }, time)
	} else {
		return "f";
	}
}
// alert기능
function onConfirm(alertComment) {
	document.getElementById('my_banner').className = "my_banner";
	document.getElementById('alert').className = "alert";
	document.getElementById('alert_comment').innerText = " "+alertComment;
	document.getElementById('okBtn').className = "small fit icon fas fa-check"
	document.getElementById('cancelBtn').className = "small fit icon fas fa-times"
}

function offConfirm() {
	document.getElementById('my_banner').className = "skip";
	document.getElementById('alert').className = "skip";
	document.getElementById('alert_comment').innerText = ""
	document.getElementById('okBtn').className = "skip"
	document.getElementById('cancelBtn').className = "skip"
}


function myAlert(alertComment) {
	document.getElementById('my_banner').className = "my_banner";
	document.getElementById('alert').className = "alert";
	document.getElementById('alert_comment').innerText = " "+alertComment;
	document.getElementById('okBtn').className = "skip"
	document.getElementById('cancelBtn').className = "skip"
	var time;
    let cnt=2;
    clearInterval(time);
    time = setInterval(function adTimer(){
    	cnt = cnt - 1;
   		if (cnt == 0) {
		clearInterval(time);
		document.getElementById('my_banner').className = "skip";
		document.getElementById('alert').className = "skip";
		document.getElementById('alert_comment').innerText = ""
		document.getElementById('okBtn').className = "skip"
		document.getElementById('cancelBtn').className = "skip"
		}
   	}, 1000);
}

//스크립트 - url get파라미터받기
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

//문자 null체크
function isEmpty(str){
	if(typeof str == "undefined" || str == null || str == "") {
		return true;	
	} else {
		return false;	
	}
}

//좋아요
function likeBoard(boardId, memberId, liked, likeCnt) {
	if(memberId == "0") {
		myAlert("좋아요 기능은 로그인 시 가능합니다.");
		return false;
	}
	if(!ajaxClick) {
		return false;
	}
	ajaxClick = false;
//	$("#loader").removeClass("skip");
	var likeCnt = Number(likeCnt)
	var data = {boardId:boardId,memberId:memberId,liked:liked};
	$.ajax({
		url : "/like/board",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				let boardLike1 = "<a href='javascript:return false;' onclick='likeBoard("+boardId+","+memberId+",true,"+(likeCnt+1)+")'>";
				let boardLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
				let boardLike3 = "<span class='postCommentLike'><em>"+(likeCnt+1)+"</em>개</span></a>";
				$('#boardLikeBox'+boardId).empty();
				$('#boardLikeBox'+boardId).append(boardLike1+boardLike2+boardLike3);
//				$("#loader").addClass("skip");
			} else if (data == 2){
				let boardLike1 = "<a href='javascript:return false;' onclick='likeBoard("+boardId+","+memberId+",false,"+(likeCnt-1)+")'>";
				let boardLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";
				let boardLike3 = "<span class='postCommentLike'><em>"+(likeCnt-1)+"</em>개</span></a>";
				$('#boardLikeBox'+boardId).empty();
				$('#boardLikeBox'+boardId).append(boardLike1+boardLike2+boardLike3);
//				$("#loader").addClass("skip");
			} else {
//				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
			ajaxClick = true;
		}
	});
}

function likeComment(commentId, memberId, liked, likeCnt) {
	if(memberId == "0") {
		myAlert("좋아요 기능은 로그인 시 가능합니다.");
		return false;
	}
	if(!ajaxClick) {
		return false;
	}
	ajaxClick = false;
//	$("#loader").removeClass("skip");
	var likeCnt = Number(likeCnt)
	var data = {commentId:commentId,memberId:memberId,liked:liked};
	$.ajax({
		url : "/like/comment",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				let commentLike1 = "<a href='javascript:return false;' onclick='likeComment("+commentId+","+memberId+",true,"+(likeCnt+1)+")'>";
				let commentLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
				let commentLike3 = "<span class='postCommentLike'><em>"+(likeCnt+1)+"</em>개</span></a>";
				$('#commentLikeBox'+commentId).empty();
				$('#commentLikeBox'+commentId).append(commentLike1+commentLike2+commentLike3);
//				$("#loader").addClass("skip");
			} else if (data == 2){
				let commentLike1 = "<a href='javascript:return false;' onclick='likeComment("+commentId+","+memberId+",false,"+(likeCnt-1)+")'>";
				let commentLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";
				let commentLike3 = "<span class='postCommentLike'><em>"+(likeCnt-1)+"</em>개</span></a>";
				$('#commentLikeBox'+commentId).empty();
				$('#commentLikeBox'+commentId).append(commentLike1+commentLike2+commentLike3);
//				$("#loader").addClass("skip");
			} else {
//				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
			ajaxClick = true;
		}
	});
}

function likeRecomment(recommentId, memberId, liked, likeCnt) {
	if(memberId == "0") {
		myAlert("좋아요 기능은 로그인 시 가능합니다.");
		return false;
	}
	if(!ajaxClick) {
		return false;
	}
	ajaxClick = false;
//	$("#loader").removeClass("skip");
	var likeCnt = Number(likeCnt)
	var data = {recommentId:recommentId,memberId:memberId,liked:liked};
	$.ajax({
		url : "/like/recomment",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				let recommentLike1 = "<a href='javascript:return false;' onclick='likeRecomment("+recommentId+","+memberId+",true,"+(likeCnt+1)+")'>";
				let recommentLike2 = "<span class='icon fas fa-heartbeat liked'></span>&nbsp;";
				let recommentLike3 = "<span class='postCommentLike'><em>"+(likeCnt+1)+"</em>개</span></a>";
				$('#recommentLikeBox'+recommentId).empty();
				$('#recommentLikeBox'+recommentId).append(recommentLike1+recommentLike2+recommentLike3);				
//				$("#loader").addClass("skip");
			} else if (data == 2){
				let recommentLike1 = "<a href='javascript:return false;' onclick='likeRecomment("+recommentId+","+memberId+",false,"+(likeCnt-1)+")'>";
				let recommentLike2 = "<span class='icon fas fa-heartbeat'></span>&nbsp;";						
				let recommentLike3 = "<span class='postCommentLike'><em>"+(likeCnt-1)+"</em>개</span></a>";
				$('#recommentLikeBox'+recommentId).empty();
				$('#recommentLikeBox'+recommentId).append(recommentLike1+recommentLike2+recommentLike3);				
//				$("#loader").addClass("skip");
			} else {
//				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
			ajaxClick = true;
		}
	});
}

//신고하기 기능
function complainBoard(boardId, memberId) {
	if(memberId == 0) {
		myAlert("신고하기 기능은 로그인 시 가능합니다.");
		return false;
	}
	onConfirm("정말 신고하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {boardId:boardId,memberId:memberId};
		$.ajax({
			url : "/complain/board",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					$("#loader").addClass("skip");
					myAlert("신고가 정상적으로 접수 되었습니다!");
				} else {
					$("#loader").addClass("skip");
					location.replace("/error/goError");
				}
			}
		});
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function complainComment(commentId, memberId) {
	if(memberId == 0) {
		myAlert("신고하기 기능은 로그인 시 가능합니다.");
		return false;
	}
	onConfirm("정말 신고하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {commentId:commentId,memberId:memberId};
		$.ajax({
			url : "/complain/comment",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					$("#loader").addClass("skip");
					myAlert("신고가 정상적으로 접수 되었습니다!");
				} else {
					$("#loader").addClass("skip");
					location.replace("/error/goError");
				}
			}
		});
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function complainRecomment(recommentId, memberId) {
	if(memberId == 0) {
		myAlert("신고하기 기능은 로그인 시 가능합니다.");
		return false;
	}
	onConfirm("정말 신고하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {recommentId:recommentId,memberId:memberId};
		$.ajax({
			url : "/complain/recomment",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					$("#loader").addClass("skip");
					myAlert("신고가 정상적으로 접수 되었습니다!");
				} else {
					$("#loader").addClass("skip");
					location.replace("/error/goError");
				}
			}
		});
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function complainMessage(messageId, memberId) {
	if(memberId == 0) {
		myAlert("신고하기 기능은 로그인 시 가능합니다.");
		return false;
	}
	onConfirm("정말 신고하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		$("#loader").removeClass("skip");
		var data = {messageId:messageId,memberId:memberId};
		$.ajax({
			url : "/complain/message",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if(data == 1) {
					$("#loader").addClass("skip");
					myAlert("신고가 정상적으로 접수 되었습니다!");
				} else {
					$("#loader").addClass("skip");
					location.replace("/error/goError");
				}
			}
		});
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

//멤버 보기 기능

function showMember(memberId) {
	location.href="/member/info?memberId="+encodeURI(memberId)
}

//게시판 북마크
function markBoard(boardId, memberId, marked) {
	if(memberId == "0") {
		myAlert("북마크 기능은 로그인 시 가능합니다.");
		return false;
	}
	if(!ajaxClick) {
		return false;
	}
	ajaxClick = false;
//	$("#loader").removeClass("skip");
	var data = {boardId:boardId,memberId:memberId,marked:marked};
	$.ajax({
		url : "/board/mark/reg",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				let boardMark = "<a href='javascript:return false;' onclick='markBoard("+boardId+","+memberId+",true,"+")'>"+
				"<span class='icon fas fa-bookmark' style='color:#ffd700; font-size:1.2em;'></span></a>";
				$('#boardMarkBox'+boardId).empty();
				$('#boardMarkBox'+boardId).append(boardMark);
//				$("#loader").addClass("skip");
			} else if (data == 2){
				let boardMark = "<a href='javascript:return false;' onclick='markBoard("+boardId+","+memberId+",false,"+")'>"+
				"<span class='icon far fa-bookmark'></span></a>";
				$('#boardMarkBox'+boardId).empty();
				$('#boardMarkBox'+boardId).append(boardMark);
//				$("#loader").addClass("skip");
			} else {
//				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
			ajaxClick = true;
		}
	});
}
