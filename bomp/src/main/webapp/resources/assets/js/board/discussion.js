var urlLink = "";

window.onload = function() {
	let pageNumStr = getParameterByName("pageNum");
	urlLink = document.location.href;
	urlLink = urlLink.split("show")[0];
	urlLink = urlLink + "list?pageNumStr=" + 3;
}

window.addEventListener('popstate', function(event) {    
   location.href = urlLink;
   history.pushState(null, document.title, location.href); 
});

function update(boardId, likeCnt, commentCnt) {
	if(likeCnt >= 5 || commentCnt >=5) {
		myAlert("좋아요와 댓글이 5개 이상이면 수정이 불가합니다.");
	} else {
		location.replace("/board/modify?boardId="+boardId);		
	}
}

function comment(boardId) {
	let link = document.location.href;
	link = link.split("?")[0];
	let urlLink = link+"?boardId="+boardId;
	sessionStorage.removeItem("urlLink")
	sessionStorage.setItem("urlLink", urlLink ); 
	location.href="/comment/list?boardId="+encodeURI(boardId);
}

function confirm(boardId) {
	onConfirm("정말 삭제하시겠습니까?");
	document.getElementById('okBtn').onclick = function() {
		if(clickCheck(500) == "f") {
			return false;
		}
		offConfirm();
		remove(boardId)
	}
	document.getElementById('cancelBtn').onclick = function() {
		offConfirm();
	}
}

function remove(boardId) {
	location.replace("/board/removeDiscussion?boardId="+boardId);
}

//태그클릭
function searchKeyword(keyword) {
	location.href="/board/search/list?keyword="+encodeURI(keyword);
}