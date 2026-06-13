# Decisions

> 참고: 2026-06-05에 기록된 초기 의사결정은 ADR 형식 도입 전의 구형 기록이므로 기존 형식 그대로 유지한다. 신규 의사결정은 ADR 번호, 날짜, 상태, 결정, 이유, 고려한 대안, 영향 범위 형식을 따른다.

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

## ADR-008 tasks.md 작업 ID와 Done 완료일 규칙 도입

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

`tasks.md`의 모든 작업 항목은 `T-001` 형식의 고유 ID를 가지고, `Done` 항목은 완료일을 `done: YYYY-MM-DD` 형식으로 기록한다.

### 이유

작업 항목이 상태 간 이동할 때 같은 작업을 추적할 수 있어야 한다. 또한 완료된 작업의 날짜를 남기면 진행 이력과 의사결정 시점을 비교하기 쉬워지고, 이후 세션에서 프로젝트 흐름을 더 정확히 복구할 수 있다.

### 고려한 대안

- 기존 목록 형식을 유지한다.
- `Done` 항목에만 날짜를 추가한다.
- 모든 작업에 ID를 부여하고 `Done` 항목에 완료일을 추가한다.

### 영향 범위

- `tasks.md`의 모든 작업 항목은 고유 ID를 포함한다.
- 작업 ID는 상태가 바뀌어도 유지하고 다른 작업에 다시 부여하지 않는다.
- `Done` 항목은 완료일을 포함한다.
- 새 작업 ID는 `tasks.md`에 존재하는 가장 큰 ID 다음 번호를 사용한다.

## ADR-009 작업 실행 전 사용자 승인 절차 도입

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

코드 수정, 문서 수정, Git 작업, PR 작업, 배포 작업 등 실제 변경이 발생하는 요청은 실행 전에 실행계획을 제시하고 사용자 승인을 받은 뒤 진행한다.

### 이유

사용자가 작업 범위, 영향 파일, 검증 방법, Git 처리 계획을 먼저 확인하고 결정할 수 있도록 하기 위해서다. 이를 통해 의도하지 않은 변경, 불필요한 커밋, 과도한 PR 생성을 줄인다.

### 고려한 대안

- 요청 즉시 작업을 수행한다.
- 고위험 작업에만 승인 절차를 둔다.
- 모든 실제 변경 작업에 실행계획과 사용자 승인 절차를 둔다.

### 영향 범위

- 파일 수정, Git 작업, PR 생성, 배포 작업은 승인 후 수행한다.
- 단순 질문 답변, 설명, 현황 조회, 명령어 안내는 승인 절차 없이 처리할 수 있다.
- 실행계획 승인 전에는 파일을 수정하지 않는다.
- 사용자가 수정 요청을 하면 실행계획을 다시 작성해 제시한다.

## ADR-010 current-state.md 작업 ID와 완료일 규칙 도입

### 날짜

2026-06-10

### 상태

채택 (Approved)

### 결정

`current-state.md`의 작업 항목은 가능한 경우 `tasks.md`의 `T-xxx` 작업 ID를 참조하고, `최근 완료 작업` 항목은 완료일을 `done: YYYY-MM-DD` 형식으로 기록한다.

### 이유

`current-state.md`와 `tasks.md`가 서로 다른 ID 체계를 가지면 같은 작업을 추적하기 어렵다. `tasks.md`의 작업 ID를 참조하면 현재 상태 요약과 전체 작업 이력을 연결할 수 있고, 완료일을 함께 기록하면 최근 작업 흐름을 더 정확히 복구할 수 있다.

### 고려한 대안

- `current-state.md`에는 ID를 기록하지 않는다.
- `current-state.md` 전용 `CS-xxx` ID를 사용한다.
- `tasks.md`의 `T-xxx` ID를 참조한다.

### 영향 범위

- `current-state.md`의 `진행 중 작업`, `최근 완료 작업`, `다음 작업`은 가능한 경우 `tasks.md`의 작업 ID를 참조한다.
- `최근 완료 작업`은 완료일을 포함한다.
- 전체 완료 이력은 `tasks.md`가 관리하고, `current-state.md`는 최근 흐름 요약만 유지한다.
- `tasks.md`에 없는 완료 기록은 먼저 `tasks.md`에 작업 ID를 추가한 뒤 `current-state.md`에서 참조한다.

## ADR-011 Memory Bank 규칙 표현 명확화

### 날짜

2026-06-11

### 상태

채택 (Approved)

### 결정

