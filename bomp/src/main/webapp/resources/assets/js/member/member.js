var pageNum = 1;
var arrMethod = "";
var tagBoxToggle = true;
var nickBoxToggle = true;
var reduplication = true;
var oldVal ="";

window.onload = function() {
	arrMethod = getParameterByName("arrMethod");
	arrMethod = (!isEmpty(arrMethod))?arrMethod:"n";
	pageNum = 1;
	getAlertList(pageNum,arrMethod);
	
	$("#arraySelect").on("change",function() { //순서정렬 기능
		let method = $("#arraySelect option:selected").val()
		if(method == "n") {
			location.replace("info?arrMethod=n");
		} else if(method == "c"){
			location.replace("info?arrMethod=c");
		} else {
			location.replace("info?arrMethod=c");
		}
	});
	
	$("#memberNickInput").on("propertychange change keyup paste input", function() {
   		var currentVal = $(this).val();
	    if(currentVal == oldVal) {
	        return;
	    }
	    oldVal = currentVal;
	    reduplication = true;
		myAlert("변경 시 다시 닉네임 체크 해주세요!");
	});
}

function getAlertList(pageNum,arrMethod) {
	$('.alertListBtn').detach();
	if(!ajaxClick) {
			return false;
		}
		ajaxClick = false;
		var data = {pageNum:pageNum,arrMethod:arrMethod};
		var alertVal = [];
		var alertUDateVal = [];
		$("#loader").removeClass("skip");
		$.ajax({
			url : "alert",
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
	       		if (data.code == "OK") {
					alertVal = data.alertList ;
					alertUDateVal = data.alertUDateList;
					for(let i=0; i<alertVal.length; i++) {
						let memberId = alertVal[i].memberId;
						let checked = alertVal[i].checked;
						let kind = alertVal[i].kind;
						let alertUserComment = alertVal[i].alertUserComment;
						let bocoreId = alertVal[i].bocoreId;
						let memberNick = alertVal[i].memberNick;
						let alertUDate = alertUDateVal[i];
						let htmlAlertStart = "<li id='alertBox' class='comment' style='padding: 0; margin-top: 10px; word-break: break-word; font-size:0.9em;'>" 
						let htmlAlertMemberNick = "<a href='javascript:return false;' onclick='showMember("+memberId+")'>"+
						"<span>"+memberNick+"</span></a>님 께서&nbsp;";
						let htmlAlertComment1 = "";
						if(kind == 3) {
							htmlAlertComment1 = "<a href='javascript:return false;' onclick='goRecommentFromAlert("+bocoreId+")'>"							
						} else if(kind == 2) {
							htmlAlertComment1 = "<a href='javascript:return false;' onclick='goCommentFromAlert("+bocoreId+")'>"
						} else if(kind == 1) {
							htmlAlertComment1 = "<a href='javascript:return false;' onclick='goBoardFromAlert("+bocoreId+")'>"
						} else if(kind == 4){
							htmlAlertComment1 = "<a href='javascript:return false;' onclick='goMessageFromAlert("+bocoreId+")'>"
						} else {
							htmlAlertComment1 = "<a href='javascript:return false;'>"							
						}
						let htmlAlertComment2 = "<span>"+alertUserComment+"</span></a>&nbsp;&nbsp;";
						let htmlAlertUDateHtml = "<span class='postDate' style='font-size: 0.8em; color: #666'>&nbsp;"+alertUDate+"&nbsp;&nbsp;</span>";
						$('#alertList').append(htmlAlertStart+htmlAlertMemberNick+
						htmlAlertComment1+htmlAlertComment2+htmlAlertUDateHtml+"</li>");
					}
					let alertListMoreBtn = `<a style="font-size:0.9em;" href="javascript:return false;" onclick="getAlertList(`+(Number(pageNum)+1)+`, '`+arrMethod+`')">`+
					`<span class="icon fas fa-plus-square"></span> 더보기</a>`;				
					if(alertVal.length < 10) {
						$('#alertList').append("<li class='alertListBtn comment'></li>");
					} else {
						$('#alertList').append("<li class='alertListBtn comment'>"+alertListMoreBtn+"</li>");					
					}
					$("#loader").addClass("skip");
	       		} else if(data.code == "NO") {
					$("#loader").addClass("skip");
	       		} else {
					location.replace("/error/goError");		
				}
				ajaxClick = true;
	   		}
		});
		return false;
}

function goRecommentFromAlert(recommentId) {
	location.href = "/comment/recommentFromAlertList?recommentId="+recommentId;
}

function goCommentFromAlert(commentId) {
	location.href = "/comment/commentFromAlertList?commentId="+commentId;
}

function goBoardFromAlert(boardId) {
	location.href = "/comment/boardFromAlertList?boardId="+boardId;
}

function memberNickCheck() {
	let memberNick = document.getElementById('memberNickInput').value;
	if (memberNick == "") {
        myAlert("닉네임을 입력해주세요.");
        return false;
    }
	let regexp = /^[0-9]*$/;
	if(regexp.test(memberNick)) {
		myAlert("닉네임은 숫자만 들어갈 수 없습니다.");
        return false;
	}
	regexp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
	if(regexp.test(memberNick)) {
		myAlert("닉네임에는 특수문자를 사용할 수 없습니다.");
        return false;
	}
	if (memberNick.length > 10) {
        myAlert("닉네임은 최대 10글자 입니다.");
        return false;
    }
	if(clickCheck(500) == "f") {
			return false;
	}
	$("#loader").removeClass("skip");
	var data = {memberNick:memberNick};
	$.ajax({
		url : "/member/memberNickCheck",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				myAlert("변경 가능합니다!")
				reduplication = false;
				$("#loader").addClass("skip");
			} else {
				myAlert("해당 아이디는 중복됩니다.");
				reduplication = true;
				$("#loader").addClass("skip");
			}
		}
	});
}

