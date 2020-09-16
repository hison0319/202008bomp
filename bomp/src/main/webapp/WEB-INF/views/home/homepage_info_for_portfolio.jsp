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
<script src="/js/hosting/contact.js"></script>
<link rel="stylesheet"
	href="/css/main.css" />
<link rel="stylesheet" href="/css/noscript.css" />
<style type="text/css">
#header .navPanelToggle:before {
	color: #333;
}

ul > li {
	margin-left:20px;
}

blockquote {
	font-style : normal;
}

.red {
	color : red;
}

/*.image_box {
	width: 100%;
	height: 300px;
	margin: 0 auto;
	background-image : url("images/bompERmodel.jpg");
	background-repeat : no-repeat;
	background-size : cover;
}*/

.image_box {
	background-image : url("images/bompERmodel.jpg");
	background-repeat: no-repeat;
  	background-size: 100%;
  	height: 75vw;
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
			<h1 style="font-size: 2em;line-height: 1.5em;">안녕하세요! 방문해주셔서 감사합니다. 홈페이지 정보를 소개하겠습니다.</h1>
			<h1 style="font-size: 2em;line-height: 1.5em;">제목 : 봄-봄백과사전</h1>
			<h2>기간 : `20. 8. 15 ~ `20. 9. 13</h2>
			<h2>기관 : 더조은 아카데미 컴퓨터 학원</h2>
			<h2>인원 : 손한이(개인 프로젝트)</h2>
			<h3 style="line-height: 1.8em;">개요 : 시각장애인을 위한 백과사전을 만드는 커뮤니티. 게시글로 단어를 제시. 볼 수 있는 사람들의 시각정보를 댓글로 모으고, 공감성이 높은 댓글을 좋아요로 선별. 게시글 피드 정렬, 게시글 작성, 좋아요,
				태그, 댓글, 답글 기능 등을 갖춘 스프링 프레임워크 기반의 웹 서버 개발 프로젝트 입니다.</h3>
		</div>
	</section>

	<section class="wrapper style">
		<div class="inner">
			<header>
				<h4>개발 환경</h4>
				<p>Java 1.8.0<br>
				Apache Tomcat 9.0.35<br>
				MySQL 8.0.20<br>
				Spring 3.9. 13<br>
				Servlet 4.0<br>
				Jquery 1.12.4<br>
				Window10, eclipse 4.16.0, HTML5, CSS3, JSP 2.3</p>
			</header>
			<h4>요구사항 정의서(기능 정리)</h4>
			<ul>
				<li><h5>홈</h5>
				<blockquote>1. 실시간 베스트 게시물 3개 표시 (전체 게시물 중 좋아요가 가장많이 달린 댓글을 보유한 게시물 3개 표시)<br>
				2. 메일 보내기 서비스 <span class="red">(dependency javax.mail)</span></blockquote>
				</li>
				<li><h5>회원가입 및 로그인</h5>
				<blockquote>1. 카카오 로그인만 허용 <span class="red">(카카오 디벨로퍼 REST방식)</span><br>
				2. 최초 로그인 시 디비에 카카오 계정 고유번호만 저장. 별도의 회원가입 없이 회원 활동 가능.</blockquote>
				</li>
				<li><h5>멤버 페이지</h5>
				<blockquote>1. 멤버 닉네임 변경 기능 <span class="red">(스크립트 유효성검사 및 ajax활용 중복체크)</span><br>
				2. 멤버 태그 삽입 및 수정 기능 (태그로 설정한 키워드를 가진 게시물을 피드에서 우선적으로 봄) <span class="red">(ajax비동기식)</span><br>
				3. 내가 작성한 게시물 보기 기능<br>
				4. 북마크 해놓은 게시물 보기 기능<br>
				5. 회원 탈퇴 기능 <span class="red">(DBMS활용 cascade방식 데이터 무결성)</span><br>
				6. 알람 확인하기 기능 (확인 안한 알람과 확인 한 알람 구분) <span class="red">(ajax비동기식)</span><br>
				7. 메세지 남기기 기능</blockquote>
				</li>
				<li><h5>게시판</h5>
				<blockquote>1. 게시글 피드 순(태그한 키워드가 있는 게시글 -> 태그한 키워드가 없는 게시글)(기본 각 인기순 정렬)<span class="red">(ajax비동기식 출력)</span><br>
				2. 글쓰기 <span class="red">(스크립트 유효성검사)(파일 업로드 용량및 확장자 체크 dependency commons-fileupload)</span><br>
				3. 글 수정하기 (좋아요와 댓글이 일정 범위 초과 시 수정 불가)<br>
				4. 북마크 기능 <span class="red">(ajax비동기식)</span><br>
				5. 좋아요 기능 <span class="red">(ajax비동기식)</span><br>
				6. 댓글로 이동 후 돌아오기 시 기존 게시글 위치로 돌아오는 기능  <span class="red">(스크립트 스크롤러 활용)</span><br>
				7. 검색 기능 (통합 검색) (태그검색 결과 -> 제목 및 내용 검색 결과로 정렬)<br>
				8. 정렬 기능 (태그 순서는 유지/ 인기순과 최근 활동 순으로 정렬 가능)<br>
				&nbsp;&nbsp;&nbsp;&nbsp;- 인기순 : 좋아요를 많이 받은 댓글을 보유한 게시글<br>
				&nbsp;&nbsp;&nbsp;&nbsp;- 최근활동순 : 최근에 댓글이 달린 게시글<br>
				9. 각 카테고리 분류/ 토론방은 정적 리스트와 페이지 네이션으로 출력<br>
				10. 멤버 닉네임을 누르면 해당 멤버 페이지로 이동</blockquote>
				</li>
				<li><h5>댓글</h5>
				<blockquote>1. 댓글 정렬 (인기순, 최신순 정렬) <span class="red">(ajax비동기식)</span><br>
				2. 댓글 달기 <br>
				3. 댓글 좋아요 <span class="red">(ajax비동기식)</span><br>
				4. 답글 달기 <span class="red">(ajax비동기식)</span><br>
				5. 답글 좋아요  <span class="red">(ajax비동기식)</span><br>
				6. 댓글, 답글 삭제 <span class="red">(ajax비동기식)</span><br>
				7. 태그 키워드를 누루면 태그 검색으로 이동<br>
				8. 멤버 닉네임을 누르면 해당 멤버 페이지로 이동</blockquote>
				</li>
				<li><h5>기타</h5>
				<blockquote>1. 보안 향상을 위한 Java class config활용<br>
				2. 커넥션 풀 활용 <span class="red">(Hikari jdbc connection pool 활용)</span><br>
				3. ajax를 위한 데이터 바인딩 <span class="red">(dependency jackson-databind, gson 활용)</span><br>
				4. 에러 페이지 사용자 설정 <span class="red">(@ExceptionHandler 활용)</span><br>
				5. 크롬 브라우저에서만 정상 작동(브라우저 체크)</blockquote>
				</li>
			</ul>
			<h4>ER 다이어그램</h4>
				<div class="image_box">
				</div>
			<!-- <h4>git 주소(private) : </h4>
			<a href="https://github.com/hison0319/202008bomp_project" target="_blank">https://github.com/hison0319/202008bomp_project</a> -->
		</div>
	</section>
	
	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
		<br><br><br><br>
			<div class="aboutUsSub">
				<strong style="font-size:3em;">읽어 주셔서 감사합니다!</strong>	
			</div>
		<br><br><br><br>
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