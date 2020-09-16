<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=****"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', '****');
</script>

<title>봄봄백과</title>
<meta charset="utf-8" />
<meta name="keywords" content="봄봄백과, 시각장애인, 백과사전, 흰지팡이, bombompedia" />
<meta name="description" content="시각장애인을 위한, 그리고 우리 모두를 위한 백과사전을 만들어 보세요!" />
<meta name="author" content="hani" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />

<meta property="og:url" content="https://bombompedia.com/" />
<meta property="og:type" content="website" />
<meta property="og:title" content="봄봄백과" />
<meta property="og:description" content="시각장애인을 위한, 그리고 우리 모두를 위한 백과사전을 만들어 보세요!" />
<meta property="og:image" content="/images/bg01.jpg" />

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/board/modify.js"></script>
<link rel="stylesheet" href="/css/posting.css" />
<link rel="stylesheet" href="/css/noscript.css" />
</head>
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>

<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="" class="is-preload">

	<!-- Header -->
	<header id="header" class="alt">
		<nav id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/" Tabindex="1">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list"
					Tabindex="2">봄-봄백과(전체보기)</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/board/nature/list">자연</a></li>
						<li><a href="${pageContext.request.contextPath}/board/object/list">(인위적)사물</a></li>
						<li><a href="${pageContext.request.contextPath}/board/scenery/list">풍경</a></li>
						<li><a href="${pageContext.request.contextPath}/board/person/list">인물</a></li>
						<li><a href="${pageContext.request.contextPath}/board/etc/list">기타(분류에
								없는)</a></li>
					</ul></li>
				<li><a
					href="${pageContext.request.contextPath}/discussion/list"
					Tabindex="4">토론방</a></li>
				<li><a href="${pageContext.request.contextPath}/board/posting"
					Tabindex="5">바로글쓰기</a></li>
				<c:choose>
					<c:when test="${sessionScope.member == null}">
						<li><a
							href="https://kauth.kakao.com/oauth/authorize?client_id=****&redirect_uri=https://bombompedia.com/kakaologin&response_type=code"
							Tabindex="6" class="button primary small fit"> 카카오 로그인</a></li>
					</c:when>
					<c:when test="${sessionScope.member != null}">
						<li><a href="${pageContext.request.contextPath}/member/info"
							Tabindex="6">${sessionScope.member.memberNick}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"
							Tabindex="7" class="button primary small fit"> 로그아웃</a></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</header>
	
	<section id="banner">
		<article>
			<img src="/images/bg04.jpg" alt="" />
			<div class="inner">
				<h2 class="logo">봄-봄백과 게시글 수정</h2>
				<h2 class="slogan">궁굼한 단어를 적어보세요.</h2>
			</div>
		</article>
	</section>
	
	<div class="loader skip" id="loader"></div>
	<div id="my_banner" class="my_banner skip">
		<div id="alert" class="skip">
			<p class="alert_comment_box">
				<i class="fas fa-bullhorn"></i><span id="alert_comment"></span>
			</p>
			<a class="skip" href="javascript:return false;" id="okBtn"> 확인</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="skip" href="javascript:return false;" id="cancelBtn">
				취소</a>
		</div>
	</div>

	<!-- container -->
    <section class="wrapper style">
        <div class="inner">
            <div class="posting">
                <form action="modify" name="modifyForm" method="POST" enctype="multipart/form-data">
                    <div class="textWindow">
                        <select name="" id="category" disabled="disabled">
                            <option value="${board.categoryName}" selected>${board.categoryName}</option>
                        </select>
                    <input type="hidden" name="categoryName" value="${board.categoryName}">
                        <div class="col-6 col-12-xsmall">
                        	<input type="hidden" name="boardId" value="${board.boardId}" readonly/>
                        	<input type="hidden" name="memberId" value="${board.memberId}" readonly/>
                            <input type="text" name="memberNick" id="memberNick" value="${board.memberNick}" readonly/>
                            <input type="text" name="title" id="title" value="${board.title}" placeholder="제목 (단어)"
                                maxlength="100" />
                        </div>
                        <div class="col-12">
                            <textarea name="content" id="content" placeholder="내용을 입력해주세요. 최대 1000자. 죄송합니다. 현재 모바일 이모티콘은 사용할 수 없습니다."
                                maxlength="1000" rows="6">${board.content}</textarea>
                        </div>
                    </div>
                    <input type="text" name="boardTagStr" id="boardTag" value="${boardTagString}" placeholder="태그를 #으로 구분해서 넣어주세요!(5개 이하)" />
                    <br>
                    <div class="fileInput">
                        이미지 업로드(1장)&nbsp;<span class="icon fas fa-paperclip"></span>&nbsp;&nbsp;&nbsp;<input type="file"
                            class="imgAttachment" name="imageFile" 
                            accept="image/gif, image/jpeg, image/jpg, image/x-png"
                            aria-label="이미지업로드" data-tip="이미지업로드‬">&nbsp;&nbsp;최대 2MB
                    </div>
                    <input type="hidden" name="imgAddress" value="${board.imgAddress}">
                    <br>
                    <input type="text" name="imgAlt" id="imgAlt" value="${board.imgAlt}" placeholder="시각장애인을 위해 간단한 이미지 설명을 해주세요." />
                </form>
                <ul class="actions fit">
                    <li><button class="upLoad" aria-label="게시하기" data-tip="게시하기" onclick="sumbmitCheck()">수정하기</button></li>
                </ul>
            </div>
        </div>
    </section>

	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="aboutUsSub">
				<strong>봄-봄백과<br /></strong>
				<p>
					시각장애인과 시각장애인이 아닌 사람들의 봄을 공유함으로써 모두를 위한 백과사전을 만드는 공간입니다.<br /> 이
					활동은 우리 모두에게 큰 도움이 될 것 입니다. <br />당신의 봄을 백과사전에 공유해주세요!
				</p>
			</div>
			<p class="copyright">&copy; Untitled Corp. All rights reserved.</p>
			<ul class="menu">
				<li><a href="#">이용약관</a></li>
				<li><a href="#">사이트 정책</a></li>
			</ul>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.dropotron.min.js"></script>
	<script src="/js/jquery.scrollex.min.js"></script>
	<script src="/js/browser.min.js"></script>
	<script src="/js/breakpoints.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>

</body>

</html>