function memberNickModify() {
	if(clickCheck(500) == "f") {
			return false;
	}
	if(!reduplication) {
	    document.memberNickForm.submit();	
	} else {
		myAlert("닉네임 체크 해주세요.");
		return false;
	}
}

function memberNickBox() {
	if(nickBoxToggle) {
		$(".nickBox").removeClass('skip');
		nickBoxToggle = false;
	} else {
		$(".nickBox").addClass('skip');
		nickBoxToggle = true;		
	}
}

function memberTagForm() {
	if(tagBoxToggle) {
		$(".tagRegBox").removeClass('skip');
		tagBoxToggle = false;
	} else {
		$(".tagRegBox").addClass('skip');
		$("#tagBox").load(location.href + " #tagBox");
		tagBoxToggle = true;
	}
}

function memberTagReg(memberId) {
	if(clickCheck(500) == "f") {
			return false;
	}
	let memberTag = document.getElementById('memberTag').value;
	let tagArr = memberTag.split("#");
	for (let i=1; i<tagArr.length; i++) {
		if(tagArr[i].trim().length > 10) {
			myAlert("각 태그는 10글자 이상 쓸수 없습니다.");
	        return false;			
		}
	}
	$("#loader").removeClass("skip");
	var data = {memberId:memberId,memberTag:memberTag};
	$.ajax({
		url : "/member/tagReg",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				$("#tagBox").load(location.href + " #tagBox");
				$('.tagRegBox').addClass("skip");
				$("#loader").addClass("skip");
				myAlert("수정 완료 되었습니다!");
			} else {
				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
		}
	});
}

function removeMember(memberId) {
	onConfirm("정말 탈되하겠습니까? 우리는 회원개인 정보를 남기지 않으며, 탈퇴 시 모든 활동은 삭제 됩니다.");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		location.replace("/member/remove?memberId="+memberId);
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function memberBoardList(bLMemberId) {
	location.href = "/board/member/list?arrMethod=p&bLMemberId="+bLMemberId;
}

function markBoardList(memberId) {
	location.href = "/board/mark/list?arrMethod=p&bLMemberId="+memberId;
}

function messageBox(fromMemberId) {
	if(fromMemberId == 0) {
		myAlert("로그인을 해야 이용할 수 있는 기능입니다.");
		return false;
	}
	$("#messageForm").removeClass("skip");
	$("#my_banner").removeClass("skip");
}

function messageSend() {
	let fromMemberId = $("#fromMemberId").val();
	let toMemberId = $("#toMemberId").val();
	let messageContent = $("#messageContent").val()
	if(messageContent.length > 500) {
		document.getElementById('alert').className = "alert";
		document.getElementById('alert_comment').innerText = " 죄송합니다. 메세지는 500자 이상 입력할 수 없습니다.";
		document.getElementById('okBtn').className = "skip"
		document.getElementById('cancelBtn').className = "skip"
		var time;
	    let cnt=2;
	    clearInterval(time);
	    time = setInterval(function adTimer(){
	    	cnt = cnt - 1;
	   		if (cnt == 0) {
			clearInterval(time);
			document.getElementById('alert').className = "skip";
			document.getElementById('alert_comment').innerText = ""
			document.getElementById('okBtn').className = "skip"
			document.getElementById('cancelBtn').className = "skip"
			}
	   	}, 1000);
		return false;
	}
	if(clickCheck(500) == "f") {
		return false;
	}
	console.log(messageContent);
	$("#loader").removeClass("skip");
	var data = {fromMemberId:fromMemberId,toMemberId:toMemberId,messageContent:messageContent};
	$.ajax({
		url : "/member/messageSend",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data == 1) {
				$('#messageForm').addClass("skip");
				$("#loader").addClass("skip");
				document.getElementById('alert').className = "alert";
				document.getElementById('alert_comment').innerText = " 메세지를 보냈습니다!";
				document.getElementById('okBtn').className = "skip"
				document.getElementById('cancelBtn').className = "skip"
				var time;
			    let cnt=2;
			    clearInterval(time);
			    time = setInterval(function adTimer(){
			    	cnt = cnt - 1;
			   		if (cnt == 0) {
					clearInterval(time);
					document.getElementById('my_banner').className = "my_banner skip";
					document.getElementById('alert').className = "skip";
					document.getElementById('alert_comment').innerText = ""
					document.getElementById('okBtn').className = "skip"
					document.getElementById('cancelBtn').className = "skip"
					}
			   	}, 1000);
			} else {
				$('#messageForm').addClass("skip");
				$("#loader").addClass("skip");
				location.replace("/error/goError");
			}
		}
	});
}

function messageCancel() {
	$("#messageForm").addClass("skip");
	$("#my_banner").addClass("skip");
}

function goMessageFromAlert(messageId) {
	location.href = "/member/getMessage?messageId="+messageId;
}