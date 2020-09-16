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
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
<script src="/js/board/board_common.js"></script>
<script src="/js/board/board_list.js"></script>
<link rel="stylesheet" href="/css/board.css" />
<link rel="stylesheet" href="/css/noscript.css" />
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
			<img src="/images/view.jpg" alt="" />
			<div class="inner">
				<h2 class="logo">봄-봄백과</h2>
				<h2 class="slogan">풍경편</h2>
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

	<section class="wrapper style">
		<div class="inner">
			<div class="function_board">
				<button class="postWirtingBtn" aria-label="글쓰기" data-tip="글쓰기" onclick="goPosting()">
					<span class="icon fas fa-pencil-alt"> 글쓰기</span>
				</button>
				<div class="postTool">
					<form name="searchfrm" action="/board/search/list" method="GET" >
						<div class="row gtr-uniform">
							<div class="col-6 col-12-xsmall" style="width: 110px;">
								<input type="text" name="keyword" id="keyword" value=""
									placeholder="검색어" style="width: 100px;" />
							</div>
							<div class="col-6 col-12-xsmall" style="width: 80px;">
								<a href="javascript:return false;" class="button icon solidfas fa-search"
									style="font-size: 0.8em; width: 80px;" onclick="searchKeyword()">검색</a>
							</div>
						</div>
					</form>
					<div class="array tool">
						<select name="arraySelect" id="arraySelect">
							<c:choose>
								<c:when test="${arrMethod == 'p'}">
									<option value="p" selected>댓글인기</option>
								</c:when>
								<c:otherwise>
									<option value="p">댓글인기</option>
								</c:otherwise>
								</c:choose>
							<c:choose>
								<c:when test="${arrMethod == 'u'}">
									<option value="u" selected>최근활동</option>
								</c:when>
								<c:otherwise>
									<option value="u">최근활동</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
			</div>
			<ul style="padding: 0;" id="boardList">
				
			</ul>
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