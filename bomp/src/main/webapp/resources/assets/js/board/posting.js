function sumbmitCheck() {
    if (document.postingForm.title.value == "") {
        myAlert("제목을 입력해주세요.");
        return false;
    }
	regexp = /^[0-9]*$/;
	if(regexp.test(document.postingForm.title.value)) {
		myAlert("제목은 숫자만 들어갈 수 없습니다.");
        return false;
	}
	regexp = /[\{\}\[\]\/;:|\)*~`^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	if(regexp.test(document.postingForm.title.value)) {
		myAlert("제목에는 (?.,!를 제외한) 특수문자를 사용할 수 없습니다.");
        return false;
	}
	if (document.postingForm.title.value.length > 50) {
        myAlert("제목은 최대 50글자 입력 가능합니다.");
        return false;
    }
	if (document.postingForm.content.value.length > 500) {
        myAlert("내용은 최대 500글자 입력 가능합니다.");
        return false;
    }
	if (document.postingForm.categoryName.value == "0") {
		myAlert("카테고리를 선택해주세요.");
        return false;
	}
	if (document.postingForm.categoryName.value == "토론방" && document.postingForm.imageFile.value != "") {
		myAlert("토론방에는 이미지를 삽입할 수 없습니다.");
        return false;
	}
	let tagArr = document.postingForm.boardTagStr.value.split("#");
	for (let i=1; i<tagArr.length; i++) {
		if(tagArr[i].trim().length > 10) {
			myAlert("각 태그는 10글자 이상 쓸수 없습니다.");
	        return false;			
		}
	}
	if(clickCheck(500) == "f") {
			return false;
	}
    document.postingForm.submit();
}

function cancel() {
	document.postingForm.title.value = "";
	document.postingForm.categoryName.value = "0";
	document.postingForm.content.value = "";
	document.postingForm.boardTag.value = "";
	document.postingForm.imageAlt.value = "";
	document.postingForm.imageFile.value = "";
}