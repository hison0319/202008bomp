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
<script src="/js/hosting/hosting.js"></script>
<script src="/js/hosting/contact.js"></script>
<link rel="stylesheet"
	href="/css/main.css?" />
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
			<img src="images/bg01.jpg" alt="" />
			<div class="inner">
				<h2 class="logo">봄-봄백과</h2>
				<h2 class="slogan">봄과 봄을 잇는, 함께쓰는 백과사전</h2>
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
			<section class="spotlight">
				<div class="content">
					<h2 class="aboutUs" Tabindex="5">About us</h2>
					<p class="aboutUs_p" Tabindex="6">
						'본다'는 것은 어떤 의미일까요?<br /> 국어사전의 첫 번째 의미는 "눈으로 대상의 존재나 형태적 특징을 안다"는
						것입니다. 하지만 거기에는 "대상의 내용이나 상태를 알기 위하여 살피다"라는 의미도 포함됩니다. <br /> <br />
						시각장애인 역시 우리와 다른 방법으로 사물을 느끼고 판단합니다. 다만 '눈'의 기능이 우리와 다를 뿐이지요. 그런
						의미에서 시각장애인 역시 '본다'는 행위를 하고 있는 것인지도 모르겠습니다.<br /> <br /> 봄봄백과는
						시각장애인과 시각장애인이 아닌 사람들의 '봄'을 공유하기 위해 만들어졌습니다. 다른 방법으로 세상을 바라보는 일은,
						시각 능력을 기준으로 구분되는 서로를 이해하도록 하는 출발점이 될 수 있을 것입니다.<br /> 이제 우리의 봄을
						모두와 함께 나눠볼 때입니다. 당신의 봄을 백과사전에 공유해주세요!
					</p>
				</div>
			</section>
		</div>
	</section>

	<section class="wrapper style">
		<div class="inner">
			<h2>Best post</h2>
			<div class="posts">
				<c:forEach var="bL" items="${boardList}" varStatus="i">

					<section class="post">
						<span class="image"><img src="${bL.imgAddress}"
							alt="${bL.imgAlt}" /></span>
						<div class="content">
							<a href="javascript:return false;"
								onclick="showMember(${bL.memberId})"><span class="postId">${bL.memberNick}</span></a><span
								class="postDate">&nbsp;&nbsp;&nbsp;${boardUDateList[i.index]}</span>
							<h3>${bL.title}</h3>
							<div class="postComment">
								<ul>
									<c:forEach var="cL" items="${bL.commentList}" varStatus="j">
										<li><a href="javascript:return false;"
											onclick="showMember(${cL.memberId})"><span
												class="postCommentId1" style="font-weight:bold;">${cL.memberNick}</span></a> <span
											class="commentText">&nbsp; ${cL.commentText}</span><span
											class="postDate">&nbsp;&nbsp;${cL.UDateStr}&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
											</div>
									</c:forEach>
								</ul>
								<ul class="actions">
									<li><a href="javascript:return false;"
										onclick="goBoardList(${bL.boardId})" class="button">더보기</a></li>
								</ul>
							</div>
						</div>
					</section>
				</c:forEach>
			</div>
		</div>
	</section>

	<section class="wrapper style">
		<div class="inner">
			<h2>Category</h2>
			<div class="posts">
				<section class="post">
					<a href="${pageContext.request.contextPath}/board/nature/list">
						<span class="image"><img src="images/whale.jpg" alt="" /></span>
						<h3>자연</h3>
					</a>
				</section>
				<section class="post">
					<a href="${pageContext.request.contextPath}/board/object/list">
						<span class="image"><img src="images/seoul.jpg" alt="" /></span>
						<h3>(인위적)사물</h3>
					</a>
				</section>
				<section class="post">
					<a href="${pageContext.request.contextPath}/board/scenery/list">
						<span class="image"><img src="images/view.jpg" alt="" /></span>
						<h3>풍경</h3>
					</a>
				</section>
				<section class="post">
					<a href="${pageContext.request.contextPath}/board/person/list">
						<span class="image"><img src="images/people.jpg" alt="" /></span>
						<h3>인물</h3>
					</a>
				</section>
				<section class="post">
					<a href="${pageContext.request.contextPath}/board/etc/list"> <span
						class="image"><img src="images/idea.jpg" alt="" /></span>
						<h3>기타(분류에없는)</h3>
					</a>
				</section>
				<section class="post">
					<a href="${pageContext.request.contextPath}/discussion/list"> <span class="image"><img
							src="images/discuss.jpg" alt="" /></span>
						<h3>토론방</h3>
					</a>
				</section>
			</div>
		</div>
	</section>

	<!-- Contact -->
	<section id="contact" class="wrapper split">
		<div class="inner">
			<section>
				<h2>contact</h2>
				<form>
					<div class="row gtr-uniform">
						<div class="col-6 col-12-large col-6-medium col-12-xsmall">
							<label for="name">이름</label> <input type="text" name="name"
								id="name" placeholder="이름" />
						</div>
						<div class="col-6 col-12-large col-6-medium col-12-xsmall">
							<label for="email">메일주소</label> <input type="email"
								name="fromEmail" id="fromEmail" placeholder="메일주소" />
						</div>
						<div class="col-12">
							<label for="message">내용</label>
							<textarea name="messageContext" id="messageContext" rows="5" placeholder="내용을 입력해주세요."></textarea>
						</div>
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="보내기" class="primary" /></li>
								<li><input type="reset" value="다시쓰기" /></li>
							</ul>
						</div>
					</div>
				</form>
			</section>
			<section>
				<h2>infomation us</h2>
				<ul class="bulleted-icons">
					<li><span class="icon-wrapper"><span
							class="icon solid fa-envelope"></span></span>
						<h3>메일주소</h3>
						<p>hison0319test@gmail.com</p></li>
					<li><span class="icon-wrapper"><span
							class="icon solid fa-phone"></span></span>
						<h3>전화번호</h3>
						<p>(010) 7530-0079</p></li>
					<li><span class="icon-wrapper"><span
							class="icon brands fab fa-instagram"></span></span>
						<h3>Instagram</h3>
						<p>
							<a
								href="javascript:void(window.open('https://www.instagram.com/bombom_pedia', '_blank'))">@bombom_pedia</a>
						</p></li>
					<li><span class="icon-wrapper"><span
							class="icon solid fa-home"></span></span>
						<h3>주소</h3>
						<p>
							<br /> <br />
						</p></li>
				</ul>
			</section>
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