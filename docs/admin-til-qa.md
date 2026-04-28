# 관리자 기능 구현 Q&A 메모

## 1. 구현 순서 잡기

- 기능 정리는 문제에 적힌 항목 기준으로 나누면 된다.
- 구현 순서는 요구사항의 흐름과 선행 조건을 봐야 한다.
- 관리자 기능 흐름:
  - 회원가입을 해야 관리자 데이터가 생성된다.
  - 회원가입한 관리자는 바로 사용 가능한 것이 아니라 승인대기 상태가 된다.
  - 승인대기 상태의 관리자가 있어야 승인 / 거부가 가능하다.
  - 승인되어 활성 상태가 되어야 로그인할 수 있다.
  - 로그인한 사용자가 있어야 내 프로필 조회나 비밀번호 변경이 가능하다.
- 로그인 담당자가 따로 있으면, 내가 먼저 할 수 있는 승인 / 거부 / 조회부터 시작하면 된다.

## 2. 로그인 담당과 역할 분리

- `POST /admins/register`, `POST /admins/login`, `POST /admins/logout`가 로그인 담당 범위라면 해당 API 구현은 겹친다.
- 이런 경우 관리자 CRUD 담당은 승인 / 거부 / 조회 / 수정 / 삭제부터 구현하면 된다.
- 기능은 다르지만 같은 파일을 건드릴 수 있으니 팀원과 범위를 꼭 맞춰야 한다.

## 3. DTO와 record

- `record`는 DTO를 짧게 만들기 위한 자바 문법이다.
- 하지만 지금은 일반 클래스로 DTO를 만드는 쪽이 구조를 이해하기 더 쉽다.
- DTO를 일반 클래스로 만들 때는:
  - 필드
  - 생성자
  - getter
  를 직접 보면서 구조를 익히기 좋다.

## 4. DTO 작명 규칙

- 클래스 작명 규칙:
  - 도메인 + 액션 + 요청/응답 + Dto
- 예시:
  - `AdminRejectRequestDto`
  - `AdminApproveResponseDto`
  - `AdminRejectResponseDto`
  - `AdminDetailResponseDto`
  - `AdminUpdateRequestDto`

## 5. 메서드명 규칙

- 메서드명은 동사로 시작한다.
- 메서드명만 보고 기능을 알 수 있게 작성한다.
- Controller와 Service 메서드 이름을 동일하게 작성한다.
- 예시:
  - `approveAdmin`
  - `rejectAdmin`
  - `getAdmin`
  - `getAdmins`

## 6. 주석 스타일

- 존댓말보다 짧고 단정하게 쓰는 게 보기 좋다.
- 메서드 설명 주석은 어노테이션 위에 두는 게 읽기 좋다.
- 예시:
  - `// 관리자 승인 API`
  - `// 승인 대기 상태 검증`
  - `// 엔티티를 관리자 목록 응답 DTO로 변환`

## 7. 승인 / 거부 구현 포인트

- 승인 / 거부는 `PENDING` 상태인 관리자만 처리해야 한다.
- 이를 위해 상태 검증 메서드가 필요하다.
- 예:
  - `validatePending(Admin admin)`
- 이 검증이 없으면 이미 승인된 관리자나 거부된 관리자를 다시 처리할 수 있어 상태 흐름이 꼬인다.

## 8. 단건 조회

- 단건 조회는 보통 RequestDto가 필요 없다.
- `GET /admins/{id}`는 `PathVariable`로 `id`만 받는다.
- ResponseDto만 있으면 된다.
- 추천 이름:
  - `AdminDetailResponseDto`
- 단건 조회 서비스 흐름:
  - `id`로 관리자 조회
  - 없으면 `AdminNotFoundException`
  - 있으면 `AdminDetailResponseDto.from(admin)` 반환

## 9. 다건 조회 기본 구조

- 다건 조회는 보통 두 부분으로 나뉜다.
  - 관리자 목록 데이터
  - 페이지 정보
- 다건 조회 응답 필드:
  - `currentPage`
  - `pageSize`
  - `totalCount`
  - `totalPages`
  - `adminList`
- 목록 한 줄 DTO:
  - `AdminListResponseDto`
- 최종 페이지 응답 DTO:
  - `AdminPageResponseDto`