단순 질의와 실제 변경 작업의 종료 기준을 구분하고, `current-state.md`는 `tasks.md`의 작업 ID를 새로 부여하지 않고 참조한다고 표현한다. 또한 ADR 형식 도입 이전의 구형 의사결정 기록은 `decisions.md` 상단 안내를 통해 기존 형식 그대로 유지한다고 명시한다.

### 이유

기존 문서 표현은 파일 변경이 없는 단순 질문에도 Memory Bank 업데이트가 필요한 것처럼 읽힐 수 있었다. 또한 `tasks.md`의 작업 ID 유지 규칙과 `current-state.md`의 작업 ID 참조 규칙이 같은 용어로 설명되어, 같은 ID를 새 작업에 다시 부여해도 되는 것처럼 오해될 수 있었다.

### 고려한 대안

- 기존 표현을 유지한다.
- `AGENTS.md`만 수정한다.
- `AGENTS.md`와 `decisions.md`를 함께 수정해 운영 규칙과 결정 이력을 모두 명확히 한다.

### 영향 범위

- 파일 변경이나 외부 상태 변경이 없는 단순 질의/검토는 Memory Bank 업데이트 없이 종료할 수 있다.
- `current-state.md`는 `tasks.md` 작업 ID를 새로 부여하지 않고 참조한다.
- `tasks.md` 작업 ID는 다른 작업에 다시 부여하지 않는다.
- ADR 형식 도입 전 구형 의사결정 기록은 기존 형식으로 유지한다.

## ADR-012 PR 병합 상태 기록 커밋 금지 명확화

### 날짜

2026-06-11

### 상태

채택 (Approved)

### 결정

PR 병합 사실만 기록하기 위한 `develop` 직접 커밋은 만들지 않는다. PR 병합 사실은 GitHub PR 기록과 merge commit을 기준으로 추적하고, 병합 후 Memory Bank 업데이트가 필요하면 다음 작업 브랜치 또는 별도 문서 브랜치에서 처리한다.

### 이유

`develop`은 기본 개발 브랜치이므로 직접 커밋을 최소화하고 PR 기반 이력을 유지해야 한다. PR 병합 여부는 GitHub에 이미 기록되므로, 병합 상태만 남기기 위한 별도 커밋은 이력을 불필요하게 늘리고 브랜치 정책을 흐릴 수 있다.

### 고려한 대안

- PR 병합 후 매번 `develop`에 Memory Bank 상태 기록 커밋을 만든다.
- PR 병합 기록은 GitHub에만 맡기고 Memory Bank에는 기록하지 않는다.
- PR 병합 사실은 GitHub 기록을 기준으로 삼고, 필요한 Memory Bank 업데이트는 다음 작업 브랜치 또는 별도 문서 브랜치에서 처리한다.

### 영향 범위

- PR 병합 후 로컬 `develop`은 `origin/develop`과 fast-forward 동기화만 수행한다.
- 병합 상태 기록만을 위한 `develop` 직접 커밋은 기본적으로 금지한다.
- Memory Bank 업데이트가 필요하면 feature 브랜치에서 커밋하고 PR을 생성한다.
- 사용자가 명시적으로 요청한 경우에만 예외를 검토한다.

## ADR-013 Jira 이슈 키 기반 작업 추적 도입

### 날짜

2026-06-13

### 상태

채택 (Approved)

### 결정

Jira 이슈 키를 Jira, GitHub, Codex Memory Bank를 연결하는 공통 작업 식별자로 사용한다. Jira 이슈가 있는 작업은 Memory Bank 작업 항목, 브랜치명, 커밋 메시지, PR 제목에 가능한 한 Jira 이슈 키를 포함한다.

### 이유

1인 프로젝트에서도 실무형 작업 흐름을 연습하려면 작업 관리 도구와 코드 변경 이력이 같은 작업 단위로 연결되어야 한다. Jira 이슈 키를 공통 식별자로 사용하면 Jira의 상태 관리, GitHub의 브랜치/PR 이력, Codex의 Memory Bank 기록을 같은 작업으로 추적할 수 있다.

### 고려한 대안

- Memory Bank의 `T-xxx` ID만 사용한다.
- Jira 이슈 키만 사용하고 Memory Bank 작업 ID를 제거한다.
- Memory Bank `T-xxx` ID와 Jira 이슈 키를 함께 사용한다.

### 영향 범위

- Jira 에픽 `ATS-1`은 MVP 배포 준비 전체 범위를 나타낸다.
- 남은 작업은 `ATS-2`부터 `ATS-6`까지의 Jira 이슈와 연결한다.
- Memory Bank는 `T-xxx` ID와 Jira 이슈 키를 함께 기록한다.
- Git 브랜치, 커밋 메시지, PR 제목에는 가능한 경우 Jira 이슈 키를 포함한다.
- Jira는 작업 추적의 운영 도구, Memory Bank는 Codex 컨텍스트 복구 도구로 사용한다.

