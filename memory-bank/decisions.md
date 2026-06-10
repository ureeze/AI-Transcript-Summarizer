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

## ADR-001 Memory Bank 구조 개편

### 날짜

2026-06-07

### 상태

채택 (Approved)

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

## ADR-002 decisions.md ADR 기록 형식 도입

### 날짜

2026-06-08

### 상태

채택 (Approved)

### 결정

`decisions.md`의 의사결정 기록 형식을 ADR 번호, 날짜, 상태, 결정, 이유, 고려한 대안, 영향 범위 중심으로 관리한다.

### 이유

프로젝트 진행 중 결정이 폐기, 대체, 보류될 수 있으므로 기존 기록을 삭제하지 않고 현재 유효성과 변경 맥락을 표시할 방법이 필요하다. 또한 제목을 날짜가 아니라 결정 내용으로 관리하면 후속 유지보수 시 의사결정을 더 쉽게 찾을 수 있다.

### 고려한 대안

- 기존 기록 형식을 유지한다.
- 상태 항목만 추가한다.
- ADR 번호와 상태 값을 함께 도입한다.

### 영향 범위

- 앞으로 `decisions.md`에 새 의사결정을 기록할 때 ADR 번호와 상태 항목을 포함한다.
- 상태 값은 `채택 (Approved)`, `폐기 (Deprecated)`, `대체됨 (Superseded)`, `보류 (Deferred)` 중 하나를 사용한다.
- 결정이 변경되거나 무효화되면 기존 ADR을 삭제하지 않고 상태를 변경한다.
- 후속 작업은 `current-state.md` 또는 `tasks.md`에서 관리한다.

## ADR-003 Memory Bank 문서 책임 분리

### 날짜

2026-06-09

### 상태

채택 (Approved)

### 결정

`project-brief.md`, `architecture.md`, `tech-stack.md`의 책임을 제품 개요, 시스템 구조, 기술 목록으로 분리한다.

### 이유

세 문서가 모두 기술명, 기능 흐름, 프로젝트 범위를 함께 설명하면서 내용이 중복되었다. 문서별 책임을 분리하면 같은 정보를 여러 곳에서 수정해야 하는 부담이 줄고, 다음 세션에서 필요한 정보를 더 빠르게 찾을 수 있다.

### 고려한 대안

- 기존 내용을 유지한다.
- 세 문서를 하나로 합친다.
- 문서는 유지하되 각 문서의 책임을 더 명확히 나눈다.

### 영향 범위

- `project-brief.md`는 제품 목적, 사용자, 기능 범위 중심으로 유지한다.
- `architecture.md`는 컴포넌트 책임, 데이터 흐름, API 구조 중심으로 유지한다.
- `tech-stack.md`는 언어, 프레임워크, 라이브러리, 인프라, 버전 중심으로 유지한다.

## ADR-004 troubleshooting.md 날짜와 상태 기록 형식 도입

### 날짜

2026-06-09

### 상태

채택 (Approved)

### 결정

`troubleshooting.md`의 문제 해결 기록 형식에 날짜와 상태 항목을 추가한다.

### 이유

문제 해결 이력이 누적되면 같은 문제가 언제 발생했고 현재 해결되었는지 빠르게 파악해야 한다. 날짜와 상태를 기록하면 재발 여부와 해결 상태를 추적하기 쉽다.

### 고려한 대안

- 기존 `문제`, `원인`, `해결`, `재발 방지` 형식을 유지한다.
- 날짜만 추가한다.
- 날짜와 상태를 함께 추가한다.

### 영향 범위

- 새 문제 해결 기록은 `YYYY-MM-DD - 문제 제목` 형식의 제목을 사용한다.
- 각 문제 해결 기록은 `상태` 항목을 포함한다.
- 상태 값은 `해결`, `미해결`, `관찰 중` 중 하나를 사용한다.

## ADR-005 Conventional Commits 기반 커밋 메시지 정책 도입

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

커밋 메시지는 Conventional Commits 형식인 `타입: 변경 내용`을 사용한다.

### 이유

커밋 이력에서 변경 목적을 빠르게 파악하고, 기능 추가, 버그 수정, 문서 변경, 빌드 변경 등을 일관된 기준으로 구분하기 위해서다.

### 고려한 대안

- 자유 형식 커밋 메시지를 유지한다.
- 한국어 문장형 커밋 메시지만 사용한다.
- Conventional Commits 형식에 한국어 변경 내용을 결합한다.

### 영향 범위

- 커밋 메시지는 `feat`, `fix`, `refactor`, `test`, `docs`, `chore`, `style`, `build`, `ci` 타입 중 하나를 우선 사용한다.
- 변경 내용은 한국어로 짧고 명확하게 작성한다.
- 세부 사용 기준은 `memory-bank/coding-rules.md`에서 관리한다.

## ADR-006 Memory Bank 상태 작성 규칙 보강

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

`current-state.md`와 `tasks.md`의 상태별 작성 규칙을 `AGENTS.md`에 명확히 기록한다.

### 이유

완료된 작업이 `진행 중 작업` 또는 `In Progress`에 남으면 다음 작업자가 현재 실제로 착수 중인 작업을 오해할 수 있다. 완료, 진행, 다음 작업의 위치를 명확히 구분해 Memory Bank의 현재 상태 복구 정확도를 높이기 위해서다.

### 고려한 대안

- 기존 규칙을 유지한다.
- `current-state.md` 작성 규칙만 보강한다.
- `current-state.md`와 `tasks.md` 작성 규칙을 함께 보강한다.

### 영향 범위

- `current-state.md`의 `진행 중 작업`에는 현재 착수 중이고 완료되지 않은 작업만 기록한다.
- 완료된 작업은 `최근 완료 작업` 또는 `Done`에 기록한다.
- 아직 착수하지 않은 작업은 `다음 작업` 또는 `Next`에 기록한다.
- 진행 중인 작업이 없으면 `없음`이라고 기록한다.

## ADR-007 실무형 Git/PR 운영 흐름 도입

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

Git/PR 작업은 `feature/*` 브랜치에서 수행하고 Pull Request를 통해 `develop`에 병합하는 흐름을 기본으로 한다. 상세 정책은 `memory-bank/coding-rules.md`에서 관리하고, `AGENTS.md`는 해당 정책을 따르도록 짧은 작업 원칙만 기록한다.

### 이유

실무에 가까운 협업 흐름을 연습하고, `develop` 직접 커밋과 PR 병합 기록용 커밋을 줄여 브랜치 이력을 명확하게 유지하기 위해서다. 또한 AGENTS와 coding-rules의 중복을 줄이고, 상세 Git 정책의 단일 관리 위치를 명확히 하기 위해서다.

### 고려한 대안

- `develop`에 직접 커밋한다.
- 모든 작은 변경마다 별도 PR을 만든다.
- 의미 있는 작업 단위로 feature 브랜치와 PR을 사용한다.

### 영향 범위

- 새 작업은 최신 `develop`에서 `feature/*` 브랜치를 생성해 진행한다.
- PR은 의미 있는 작업 단위로 생성한다.
- `develop`과 `main` 직접 커밋은 기본적으로 금지한다.
- PR 병합 사실만 기록하기 위한 별도 커밋은 기본적으로 만들지 않는다.
- Git 상세 정책은 `memory-bank/coding-rules.md`에서 관리한다.