## 10. 내부 DTO / 외부 DTO 개념

- `AdminListResponseDto`
  - 관리자 한 명 정보
  - 내부 DTO 느낌
- `AdminPageResponseDto`
  - 관리자 목록 전체 + 페이지 정보
  - 외부 DTO 느낌

## 11. Pageable 이해

- `Pageable`은 페이지 / 사이즈 / 정렬 정보를 담는 객체다.
- `@PageableDefault`는 기본값을 정해준다.
- 스프링의 페이지 번호는 0부터 시작한다.
- 그래서 내부적으로는 `page = 0`을 쓰고, 응답에서는 `page.getNumber() + 1`로 보여주면 된다.
- `Page` 객체가 제공하는 것:
  - `getContent()`
  - `getNumber()`
  - `getSize()`
  - `getNumberOfElements()`
  - `getTotalPages()`
  - `getTotalElements()`

## 12. stream 사용 위치

- `stream()`은 리스트를 DTO 리스트로 변환할 때 자주 쓴다.
- 예:
  - `adminPage.getContent().stream().map(AdminListResponseDto::from).toList()`
- DTO 안에서 단순 변환용으로 써도 가능하지만,
- 지금은 DTO는 단순하게 두고 service나 controller에서 `stream()`을 쓰는 쪽이 이해하기 쉽다.

## 13. 다건 조회 검색 / 필터 / 페이징

- 요구사항:
  - 검색 키워드 (이름, 이메일)
  - 페이지 번호
  - 페이지당 개수
  - 정렬 기준
  - 정렬 순서
  - 역할 필터
  - 상태 필터
- `@RequestParam(required = false)`를 쓰는 이유:
  - 검색 / 역할 / 상태 필터는 선택 조건이기 때문
- `Pageable`은 page / size / sort 용
- `RequestParam`은 keyword / role / status 용

## 14. @Query 이해

- `@Query`는 repository에서 조회 조건을 직접 쓰는 어노테이션이다.
- `@Component`와는 역할이 완전히 다르다.
- `@Component`는 스프링 빈 등록용
- `@Query`는 DB 조회문 작성용
- 검색 / 역할 / 상태 조건이 함께 있을 때 메서드 이름만으로 처리하기 어려워 `@Query`를 사용한다.

## 15. 더미 데이터와 data.sql

- 더미 데이터는 controller에 넣는 게 아니라 DB나 SQL 파일로 넣는 게 맞다.
- 팀원이 `resources` 아래 SQL 파일로 넣는 방식이면 같은 방식으로 맞추는 게 좋다.
- `src/main/resources/data.sql`로 테스트 데이터를 넣을 수 있다.
- 실행 시 자동 반영되게 하려면 설정:
  - `spring.jpa.defer-datasource-initialization=true`
  - `spring.sql.init.mode=always`

## 16. Postman 테스트

- 단건 조회:
  - `GET /admins/{id}`
  - Body 없음
- 승인:
  - `POST /admins/{id}/approve`
- 거부:
  - `POST /admins/{id}/reject`
  - body에 `rejectionReason` 필요
- 서버가 안 떠 있으면 `ECONNREFUSED`가 뜬다.
- 포트는 `application.properties` 기준으로 확인해야 한다.

## 17. 예외 처리

- 단건 조회는 없는 ID에 대해 예외가 필요하다.
- 다건 조회는 조회 결과가 없다고 예외를 던지지 않고 빈 리스트를 반환하는 것이 일반적이다.
- 다건 조회에서 예외가 필요할 수 있는 경우:
  - 잘못된 enum 값
  - 잘못된 페이지 값
  - 잘못된 정렬 값

## 18. 배운 점 요약

- 기능 정리 기준과 구현 순서 기준은 다르다.
- DTO는 엔티티를 그대로 내보내지 않고 필요한 값만 담는 역할이다.
- 페이지 응답은 목록 데이터와 페이지 정보를 함께 반환해야 한다.
- 팀플에서는 더 구조적인 방식보다 팀 전체 통일감이 더 중요할 때가 있다.
- 하지만 요구사항을 구조적으로 보면, 목록 DTO와 페이지 DTO를 분리하는 방식이 더 자연스럽다.