## ADR-014 백엔드 도메인 기준 패키지 구조 정리

### 날짜

2026-06-13

### 상태

채택 (Approved)

### 결정

백엔드 패키지 구조를 도메인 기준 + 계층 하위 분리 방식으로 정리한다.

### 이유

기존 백엔드는 controller, service, client, dto, config, exception, YouTube 자막 추출 책임이 모두 `summary` 패키지에 모여 있었다. 기능이 작을 때는 단순하지만, 배포 준비와 오류 처리 개선이 이어지면 책임이 섞여 파일 탐색과 변경 영향 파악이 어려워질 수 있다. 도메인 기준으로 `summary`와 `youtube`를 나누고, `summary` 내부는 계층별 하위 패키지로 분리해 책임을 명확히 한다.

### 고려한 대안

- 기존 단일 `summary` 패키지 유지
- 최상위 `controller`, `service`, `dto`, `exception` 계층 패키지로만 분리
- 도메인 기준 + 계층 하위 패키지 구조 도입

### 영향 범위

- `summary/controller`
- `summary/service`
- `summary/client`
- `summary/dto`
- `summary/config`
- `summary/exception`
- `youtube/service`
- 백엔드 테스트 패키지 구조
- `architecture.md`의 Backend 모듈 구성

## ADR-015 AWS EC2 기반 Docker Compose 배포 전략 채택

### 날짜

2026-06-14

### 상태

채택 (Approved)

### 결정

MVP 운영 배포 전략을 Render/Vercel에서 AWS Cloud Free Tier 기반 EC2, Nginx, Docker Compose, GitHub Actions, GHCR 조합으로 변경한다.

### 이유

사용자는 실무에 가까운 배포 경험을 원한다. AWS EC2 기반 배포는 서버 접속, reverse proxy, 컨테이너 실행, 이미지 레지스트리, CI/CD, 환경변수와 Secret 관리까지 직접 다루므로 단순 PaaS 배포보다 운영 흐름을 더 폭넓게 연습할 수 있다. Nginx는 현업에서 널리 사용되는 reverse proxy이며, GitHub Actions와 GHCR은 GitHub 기반 CI/CD와 컨테이너 이미지 배포 흐름을 익히기에 적합하다.

### 고려한 대안

- Render 백엔드 + Vercel 프론트엔드 배포
- Oracle Cloud Free Tier + Docker Compose + GitHub Actions + GHCR
- AWS Cloud Free Tier + Nginx + Docker Compose + GitHub Actions + GHCR

### 영향 범위

- `docs/prd.md`의 배포 방식
- `memory-bank/architecture.md`의 배포 구조와 배포 데이터 흐름
- `memory-bank/tech-stack.md`의 인프라 기술 스택
- `memory-bank/tasks.md`의 ATS-4, ATS-5, ATS-6 작업 정의
- Jira `ATS-4`, `ATS-5`, `ATS-6` 배포 작업 정의

## ADR-016 Docker Compose 기반 로컬 배포 구성 도입

### 날짜

2026-06-14

### 상태

채택 (Approved)

### 결정

백엔드와 프론트엔드는 각각 Dockerfile을 가지고, `deploy/docker-compose.yml`에서 Nginx reverse proxy, frontend, backend 컨테이너를 함께 실행한다. 외부 요청은 Nginx가 받고 `/`는 frontend 컨테이너, `/api/*`는 backend 컨테이너로 전달한다.

### 이유

AWS EC2 단일 서버 배포를 준비하려면 로컬에서 먼저 서버와 유사한 컨테이너 구성을 검증할 수 있어야 한다. Compose 파일을 `deploy/`에 모으면 EC2 서버에서도 같은 구조를 재사용하기 쉽고, Nginx reverse proxy를 로컬부터 사용하면 운영 라우팅과 개발 검증 흐름의 차이를 줄일 수 있다.

### 고려한 대안

- 프론트엔드와 백엔드를 각각 호스트 포트로 직접 노출한다.
- 프론트엔드 컨테이너만 Nginx를 사용하고 reverse proxy는 생략한다.
- 별도 Nginx reverse proxy 컨테이너를 두고 frontend/backend를 내부 네트워크로만 연결한다.

### 영향 범위

- `backend/Dockerfile`
- `frontend/Dockerfile`
- `frontend/nginx.conf`
- `deploy/docker-compose.yml`
- `deploy/nginx/nginx.conf`
- `deploy/.env.example`
- `README.md`
- `memory-bank/architecture.md`
- `memory-bank/tech-stack.md`
