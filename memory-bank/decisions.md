# Decisions

## 2026-06-05

### 결정

프로젝트 문서와 장기 컨텍스트를 Memory Bank로 관리한다.

### 이유

세션이 변경되어도 프로젝트 상태, 의사결정, 다음 작업을 복구할 수 있어야 한다.

### 고려한 대안

- 채팅 기록에만 의존한다.
- 코드와 README만 관리한다.
- 별도 Memory Bank 문서를 둔다.

### 영향 범위

- 모든 작업은 PRD와 Memory Bank를 읽은 뒤 진행한다.
- 작업 종료 시 Memory Bank를 갱신한다.

## 2026-06-05

### 결정

백엔드는 Gradle 기반 Spring Boot 프로젝트로 구성한다.

### 이유

사용자가 Gradle 사용을 원했고, Spring Initializr 스타일의 Gradle Wrapper 포함 구조가 로컬 검증과 배포에 적합하다.

### 고려한 대안

- Maven 유지
- 수동 Gradle 설정
- Spring Initializr 기반 Gradle 프로젝트 사용

### 영향 범위

- 백엔드 검증은 `.\gradlew.bat test`를 기준으로 수행한다.
- Gradle Wrapper를 프로젝트에 포함한다.

## 2026-06-05

### 결정

MVP 입력을 Transcript 직접 입력에서 유튜브 영상 URL 입력으로 변경한다.

### 이유

사용자가 서비스 목표를 "유튜브 영상을 입력하면 AI가 분석해 요약"하는 방식으로 변경했다.

### 고려한 대안

- Transcript 직접 입력 유지
- Transcript와 URL 입력 모두 지원
- 유튜브 영상 URL 입력만 지원

### 영향 범위

- API 요청은 `youtubeUrl`을 받는다.
- 프론트엔드는 URL 입력 UI를 제공한다.
- 백엔드는 YouTube 자막 추출 흐름을 포함한다.

## 2026-06-06

### 결정

YouTube 자막 추출은 MVP v1에서 비공식 공개 자막 추출 방식을 사용한다.

### 이유

공식 YouTube Captions API는 OAuth와 영상 소유권 권한 제약이 있어 현재 MVP 목표와 맞지 않는다. 빠른 MVP 검증을 위해 공개 자막 또는 자동 자막이 있는 영상만 지원한다.

### 고려한 대안

- 공식 YouTube Captions API 사용
- 비공식 공개 자막 추출 방식 사용
- 외부 Transcript API/SaaS 사용

### 영향 범위

- YouTube 응답 구조 변경에 영향을 받을 수 있다.
- 자막을 가져올 수 없는 경우 사용자 메시지를 반환한다.

## 2026-06-06

### 결정

OpenAI 연동은 Chat Completions API와 `gpt-4o-mini`를 사용한다.

### 이유

PRD가 `gpt-4o-mini`와 JSON 응답 포맷을 요구하며, MVP에서는 JDK `HttpClient`로 직접 호출하는 방식이 단순하다.

### 고려한 대안

- Responses API 사용
- Chat Completions API 사용
- OpenAI Java SDK 사용
- JDK `HttpClient` 직접 호출

### 영향 범위

- 백엔드는 `OPENAI_API_KEY` 환경변수가 필요하다.
- OpenAI 응답 JSON을 `summary`, `keyPoints`, `keywords`로 파싱한다.

## 2026-06-06

### 결정

브랜치 전략은 `feature/* -> develop -> main` 흐름으로 운영한다.

### 이유

`main`을 배포 브랜치로 안정적으로 유지하고, 개발 작업은 feature 브랜치와 develop에서 검증하기 위해서다.

### 고려한 대안

- `main`에서 직접 작업
- `develop`만 사용
- `feature/*`, `develop`, `main`을 분리

### 영향 범위

- 새 작업은 `develop`에서 `feature/*` 브랜치를 생성해 진행한다.
- feature 브랜치는 develop으로 PR을 생성한다.
- develop 검증 후 main으로 병합한다.

## 2026-06-07

### 결정

Memory Bank 구조를 역할별 8개 문서로 개편한다.

### 이유

기존 `active-context`, `progress`, `implementation-plan`, `task-log` 구조는 역할이 일부 겹치고, 작업 계획과 현재 상태를 분리해 보기 어렵다. 새 구조는 프로젝트 개요, 기술 설계, 기술 스택, 개발 규칙, 현재 상태, 의사결정, 작업 관리, 문제 해결을 명확히 분리한다.

### 고려한 대안

- 기존 Memory Bank 구조 유지
- 기존 문서명만 일부 변경
- 역할별 8개 문서로 전면 개편

### 영향 범위

- PRD 경로는 `docs/prd.md`로 변경된다.
- 시작 문서는 `docs/prd.md`, `project-brief.md`, `current-state.md`, `tasks.md`가 된다.
- 작업 상태는 `current-state.md`, 작업 계획은 `tasks.md`, 의사결정은 `decisions.md`에 기록한다.

## 2026-06-08

### 상태

채택 (Approved)

### 결정

`decisions.md`의 의사결정 기록 형식에 `상태` 항목을 추가한다.

### 이유

프로젝트 진행 중 결정이 폐기, 대체, 보류될 수 있으므로 기존 기록을 삭제하지 않고 현재 유효성을 표시할 방법이 필요하다.

### 고려한 대안

- 기존 기록 형식을 유지한다.
- 변경된 결정을 삭제하거나 새 기록으로만 남긴다.
- 각 결정에 상태 값을 추가해 이력을 유지한다.

### 영향 범위

- 앞으로 `decisions.md`에 새 의사결정을 기록할 때 `상태` 항목을 포함한다.
- 결정이 변경되거나 무효화되면 기존 기록을 삭제하지 않고 상태를 `폐기`, `대체됨`, `보류` 중 하나로 변경한다.
