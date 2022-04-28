# Step.1 - Servlet/JSP 기반 웹 애플리케이션 + 메모리 기반 Repository 사용
## 요구사항
+ maven project
+ packaging: war
+ MVC Pattern + FrontController Pattern 적용
  + view 페이지는 JSP 사용
  + JSP 내 Scriptlet 사용 최소화
+ 모든 메세지는 영어, 한국어 2개 언어 지원
  + 언어 변경 가능
+ 로그인/로그아웃
  + 로그인하지 않고 다른 기능 사용 불가
+ 사용자 등록/수정/삭제/조회
+ 게시물 등록/수정/삭제/조회
+ 게시물 조회 성공 시 조회 수 업데이트
  + 필터로 처리
+ 게시물 조회, 사용자 조회 시 방문자 수 업데이트
  + 필터로 처리
  + 방문자 수는 파일에 기록해서 서버 중지 후 재가동하여도 방문자 수 유지되어야 함

## 사용자
+ 사용자 속성
  + 아이디
  + 비밀번호
  + 이름
  + 프로필 이미지
+ 특별한 사용자 - 관리자
  + 아이디: admin
  + 비밀번호: 12345
  + 이름: 관리자
  + 프로필 이미지 : X
+ 관리자는 ServletContext 초기화 시점에 등록
+ 사용자 아이디, 비밀번호는 로그인 시에만 사용
+ 사용자 조회 화면에서는 이름과 프로필 이미지만 보여준다
  + 기능적으로 사용자를 구분하기 위해 아이디 사용은 가능
+ 사용자 interface
``` java
public interface User {
String getId();
void setId(String id);

    String getPassword();
    void setPassword(String password);

    String getName();
    void setName(String name);

    String getProfileFileName();
    void setProfileFileName(String profileFileName);
}
```
+ 사용자를 등록/수정/삭제/조회 시에 UserReposity 사용
  + UserReposity 구현은 메모리 기반 자료 구조 (List, Map 등) 사용
``` java
public interface UserRepository {
  void add(User user);
  void modify(User user);
  User remove(String id);

      User getUser(String id);
      List<User> getUsers();
  }
```
+ UserReposity 구현을 ServletContext 초기화 시점에 등록해서 사용
## 게시물
+ 게시물 속성
  + 게시물 아이디 (long type 숫자 값)
  + 제목
  + 본문 (plain text)
  + 작성자 아이디
  + 작성 시각 (기본값은 현재 시각)
  + 조회 수
+ 게시물 interface
``` java
public interface Post {
long getId(); // 게시물을 등록하면 id 값을 반환
void setId(long id);

    String getTitle();
    void setTitle(String title);

    String getContent();
    void setContent(String content);

    String getWriterUserId();
    void setWriterUserId(String writerUserId);

    LocalDateTime getWriteTime();
    void setWriteTime(LocalDateTime writeTime);

    int getViewCount();
    void increaseViewCount();
}
```
+ 게시물을 등록/수정/삭제/조회 시에 PostRepository 사용
``` java
public interface PostRepository {
long register(Post post);
void modify(Post post);
Post remove(long id);

    Post getPost(long id);
    List<Post> getPosts();
}
```
+ 게시물 등록 예제
``` java
Post post = new ConcretePost();
post.setTitle("제목");
post.setContent("내용");
post.setWriterUserId("dongmyo");
// NOTE: 등록 시 `setWriteTime()`을 호출하지 않으면 현재 시각을 기본값으로 설정

// NOTE: 게시물을 등록하면 repository가 id 값을 반환해야 한다.
long postId = postRepository.register(post);
post.setId(postId);
```
+ PostReposity 구현을 ServletContext 초기화 시점에 등록해서 사용
  + PostReposity 구현은 메모리 기반 자료 구조 (List, Map 등) 사용
## 화면 구성
+ 로그인하지 않았으면 로그인 화면으로 이동
+ 로그인한 사용자가 관리자면
  + 사용자 목록
  + 사용자 조회
  + 사용자 정보 수정
  + 사용자 삭제
  + 사용자 추가
    + 프로파일 업로드 ```POST /profile```
    + Servlet API 3.0 spec 기준으로 구현
    + ```commons-fileupload``` 라이브러리 사용 금지
