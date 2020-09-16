window.addEventListener("DOMContentLoaded",function(){
	let btnCheck = true;	
	$("form").on("submit", function() {
		if(btnCheck) {
			btnCheck = false;
 		   	let formData = $("form").eq(0).serialize();
			$("#loader").removeClass("skip");
	    	$.ajax({
				url : "/contact",
       		 	type : "post",
        		data : formData,
        		success : function(data) {
            		if (data) {
						$("#name").val("");
						$("#fromEmail").val("");
						$("#messageContext").val("메일을 보냈습니다! 문의주셔서 감사합니다.");				
						$("#loader").addClass("skip");
						btnCheck = true;
            		} else {
						$("#name").val("");
						$("#fromEmail").val("");
						$("#messageContext").val("죄송합니다. 전송오류입니다.");
						$("#loader").addClass("skip");
						btnCheck = true;
            		}
         		}
      		})
      		return false;
		} else {
			return false;
		}
	});
});