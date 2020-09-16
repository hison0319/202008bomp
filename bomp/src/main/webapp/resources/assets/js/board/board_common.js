var startPageNum = 1;
var downPageNum = 1;
var upPageNum = 1;
var showBoardId = "";
var boardListDownEnd = false;
var boardListUpEnd = false;
var downScrolling = false;
var upScrolling  =false;
var ajaxClick = true;
var lastScrollTop = 0;
var arrMethod = "p";
var keyword = "";
var bLMemberId = "";

function update(boardId, likeCnt, commentCnt) {
	if(likeCnt >= 5 || commentCnt >=5) {
		myAlert("좋아요와 댓글이 5개 이상이면 수정이 불가합니다.");
	} else {
		location.replace("/board/modify?boardId="+boardId);		
	}
}

function confirm(boardId) {
	onConfirm("정말 삭제하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		clickCheck(500);
		offConfirm();
		remove(boardId)
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function remove(boardId) {
	location.replace("/board/remove?boardId="+boardId);
}

function comment(boardId, pageNum) {
	let keywordParam = keyword
	let arrMethodParam = arrMethod
	let bLMemberIdParam = bLMemberId;
	let link = document.location.href;
	link = link.split("?")[0];
	if(pageNum == null){
		pageNum = 1;
	}
	let urlLink = link+"?boardId="+boardId+"&pageNum="+pageNum+"&arrMethod="+arrMethodParam+"&keyword="+keywordParam+"&bLMemberId="+bLMemberIdParam;
	sessionStorage.removeItem("urlLink")
	sessionStorage.setItem("urlLink", urlLink ); 
	location.href="/comment/list?boardId="+encodeURI(boardId);
}

//검색, 태그클릭
function searchKeyword(keyword) {
	if(!isEmpty(keyword)) {
		document.searchfrm.keyword.value = keyword;
	}
	let inputKeyword = document.searchfrm.keyword.value;
	if (inputKeyword == "") {
        myAlert("검색어를 입력해주세요.");
        return false;
    }
	if (inputKeyword > 10) {
        myAlert("검색어는 최대 10글자 입력 가능합니다.");
        return false;
    }
	document.searchfrm.submit();
}

function goPosting() {
	location.replace("/board/posting");
}