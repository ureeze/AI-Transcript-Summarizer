# Coding Rules

## 기본 원칙

- 최소 변경 원칙을 지킨다.
- 불필요한 리팩토링을 하지 않는다.
- 기존 아키텍처와 코드 스타일을 존중한다.
- 근거 없는 추측 구현을 하지 않는다.
- 단순하고 유지보수 가능한 코드를 우선한다.

## 폴더 구조 규칙

- 프론트엔드 코드는 `frontend/`에 둔다.
- 백엔드 코드는 `backend/`에 둔다.
- PRD는 `docs/prd.md`에 둔다.
- Memory Bank는 `memory-bank/`의 8개 문서로 관리한다.

## 테스트 규칙

- 백엔드 변경 후 가능한 경우 `backend/.\gradlew.bat test`를 실행한다.
- 프론트엔드 변경 후 가능한 경우 `frontend/npm run build`를 실행한다.
- 테스트 또는 빌드가 실패하면 원인을 기록하고 수정 후 다시 검증한다.

## Git 정책

- `main` 브랜치는 배포 브랜치로 사용한다.
- `develop` 브랜치는 기본 개발 브랜치로 사용한다.
- 새로운 작업은 `develop`에서 `feature/*` 브랜치를 생성해 진행한다.
- `feature/*` 브랜치는 `develop`으로 Pull Request를 생성한다.
- `develop` 검증 후 `main`으로 병합한다.
- 커밋 메시지는 한국어로 작성한다.
- 커밋 전 `git status`로 변경 파일을 확인한다.
- 커밋에는 현재 작업과 관련된 변경만 포함한다.
- 불필요한 빌드 산출물, 임시 파일, 로컬 환경 파일은 커밋하지 않는다.

## Git Commit Message Convention

커밋 메시지는 Conventional Commits 형식을 사용한다.

기본 형식:

```text
타입: 변경 내용
```

예시:

```text
feat: 회원가입 API 추가
fix: 로그인 오류 수정
refactor: UserService 리팩토링
```

### Commit Type 가이드

#### feat

새로운 기능 추가

예시:

```text
feat: 회원가입 API 추가
feat: JWT 인증 기능 구현
feat: 영화 예매 기능 추가
```

사용 기준:

- 새로운 API 추가
- 새로운 기능 구현
- 새로운 화면 추가
- 새로운 서비스 추가

#### fix

버그 수정

예시:

```text
fix: 로그인 시 NullPointerException 수정
fix: 좌석 중복 예약 오류 수정
fix: 비밀번호 검증 로직 수정
```

사용 기준:

- 운영 버그 수정
- 기능 오류 수정
- 예외 처리 누락 수정

#### refactor

기능 변화 없이 코드 구조 개선

예시:

```text
refactor: UserService 책임 분리
refactor: Reservation 로직 메서드 추출
refactor: 중복 코드 제거
```

사용 기준:

- 기능 추가 아님
- 버그 수정 아님
- 구조 개선

예시:

```java
if (user != null) {
    ...
}
```

```java
validateUser(user);
```

#### test

테스트 코드 추가 및 수정

예시:

```text
test: UserService 단위 테스트 추가
test: ReservationService 테스트 보완
test: 통합 테스트 추가
```

사용 기준:

- JUnit 테스트 추가
- MockMvc 테스트 추가
- Testcontainers 테스트 추가

#### docs

문서 변경

예시:

```text
docs: README 업데이트
docs: API 명세 추가
docs: ERD 문서 수정
```

사용 기준:

- README 수정
- API 문서 수정
- 설계 문서 수정

#### chore

설정 및 기타 작업

예시:

```text
chore: application.yml 정리
chore: gradle 버전 업데이트
chore: .gitignore 수정
```

사용 기준:

- 환경설정 변경
- 설정 파일 수정
- 라이브러리 버전 변경

#### style

코드 스타일 변경

예시:

```text
style: 코드 포맷팅 적용
style: import 정렬
style: 공백 정리
```

사용 기준:

- IntelliJ 자동 포맷
- import 정리
- 들여쓰기 수정

주의:

- 기능 변경 아님
- 로직 변경 아님

#### build

빌드 관련 변경

예시:

```text
build: Gradle 의존성 추가
build: Spring AI 라이브러리 추가
build: QueryDSL 설정 추가
```

사용 기준:

- Gradle 설정 변경
- Maven 설정 변경
- 라이브러리 추가/삭제

#### ci

CI/CD 관련 변경

예시:

```text
ci: GitHub Actions 배포 추가
ci: Jenkins Pipeline 수정
ci: Docker Build Workflow 추가
```

사용 기준:

- GitHub Actions
- Jenkins
- GitLab CI
- 배포 스크립트

## 코드 리뷰 규칙

- 커밋 전 현재 변경사항을 `git diff` 기준으로 리뷰한다.
- 검토 항목:
  - 버그 가능성
  - 예외 처리 누락
  - 보안 문제
  - 테스트 누락
  - 성능 문제
  - 불필요한 코드
  - 기존 아키텍처 위반 여부
  - 문서 업데이트 필요 여부
- 심각한 문제가 발견되면 수정 후 테스트를 다시 수행한다.

## 보안 규칙

- OpenAI API Key는 백엔드 환경변수로만 관리한다.
- 프론트엔드에 API Key를 노출하지 않는다.
- `.env`, 로컬 환경 파일, 민감 정보는 커밋하지 않는다.
- 유튜브 영상 URL과 추출 자막은 DB에 저장하지 않는다.
