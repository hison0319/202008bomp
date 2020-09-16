function show(boardId, pageNum) {
	location.href="show?boardId="+encodeURI(boardId)+"&pageNum="+encodeURI(pageNum);
}

window.onload = function() {
	let tempTitle;
	let title = document.getElementsByClassName('title');
	for(let i=0; i<title.length; i++) {
		tempTitle = title[i].innerText;
		if (tempTitle.length>10) {
			title[i].innerText = tempTitle.substring(0,10)+"...";
		}
	}
}