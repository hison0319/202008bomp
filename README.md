### 죄송합니다. 코딩 과정을 담은 git은 호스팅 패스워드 등의 노출 문제로 본 깃으로 대체합니다.

# 제목 : 봄-봄백과사전
## 기간 : `20. 8. 15 ~ `20. 9. 13
## 기관 : 더조은 아카데미 컴퓨터학원
## 팀원 : 손한이(개인 프로젝트)
### 개요 : 시각장애인을 위한 백과사전을 만드는 커뮤니티. 게시글로 단어를 제시. 볼 수 있는 사람들의 시각정보를 댓글로 모으고, 공감성이 높은 댓글을 좋아요로 선별. 게시글 피드 정렬, 게시글 작성, 좋아요, 태그, 댓글, 답글 기능 등을 갖춘 스프링 프레임워크 기반의 웹 서버 개발 프로젝트 입니다.
> 1. 개발 환경
>> *	Java 1.8.0
>> *	Apache Tomcat 9.0.35
>> *	MySQL 8.0.20
>> *	Spring 3.9.13
>> *	Servlet 4.0
>> *	Jquery 1.12.4
>> *	Window10, eclipse 4.16.0, HTML5, CSS3, JSP 2.3

> 2. 요구사항 정의서(기능 정리)
>> * 홈
>>> - 실시간 베스트 게시물 3개 표시 (전체 게시물 중 좋아요가 가장많이 달린 댓글을 보유한 게시물 3개 표시)
>>> - 메일 보내기 서비스 (dependency javax.mail)

>> * 회원가입 및 로그인
>>> - 카카오 로그인만 허용 (카카오 디벨로퍼 REST방식)
>>> - 최초 로그인 시 디비에 카카오 계정 고유번호만 저장. 별도의 회원가입 없이 회원 활동 가능.

>> * 멤버 페이지
>>> - 멤버 닉네임 변경 기능 (스크립트 유효성검사 및 ajax활용 중복체크)
>>> - 멤버 태그 삽입 및 수정 기능 (태그로 설정한 키워드를 가진 게시물을 피드에서 우선적으로 봄) (ajax비동기식)
>>> - 내가 작성한 게시물 보기 기능
>>> - 북마크 해놓은 게시물 보기 기능
>>> - 회원 탈퇴 기능 (DBMS활용 cascade방식 데이터 무결성)
>>> - 알람 확인하기 기능 (확인 안한 알람과 확인 한 알람 구분) (ajax비동기식)
>>> - 메세지 남기기 기능

>> * 게시판
>>> - 게시글 피드 순(태그한 키워드가 있는 게시글 -> 태그한 키워드가 없는 게시글)(기본 각 인기순 정렬)(ajax비동기식 출력)
>>> - 글쓰기 (스크립트 유효성검사)(파일 업로드 용량및 확장자 체크 dependency commons-fileupload)
>>> - 글 수정하기 (좋아요와 댓글이 일정 범위 초과 시 수정 불가)
>>> - 북마크 기능 (ajax비동기식)
>>> - 좋아요 기능 (ajax비동기식)
>>> - 댓글로 이동 후 돌아오기 시 기존 게시글 위치로 돌아오는 기능 (스크립트 스크롤러 활용)
>>> - 검색 기능 (통합 검색) (태그검색 결과 -> 제목 및 내용 검색 결과로 정렬)
>>> - 정렬 기능 (태그 순서는 유지/ 인기순과 최근 활동 순으로 정렬 가능)
>>>> + 인기순 : 좋아요를 많이 받은 댓글을 보유한 게시글
>>>> + 최근활동순 : 최근에 댓글이 달린 게시글
>>> - 각 카테고리 분류/ 토론방은 정적 리스트와 페이지 네이션으로 출력
>>> - 멤버 닉네임을 누르면 해당 멤버 페이지로 이동

>> * 댓글
>>> - 댓글 정렬 (인기순, 최신순 정렬) (ajax비동기식)
>>> - 댓글 달기
>>> - 댓글 좋아요 (ajax비동기식)
>>> - 답글 달기 (ajax비동기식)
>>> - 답글 좋아요 (ajax비동기식)
>>> - 댓글, 답글 삭제 (ajax비동기식)
>>> - 태그 키워드를 누루면 태그 검색으로 이동
>>> - 멤버 닉네임을 누르면 해당 멤버 페이지로 이동

>> * 기타
>>> - 보안 향상을 위한 Java class config활용
>>> - 커넥션 풀 활용 (Hikari jdbc connection pool 활용)
>>> - ajax를 위한 데이터 바인딩 (dependency jackson-databind, gson 활용)
>>> - 에러 페이지 사용자 설정 (@ExceptionHandler 활용)
>>> - 크롬 브라우저에서만 정상 작동(브라우저 체크)

# 감사합니다!
