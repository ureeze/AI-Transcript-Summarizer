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

### 브랜치 정책

- `main` 브랜치는 배포 브랜치로 사용한다.
- `develop` 브랜치는 기본 개발 브랜치로 사용한다.
- 새로운 작업은 최신 `develop`에서 `feature/*` 브랜치를 생성해 진행한다.
- Jira 이슈가 있는 작업은 가능하면 Jira 이슈를 작업의 공식 시작점으로 사용한다.
- Jira 이슈가 있는 작업은 브랜치명에 Jira 이슈 키를 포함한다.
- Jira-GitHub 연동을 확인하려는 작업은 가능한 경우 Jira 이슈 화면의 브랜치 생성 기능을 사용한다.
- `feature/*` 브랜치는 `develop`으로 Pull Request를 생성한다.
- `develop` 검증 후 `main`으로 병합한다.
- `develop`과 `main`에는 직접 커밋하지 않는다.

브랜치 예시:

```text
feature/ATS-2-openai-api-e2e-integration-test
feature/ATS-3-improve-frontend-error-messages
feature/ATS-4-configure-aws-docker-compose-deployment
```

### PR 정책

- PR은 의미 있는 작업 단위로 생성한다.
- 관련 코드 변경, 테스트, 문서 업데이트는 같은 PR에 포함한다.
- 너무 작은 변경은 진행 중인 같은 목적의 feature 브랜치 또는 PR에 포함한다.
- Jira 이슈가 있는 작업은 PR 제목에 Jira 이슈 키를 포함한다.
- Jira 이슈가 있는 작업은 PR 본문에도 Jira 이슈와 현재 작업 범위를 드러낸다.
- PR 생성 전 테스트/빌드 또는 문서 변경 리뷰를 완료한다.
- PR 병합 사실만 기록하기 위한 별도 커밋은 기본적으로 만들지 않는다.
- PR 병합 사실은 GitHub PR 기록과 merge commit을 기준으로 추적한다.
- PR 병합 후 로컬 `develop`을 `origin/develop`과 fast-forward 동기화한다.
- PR 병합 후 Memory Bank에 병합 완료 상태를 기록해야 하는 경우 다음 작업 브랜치 또는 별도 문서 브랜치에서 처리한다.
- 사용자가 명시적으로 요청하지 않는 한 PR 병합 상태 기록만을 위해 `develop`에 직접 커밋하지 않는다.

### 커밋 전 확인

- 커밋 메시지는 한국어로 작성한다.
- Jira 이슈가 있는 작업은 커밋 메시지 변경 내용에 Jira 이슈 키를 포함한다.
- 커밋 전 `git status`와 `git diff`로 변경 파일을 확인한다.
- 커밋에는 현재 작업과 관련된 변경만 포함한다.
- 불필요한 빌드 산출물, 임시 파일, 로컬 환경 파일은 커밋하지 않는다.

커밋 예시:

```text
test: ATS-2 OpenAI API 전체 흐름 검증
fix: ATS-3 프론트엔드 에러 메시지 개선
chore: ATS-4 AWS Docker Compose 배포 설정
```

### 예외 정책

- `develop` 또는 `main` 직접 커밋이 필요한 경우 사용자 확인을 먼저 받는다.
- PR 병합 상태 기록만을 위한 `develop` 직접 커밋은 예외 사유로 사용하지 않는다.
- 예외적으로 직접 커밋한 경우에도 변경사항 리뷰, 커밋, push, 최종 상태 확인을 수행한다.
- 커밋 또는 push를 보류하는 경우 보류 사유와 현재 Git 상태를 Memory Bank와 최종 응답에 기록한다.

## Git Commit Message Convention

커밋 메시지는 Conventional Commits 형식을 사용한다.

기본 형식:

```text
type: 변경 내용
```

예시:

```text
feat: 유튜브 URL 입력 기능 추가
fix: 자막 추출 실패 처리 수정
docs: 커밋 메시지 정책 추가
```

### Type 기준

| Type | 사용 기준 | 예시 |
| --- | --- | --- |
| `feat` | API, 화면, 서비스 등 새로운 기능 추가 | `feat: 유튜브 URL 입력 기능 추가` |
| `fix` | 버그, 기능 오류, 예외 처리 누락 수정 | `fix: 자막 추출 실패 처리 수정` |
| `refactor` | 기능 변화 없는 코드 구조 개선 | `refactor: SummaryService 책임 분리` |
| `test` | 테스트 코드 추가 또는 수정 | `test: SummaryService 테스트 추가` |
| `docs` | README, API 문서, 설계 문서 등 문서 변경 | `docs: README 업데이트` |
| `chore` | 환경설정, 설정 파일, 기타 관리 작업 | `chore: .gitignore 수정` |
| `style` | 코드 포맷, import, 공백 등 스타일 변경 | `style: import 정렬` |
| `build` | 빌드 설정, 의존성 추가/삭제 | `build: Gradle 의존성 추가` |
| `ci` | CI/CD 워크플로와 배포 스크립트 변경 | `ci: GitHub Actions 배포 추가` |

### 작성 규칙

- type은 소문자로 작성한다.
- 변경 내용은 한국어로 짧고 명확하게 작성한다.
- 하나의 커밋에는 하나의 목적만 담는다.
- 문서만 변경한 경우 `docs`를 사용한다.
- 기능 변경 없이 구조만 개선한 경우 `refactor`를 사용한다.
- 코드 동작 변경 없이 포맷만 바꾼 경우 `style`을 사용한다.

## Jira 작업 추적 규칙

- Jira는 작업 상태, 우선순위, 담당자, 에픽 관계를 관리하는 운영 도구로 사용한다.
- Memory Bank는 Codex 세션 복구를 위한 프로젝트 내부 기록으로 유지한다.
- 새 기능, 배포, 운영 이슈 대응, 문서 정책 변경처럼 추적 가치가 있는 작업은 가능하면 먼저 Jira 이슈로 만든다.
- Jira 이슈가 있는 작업은 Memory Bank 작업 항목에 Jira 이슈 키를 함께 기록한다.
- Jira 이슈 키는 브랜치명, 커밋 메시지, PR 제목에 가능한 한 포함한다.
- Jira 이슈가 있는 작업은 브랜치 생성 또는 실제 착수 시 Jira 상태를 `진행 중`으로 맞춘다.
- Jira 이슈가 있는 작업은 테스트, Memory Bank 업데이트, 커밋, push, PR 정리까지 끝난 시점에 Jira 상태를 현재 결과에 맞게 갱신한다.
- 작업 중 새 후속 이슈가 생기면 Memory Bank에만 남기지 않고 Jira 이슈 생성 또는 기존 Jira 이슈 분리를 검토한다.
- 너무 큰 Jira 작업은 완료된 범위와 남은 범위를 분리해 새 Jira 이슈로 쪼갠다.
- 에픽 `ATS-1`은 MVP 배포 준비 전체 범위를 나타내고, 하위 작업은 `ATS-2`부터 `ATS-6`까지 연결한다.

PR 제목 예시:

```text
[ATS-2] OpenAI API End-to-End Integration Test
[ATS-3] Improve Frontend Error Messages
```

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
