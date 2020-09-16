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
<script src="/js/board/discussion.js"></script>
<link rel="stylesheet" href="/css/board.css" />
<link rel="stylesheet" href="/css/noscript.css" />
<style type="text/css">
.likeBox {
	display: inline;
}
</style>
</head>

<body class="is-preload">

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
			<img src="/images/bg02.jpg" alt="" />
			<div class="inner">
				<h2 class="logo">봄-봄백과 토론방</h2>
				<h2 class="slogan">다양한 주제로 마음껏 얘기해주세요.</h2>
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

	<section class="wrapper major-pad">
		<div class="inner">
			<div class="post">
				<div class="content">
					<a style="margin:-10px 0 20px 0;" class="button small fit icon fas fa-undo-alt" href="/discussion/list?pageNumStr=${pageNum}">목록 돌아가기</a>
					<div id="boardMarkBox${discussion.boardId}" class="optionBox" style="float: right;">
						<c:choose>
							<c:when test="${discussion.marked}">
								<a href="javascript:return false;"
									onclick="markBoard(${discussion.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${discussion.marked})"><span
									class="icon fas fa-bookmark" style="color:#ffd700; font-size:1.2em;"></span></a>
							</c:when>
							<c:when test="${!discussion.marked}">
								<a href="javascript:return false;"
									onclick="markBoard(${discussion.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${discussion.marked})"><span
									class="icon far fa-bookmark"></span></a>
							</c:when>
						</c:choose>
					</div>
					<p class="category">${discussion.categoryName}</p>
					<a href="javascript:return false;" onclick="showMember(${discussion.memberId})"><span
						class="postId" style="font-weight:bold;">${discussion.memberNick}</span></a><span class="postDate">&nbsp;&nbsp;
						${uDate}</span>
					<h3 class="comment">${discussion.title}</h3>
					<p class="postText comment">
						<a href="javascript:return false;"
							onclick="complainBoard(${discussion.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0})">${discussion.content}</a>
					</p>
					<c:if test="${boardTag != null}">
						<ul>
							<c:if test="${boardTag.tag1 != null}">
								<li><a href="javascript:return false;" onclick="searchKeyword('${boardTag.tag1}')"><span class="tag">#${boardTag.tag1}&nbsp;</span></a></li>
							</c:if>
							<c:if test="${boardTag.tag2 != null}">
								<li><a href="javascript:return false;" onclick="searchKeyword('${boardTag.tag2}')"><span class="tag">#${boardTag.tag2}&nbsp;</span></a></li>
							</c:if>
							<c:if test="${boardTag.tag3 != null}">
								<li><a href="javascript:return false;" onclick="searchKeyword('${boardTag.tag3}')"><span class="tag">#${boardTag.tag3}&nbsp;</span></a></li>
							</c:if>
							<c:if test="${boardTag.tag4 != null}">
								<li><a href="javascript:return false;" onclick="searchKeyword('${boardTag.tag4}')"><span class="tag">#${boardTag.tag4}&nbsp;</span></a></li>
							</c:if>
							<c:if test="${boardTag.tag5 != null}">
								<li><a href="javascript:return false;" onclick="searchKeyword('${boardTag.tag5}')"><span class="tag">#${boardTag.tag5}&nbsp;</span></a></li>
							</c:if>
						</ul>
					</c:if>
					<c:if test="${sessionScope.member.memberId == discussion.memberId or (sessionScope.member.memberId <= 5 and sessionScope.member.memberId != 0)}">
						<button class="button" style="box-shadow: none; border: none;"
							onclick="update(${discussion.boardId}, ${discussion.likeCnt}, ${discussion.commentCnt})">
							<span class="icon solid far fa-keyboard">&nbsp;수정</span>
						</button>
						<button class="button" style="box-shadow: none; border: none;"
							onclick="confirm(${discussion.boardId})">
							<span class="icon solid fas fa-trash-alt"
								style="font-weight: normal;">&nbsp;삭제</span>
						</button>
					</c:if>
					<hr>
					<div class="postComment">
						<div>
							<div id="boardLikeBox${discussion.boardId}" class="optionBox">
								<c:choose>
									<c:when test="${discussion.liked}">
										<a href="javascript:return false;"
											onclick="likeBoard(${discussion.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${discussion.liked}, ${discussion.likeCnt})"><span
											class="icon fas fa-heartbeat liked"></span>&nbsp;<span
											class="postCommentLike"><em>${discussion.likeCnt}</em>개</span></a>
									</c:when>
									<c:when test="${!discussion.liked}">
										<a href="javascript:return false;"
											onclick="likeBoard(${discussion.boardId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${discussion.liked}, ${discussion.likeCnt})"><span
											class="icon fas fa-heartbeat"></span>&nbsp;<span
											class="postCommentLike"><em>${discussion.likeCnt}</em>개</span></a>
									</c:when>
								</c:choose>
							</div>
							&nbsp;&nbsp; <span class="icon fas fa-comment-dots"></span> <span
								class="reCommentCnt"><em>${discussion.commentCnt}</em>개</span>
						</div>
						<ul style="padding: 0;" id="commentCntBox">
							<c:forEach var="cL" items="${commentList}" varStatus="i">
								<li class="comment"><a href="javascript:return false;"
									onclick="showMember(${cL.memberId})" style="font-weight:bold;"><span class="postCommentId">${cL.memberNick}</span></a>&nbsp;&nbsp;<a
									href="javascript:return false;"
									onclick="comment(${discussion.boardId})"><span
										class="postComment">${cL.commentText}</span></a> <span
									class="postDate">&nbsp;${commentUDateList[i.index]}&nbsp;&nbsp;</span>
									<div id="commentLikeBox${cL.commentId}" class="optionBox">
										<c:choose>
											<c:when test="${cL.liked}">
												<a href="javascript:return false;"
													onclick="likeComment(${cL.commentId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${cL.liked}, ${cL.likeCnt})"><span
													class="icon fas fa-heartbeat liked"></span>&nbsp;<span
													class="postCommentLike"><em>${cL.likeCnt}</em>개</span></a>
											</c:when>
											<c:when test="${!cL.liked}">
												<a href="javascript:return false;"
													onclick="likeComment(${cL.commentId}, ${sessionScope.member.memberId!=null?sessionScope.member.memberId:0}, ${cL.liked}, ${cL.likeCnt})"><span
													class="icon fas fa-heartbeat"></span>&nbsp;<span
													class="postCommentLike"><em>${cL.likeCnt}</em>개</span></a>
											</c:when>
										</c:choose>
									</div> &nbsp;&nbsp; <a href="javascript:return false;"
									onclick="comment(${discussion.boardId})"><span
										class="icon fas fa-comment-dots"></span> <span
										class="reCommentCnt"><em>${cL.recommentCnt}</em>개</span></a></li>
							</c:forEach>
						</ul>
						<a href="javascript:return false;"
							onclick="comment(${discussion.boardId})"
							class="button primary small fit icon fas fa-pencil-alt">
							댓글쓰기/더보기</a>
					</div>
				</div>
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