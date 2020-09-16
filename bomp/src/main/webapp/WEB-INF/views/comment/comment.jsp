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
<script src="/js/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/comment/comment.js"></script>
<link rel="stylesheet" href="/css/comment.css" />
<link rel="stylesheet" href="/css/noscript.css" />
<style type="text/css">
#header .navPanelToggle:before {
	color: #333;
}
</style>
</head>

<body class="is-preload">

	<!-- Header -->
	<header id="header" class="alt" style="color: #333;">
		<nav id="nav" style="color: #333;">
			<ul>
				<li><a href="${pageContext.request.contextPath}/" Tabindex="1"
					style="color: #333;">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list"
					Tabindex="2" style="color: #333;">봄-봄백과(전체보기)</a>
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/board/nature/list">자연</a></li>
						<li><a
							href="${pageContext.request.contextPath}/board/object/list">(인위적)사물</a></li>
						<li><a
							href="${pageContext.request.contextPath}/board/scenery/list">풍경</a></li>
						<li><a
							href="${pageContext.request.contextPath}/board/person/list">인물</a></li>
						<li><a
							href="${pageContext.request.contextPath}/board/etc/list">기타(분류에
								없는)</a></li>
					</ul></li>
				<li><a
					href="${pageContext.request.contextPath}/discussion/list"
					Tabindex="4" style="color: #333;">토론방</a></li>
				<li><a href="${pageContext.request.contextPath}/board/posting"
					Tabindex="5" style="color: #333;">바로글쓰기</a></li>
				<c:choose>
					<c:when test="${sessionScope.member == null}">
						<li><a
							href="https://kauth.kakao.com/oauth/authorize?client_id=****&redirect_uri=https://bombompedia.com/kakaologin&response_type=code"
							Tabindex="6" class="button primary small fit"
							style="border: 1px solid #333; color: #333;"> 카카오 로그인</a></li>
					</c:when>
					<c:when test="${sessionScope.member != null}">
						<li><a href="${pageContext.request.contextPath}/member/info"
							Tabindex="6" style="color: #333;">${sessionScope.member.memberNick}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"
							Tabindex="7" class="button primary small fit"
							style="border: 1px solid #333; color: #333;"> 로그아웃</a></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</header>

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

	<section class="wrapper major-pad">
		<div class="inner">
			<div class="post">
				<input type="hidden" id="boardId" value="${board.boardId}" readonly="readonly"/>
				<a style="margin: -50px 0 10px 0;" id="gobackBtn"
					class="button small fit icon fas fa-undo-alt" href="#">목록 돌아가기</a>
				<div id="boardMarkBox${board.boardId}" class="optionBox"
					style="width:30px; position: relative; left: 98%;top: 5px;">
					<c:choose>
						<c:when test="${board.marked}">
							<a href="javascript:return false;"
								onclick="markBoard(${board.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${board.marked})"><span
								class="icon fas fa-bookmark"
								style="color: #ffd700; font-size: 1.2em;"></span></a>
						</c:when>
						<c:when test="${!board.marked}">
							<a href="javascript:return false;"
								onclick="markBoard(${board.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${board.marked})"><span
								class="icon far fa-bookmark"></span></a>
						</c:when>
					</c:choose>
				</div>
				<a href="javascript:return false;" onclick="showMember(${board.memberId})"
					style="margin-top: -20px;"><span>${memberNick}</span></a><br>
				<h3 class='comment'>${board.title}</h3>
				<p class="postText comment">
					<a href="javascript:return false;"
						onclick="complainBoard(${board.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0})">
						${board.content} </a><span class="postDate">&nbsp;&nbsp;${boardUDate}</span>
				</p>
				<c:if test="${boardTag != null}">
					<ul>
						<c:if test="${boardTag.tag1 != null}">
							<li><a href="javascript:return false;"
								onclick="searchKeyword('${boardTag.tag1}')"><span
									class="tag">#${boardTag.tag1}&nbsp;</span></a></li>
						</c:if>
						<c:if test="${boardTag.tag2 != null}">
							<li><a href="javascript:return false;"
								onclick="searchKeyword('${boardTag.tag2}')"><span
									class="tag">#${boardTag.tag2}&nbsp;</span></a></li>
						</c:if>
						<c:if test="${boardTag.tag3 != null}">
							<li><a href="javascript:return false;"
								onclick="searchKeyword('${boardTag.tag3}')"><span
									class="tag">#${boardTag.tag3}&nbsp;</span></a></li>
						</c:if>
						<c:if test="${boardTag.tag4 != null}">
							<li><a href="javascript:return false;"
								onclick="searchKeyword('${boardTag.tag4}')"><span
									class="tag">#${boardTag.tag4}&nbsp;</span></a></li>
						</c:if>
						<c:if test="${boardTag.tag5 != null}">
							<li><a href="javascript:return false;"
								onclick="searchKeyword('${boardTag.tag5}')"><span
									class="tag">#${boardTag.tag5}&nbsp;</span></a></li>
						</c:if>
					</ul>
				</c:if>
				<c:choose>
					<c:when test="${sessionScope.member == null}">
						<form>
							<div class="col-12">
								<label for="message">Message</label>
								<textarea name="message" id="message" rows="1">댓글은 카톡로그인 시 작성가능합니다.</textarea>
							</div>
						</form>
					</c:when>
					<c:when test="${sessionScope.member != null}">
						<form method="post" action="registComment"
							name="registCommentForm">
							<input type="hidden" name="boardId" value="${board.boardId}"
								readonly="readonly"> <input type="hidden"
								name="memberId" value="${sessionScope.member.memberId}"
								readonly="readonly">
							<div class="col-12">
								<label for="commentText">commentText</label>
								<textarea name="commentText" id="commentText" rows="4"
									maxlength="500" placeholder="내용을 입력해주세요. 최대 500자 죄송합니다. 현재 모바일 이모티콘은 사용할 수 없습니다."></textarea>
							</div>
							<a href="javascript:return false;" onclick="insertCommentCheck()"
								class="button primary small fit icon fas fa-pencil-alt">댓글
								쓰기</a>
						</form>
					</c:when>
				</c:choose>
				<!-- <form method="post" action="#" name="searchfrm" class="search"
					style="margin-bottom: 5px;">
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							<input type="text" name="demo-name" id="demo-name" value=""
								placeholder="댓글 검색어 입력" />
						</div>
						<div class="col-6 col-12-xsmall">
							<a href="javascript:return false;" onclick="searchKeyword(${boardTag.tag1})" class="button icon solidfas fa-search"
								style="font-size: 0.8em;">검색</a>
						</div>
					</div>
				</form> -->
				<div class="arrayComent">
					<select name="arraySelect" id="arraySelect">
						<c:choose>
							<c:when test="${arrMethod == 'p'}">
								<option value="p" selected>인기순</option>
							</c:when>
							<c:otherwise>
								<option value="p">인기순</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${arrMethod == 'u'}">
								<option value="u" selected>최신순</option>
							</c:when>
							<c:otherwise>
								<option value="u">최신순</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<hr>
				<div>
					<div id="boardLikeBox${board.boardId}" class="optionBox">
						<c:choose>
							<c:when test="${board.liked}">
								<a href="javascript:return false;"
									onclick="likeBoard(${board.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${board.liked}, ${board.likeCnt})"><span
									class="icon fas fa-heartbeat liked"></span>&nbsp;<span
									class="postCommentLike"><em>${board.likeCnt}</em>개</span></a>
							</c:when>
							<c:when test="${!board.liked}">
								<a href="javascript:return false;"
									onclick="likeBoard(${board.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${board.liked}, ${board.likeCnt})"><span
									class="icon fas fa-heartbeat"></span>&nbsp;<span
									class="postCommentLike"><em>${board.likeCnt}</em>개</span></a>
							</c:when>
						</c:choose>
					</div>
					&nbsp;&nbsp; <span class="icon fas fa-comment-dots"></span>&nbsp;<span><em
						id="commentCnt">${board.commentCnt}</em>개</span>
				</div>
				<ul id="commentList">
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
	<script src="/js/jquery.dropotron.min.js"></script>
	<script src="/js/jquery.scrollex.min.js"></script>
	<script src="/js/browser.min.js"></script>
	<script src="/js/breakpoints.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>

</body>

</html>