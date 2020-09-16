function goBoardList(boardId) {
	location.href = "/board/list?arrMethod=p&boardId="+boardId;
}

window.addEventListener('load', function() {
	let tempCommentText;
	let commentText = document.getElementsByClassName('commentText');
	for(let i=0; i<commentText.length; i++) {
		tempCommentText = commentText[i].innerText;
		if (tempCommentText.length>150) {
			commentText[i].innerText = tempCommentText.substring(0,150)+"...";
		}
	}
});