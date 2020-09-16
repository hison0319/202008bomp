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
<script src="/js/member/member.js"></script>
<link rel="stylesheet" href="/css/member.css" />
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
			<img src="/images/bg03.jpg" alt="" />
			<div class="inner">
				<h2 class="logo">봄-봄백과 회원정보</h2>
				<h2 class="slogan">함께해서 감사합니다. 활동을 늘려가 보세요!</h2>
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
		<div id="messageForm" class="skip" style="width:360px; height:360px; margin: 20% auto;
		padding: 40px 20px; background-color: rgba( 255, 255, 255, 0.9 );">
			<div class="row gtr-uniform">
				<input type="hidden" id="fromMemberId" value="${member.memberId}" readonly="readonly"/>
				<input type="hidden" id="toMemberId" value="${sessionScope.member.memberId}" readonly="readonly"/>
				<div class="col-6 col-12-xsmall">
					<label for="name">이름</label> <input type="text"
						name="memberNick" value="${sessionScope.member.memberNick}" readonly="readonly"/>
				</div>
				<div class="col-12">
					<label for="message">내용</label>
					<textarea id="messageContent" rows="5" placeholder="내용을 입력해주세요. 죄송합니다. 모바일 이모티콘 입력 시 오류가 날 수 있습니다."></textarea>
				</div>
				<div class="col-12">
					<ul class="actions">
						<li><button onclick="messageSend()" class="primary">보내기</button></li>
						<li><button onclick="messageCancel()">취소</button></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- container -->
	<section class="wrapper major-pad">
		<div class="inner">
			<div class="member_info">
				<span class="icon fas fa-user-astronaut"
					style="font-size: 1.1em; color: #ffd700;"></span> <span
					style="font-size: 0.9em; font-weight: bold;">&nbsp;&nbsp;카카오
					닉네임&nbsp;:&nbsp;${member.memberNick}</span>
				<c:if test="${member.memberId == sessionScope.member.memberId }">
					<button style="font-size: 0.6em; float: right;"
						onclick="memberNickBox()">닉네임 수정하기</button>
				</c:if>
				<c:if test="${member.memberId != sessionScope.member.memberId }">
					<button style="font-size: 0.6em; float: right;"
						onclick="messageBox(${sessionScope.member!=null?sessionScope.member.memberId:0})">메세지 남기기</button>
				</c:if>
				<br><br>
				<div class="nickBox skip">
					<form action="memberNickModify" name="memberNickForm" method="POST"
						style="margin-bottom: 10px;">
						<input type="hidden" name="memberId" value="${member.memberId}"
							readonly="readonly" /> <input type="text" id="memberNickInput"
							name="memberNick" value=""
							placeholder="특수문자 불가. 변경 시 중복체크됩니다. (최대 10글자)">
					</form>
					<ul class="actions fit">
						<li><button class="upLoad" onclick="memberNickCheck()">닉네임
								확인</button></li>
						<li><button class="cancel" onclick="memberNickModify()">수정</button></li>
					</ul>
				</div>
				<span class="icon far fa-calendar-check"
					style="font-size: 1.1em; color: #ffd700;"></span> <span
					style="font-size: 0.9em;">&nbsp;&nbsp;가입일&nbsp;:&nbsp;${memberRegDateSting}</span>
				<br> <br>
				<!--<c:choose>
					<c:when test="${member.membershipLevel == 0}">
						<h3><span class="icon fas fa-seedling"
									style="font-size: 1.2em; color: #ffd700;"></span>
							&nbsp;&nbsp;등급&nbsp;:&nbsp;<span style="font-size: 0.9em;">브론즈</span>
						</h3>
					</c:when>
					<c:when test="${member.membershipLevel == 1}">
						<h3><span class="icon fas fa-leaf"
									style="font-size: 1.2em; color: #ffd700;"></span>
							&nbsp;&nbsp;등급&nbsp;:&nbsp;<span style="font-size: 0.9em;">실버</span>
						</h3>
					</c:when>
					<c:when test="${member.membershipLevel == 2}">
						<h3><span class="icon fas fa-tree"
									style="font-size: 1.2em; color: #ffd700;"></span>
							&nbsp;&nbsp;등급&nbsp;:&nbsp;<span style="font-size: 0.9em;">골드</span>
						</h3>
					</c:when>
					<c:when test="${member.membershipLevel == 3}">
						<h3><span class="icon far fa-gem"
									style="font-size: 1.2em; color: #ffd700;"></span>
							&nbsp;&nbsp;등급&nbsp;:&nbsp;<span style="font-size: 0.9em;">플레티넘</span>
						</h3>
					</c:when>
				</c:choose>
				<br>
				<h3><span class="icon fas fa-chart-bar"
									style="font-size: 1.2em; color: #ffd700;"></span>
					&nbsp;&nbsp;활동점수&nbsp;:&nbsp;<span style="font-size: 0.9em;">${member.activityFigures}</span>
				</h3>
				<br>-->
				<!-- <h3 class="icon fas fa-users" style="display: block;">
					&nbsp;&nbsp;팔로워&nbsp;:&nbsp;<span style="font-size: 0.9em;">${member.followerCnt}</span>
				</h3>
				<h3 class="icon fas fa-walking" style="display: block;">
					&nbsp;&nbsp;팔로잉&nbsp;:&nbsp;<span style="font-size: 0.9em;">${member.followingCnt}</span>
				</h3> -->
				<div id="tagBox">
					<span class="icon fas fa-tags"
						style="font-size: 1.0em; color: #ffd700;"></span> <span
						style="font-size: 0.8em;">&nbsp;&nbsp;태그&nbsp;:&nbsp;${tagList}</span>&nbsp;&nbsp;
					<c:if test="${member.memberId == sessionScope.member.memberId }">
						<button style="font-size: 0.6em; float: right;"
							onclick="memberTagForm()">태그 수정하기</button>
					</c:if>
					<br> <br>
					<div class="tagRegBox skip">
						<input type="text" id="memberTag" name="memberTag"
							value="${tagList}"
							placeholder="팔로하고싶은 태그를 #으로 구분하여 입력해주세요.(최대 10개)"> <a
							href="javascript:return false;"
							onclick="memberTagReg(${sessionScope.member.memberId})"
							class="button primary small fit icon fas fa-pencil-alt"
							style="margin-bottom: 20px;">태그 등록</a>
					</div>
				</div>
				<ul class="actions fit">
					<li><button class="upLoad" aria-label="내가 쓴 게시물"
							data-tip="내가 쓴 게시물" onclick="memberBoardList(${member.memberId})">${member.memberNick}님의
							게시물</button></li>
					<c:if test="${member.memberId == sessionScope.member.memberId}">
						<li><button class="cancel" aria-label="북마크 게시물"
								data-tip="북마크 게시물"
								onclick="markBoardList(${sessionScope.member.memberId})">북마크
								게시물</button></li>
					</c:if>
				</ul>
				<c:if test="${member.memberId == sessionScope.member.memberId or (sessionScope.member.memberId <= 5 and sessionScope.member.memberId != 0)}">
					<button style="margin-top: 10px; font-size: 0.8em;"
						onclick="removeMember(${sessionScope.member.memberId})">회원탈퇴</button>
				</c:if>
			</div>
		</div>
	</section>
	<c:if test="${member.memberId == sessionScope.member.memberId}">
		<section class="wrapper style">
			<div class="inner">
				<div class="member_info">
					<c:choose>
						<c:when test="${member.alert > 99}">
							<h3>
								<span class="icon fas fa-bell"
									style="font-size: 1.2em; color: #ffd700;"></span>
								&nbsp;&nbsp;알람&nbsp;:&nbsp;<span style="font-size: 0.9em;">99+</span>
							</h3>
						</c:when>
						<c:when test="${member.alert > 0 && member.alert <= 99}">
							<h3>
								<span class="icon fas fa-bell"
									style="font-size: 1.2em; color: #ffd700;"></span>
								&nbsp;&nbsp;알람&nbsp;:&nbsp;<span style="font-size: 0.9em;">${member.alert}</span>
							</h3>
						</c:when>
						<c:when test="${member.alert == 0}">
							<h3>
								<span class="icon fas fa-bell" style="font-size: 1.1em;"></span>
								&nbsp;&nbsp;알람&nbsp;:&nbsp;<span style="font-size: 0.9em;">${member.alert}</span>
							</h3>
						</c:when>
					</c:choose>
					<br> <select name="arraySelect" id="arraySelect">
						<c:choose>
							<c:when test="${arrMethod == 'n'}">
								<option value="n" selected>확인 안함</option>
							</c:when>
							<c:otherwise>
								<option value="n">확인 안함</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${arrMethod == 'c'}">
								<option value="c" selected>확인함</option>
							</c:when>
							<c:otherwise>
								<option value="c">확인함</option>
							</c:otherwise>
						</c:choose>
					</select> <br>
					<ul id="alertList">
					</ul>
				</div>
			</div>
		</section>
	</c:if>
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