+ 관리자가 아니면
  + 게시물 목록
  + 게시물 조회
+ 본인이 작성한 게시물이면
  + 게시물 수정
  + 게시물 삭제
  + 작성자 이름과 작성자 프로파일 표시
  + ```<img src='/profile?id='>```
  + 작성자 이름을 클릭하면 사용자 조회 화면으로 이동
  + 게시물 등록
+ 로그아웃
+ 현재 방문자수, 로그인한 사용자 수 표시
+ 현재 언어 표시
+ 언어 변경
## 테스트 코드
  + 테스트 코드 작성
  + SonarQube 
# Step.2 - JSON 파일을 이용하여 Repository 구현 + Pagination
  ## JSON 파일을 이용하여 Repository 구현
  + UserRepository
  + PostRepository
  ### Ex.) JSON 파일
  + users.json
  ``` json
  [
      {
          "id" : "admin",
          "password" : "12345",
          "name" : "Administrator",
          "profileFileName" : null
      }
  ]
  ```
  ## JSON 라이브러리
  + jackson 라이브러리 사용 (Spring Core 과제 시 사용했던 라이브러리)
  ## 게시물 Pagination 처리
  + Pagination (Paging)
    + 전체 목록을 페이지 단위로 끊어서 보여주는 것
    + page: 현재 페이지 번호 (1부터 시작)
    + size: 한 페이지에 보여줄 게시물 갯수 (10개)
  + Ex.)
    + 총 게시물 수: 13개 (1, 2, 3, ..., 13)
    + 총 페이지 수: 2 페이지
    + 1 페이지: (1, 2, 3, ..., 10)
    + 2 페이지: (11, 12, 13)
  + PostRepository에 Pagination을 위한 메서드 추가
  ``` java
  public interface PostRepository {
  // ...

      int getTotalCount();
      Page<Post> getPagedPosts(int page, int size);
  }
  ```
```java
  public interface Page<T> {
  int getPageNumber();            // 현재 페이지 번호
  int getPageSize();              // 한 페이지에 보여줄 게시물 갯수
  int getTotalPageCount();        // 총 페이지 수

      long getTotalCount();           // 총 게시물 수
      List<T> getList();              // 게시물 목록
  }
  ```
  + spring-data같은 Pagination을 지원하는 다른 라이브러리 사용 금지
  ### Step.3 - Spring framework을 이용하여 bean 으로 구현
  ## 요구사항
  + ```spring-context``` 라이브러리만 사용
    + ```spring-context``` 라이브러리만 의존 라이브러리에 추가해도  의존성 전이를 통해 ```spring-core```, ```spring-beans``` 등의 라이브러리도 사용 가능해짐
    + 그 이상의 Spring framework 관련 라이브러리는 명시적으로 ```pom.xml```에 추가해서 사용하지 말라는 뜻
  + Java Config (```@Configuration```) 으로 bean 등록
    + stereo type bean은 ```@Component``` 만 사용 가능
  + ```ServletContainerInitializer``` interface 를 구현한 클래스의 ```onStartup()``` 메서드에서
    + ```ApplicationContext.getBean()```를 통해 Spring bean 을 찾아서
  + ```ServletContext.setAttribute()``` 를 통해 ServletContext에 등록할 수 있음
    + Servlet이나 Controller에서 사용 가능
  ## 주의할 점
    
+ Spring bean으로 등록하려면 클래스에 멤버 변수가 없어야 한다
  + 왜냐하면 Spring bean은 Singleton 객체이기 때문에 상태 공유 X
  + 그럼 ```UserRepository나``` ```PostRepository는```?
+ 메모리 기반 Repository의 경우
  + synchronized collection (```Collections.synchronizedList()``` 등) 나
  + concurrent collection (```ConcurrentHashMap``` 등) 사용
+ 파일 기반 Repository의 경우
+ ```ObjectMapper```는 thread safe
+ file operation은 file에 대한 ```synchronized``` 처리 필요
+ 단, 이번 과제에서는 이 부분을 고려하지 않아도 무방함
