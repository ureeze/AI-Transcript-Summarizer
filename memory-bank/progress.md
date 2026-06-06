# Progress

## 2026-06-05 18:58

### 완료한 작업

- PRD 내용을 `PRD.md`로 정리했다.
- AGENTS.md 작업 규칙을 `AGENTS.md`로 정리했다.
- Memory Bank 초기 구조를 생성했다.
- 제품 요구사항을 `memory-bank/design-document.md`에 기록했다.
- 현재 상태를 `memory-bank/active-context.md`에 기록했다.
- 기술 구현 계획을 `memory-bank/implementation-plan.md`에 기록했다.
- 초기 의사결정 이력을 `memory-bank/task-log/2026-06.md`에 기록했다.
- 문제 해결 이력 문서 `memory-bank/trouble-shooting.md`를 생성했다.

### 현재 상태

프로젝트 문서화와 Memory Bank 초기화가 완료되었다.

### 남은 작업

- 프론트엔드 프로젝트 생성
- 백엔드 프로젝트 생성
- 요약 API 구현
- 프론트엔드 입력/결과 UI 구현
- OpenAI API 연동
- 테스트 및 빌드 확인
- 배포

### 다음 작업

React + Vite + TypeScript + Tailwind 프론트엔드와 Spring Boot 백엔드의 기본 프로젝트 구조를 생성한다.

## 2026-06-05 19:20

### 완료한 작업

- AGENTS.md 규칙에 따라 PRD와 Memory Bank 문서를 읽고 현재 상태를 확인했다.
- `frontend/` React + Vite + TypeScript + Tailwind CSS 기본 구조를 생성했다.
- `backend/` Spring Boot 3 + Java 21 Maven 기본 구조를 생성했다.
- 백엔드에 `POST /api/summarize` 컨트롤러, 요청/응답 DTO, 더미 서비스 응답을 추가했다.
- 프론트엔드 의존성을 설치했다.
- 프론트엔드 빌드 실패 원인을 수정했다.
- 프론트엔드 `npm run build` 성공을 확인했다.
- `.gitignore`와 `README.md`를 추가했다.

### 현재 상태

프론트엔드와 백엔드 기본 프로젝트 구조가 생성되었고, 프론트엔드 빌드는 성공했다.

### 남은 작업

- 백엔드 Maven Wrapper 추가 또는 Maven 설치 환경에서 빌드 확인
- 프론트엔드 입력/결과 UI 구현
- 프론트엔드와 백엔드 더미 API 연결
- OpenAI API 연동
- 전체 로컬 실행 검증
- 배포

### 다음 작업

Maven Wrapper를 추가하거나 Maven 설치 후 백엔드 빌드를 확인하고, 이후 프론트엔드 UI 구현과 더미 API 연결을 진행한다.

## 2026-06-05 19:26

### 완료한 작업

- 사용자의 선호에 따라 백엔드 빌드 도구를 Maven에서 Gradle로 전환했다.
- `backend/pom.xml`을 제거했다.
- `backend/settings.gradle`과 `backend/build.gradle`을 추가했다.
- `.gitignore`, `README.md`, Memory Bank 문서를 Gradle 기준으로 갱신했다.

### 현재 상태

백엔드는 Gradle 기반 Spring Boot 프로젝트 구조로 변경되었다.

### 남은 작업

- Gradle Wrapper 추가 또는 Gradle 설치 환경에서 백엔드 빌드 확인
- 프론트엔드 입력/결과 UI 구현
- 프론트엔드와 백엔드 더미 API 연결
- OpenAI API 연동
- 전체 로컬 실행 검증
- 배포

### 다음 작업

Gradle Wrapper를 추가하거나 Gradle 설치 후 백엔드 `gradle test` 또는 `gradle bootRun`을 확인한다.

## 2026-06-05 19:35

### 완료한 작업

- Spring Initializr에서 Gradle 프로젝트 ZIP을 받아 `backend/`를 Initializr 스타일로 정리했다.
- `backend/gradlew`, `backend/gradlew.bat`, `backend/gradle/wrapper/`를 추가했다.
- PRD의 Spring Boot 3 요구사항에 맞춰 Spring Boot `3.5.14` 기반 Gradle 설정을 적용했다.
- Gradle Wrapper 설정은 Initializr가 생성한 Gradle `8.14.5`를 사용한다.
- 기존 백엔드 애플리케이션 코드와 `/api/summarize` 더미 API 구조는 유지했다.
- 백엔드 `.\gradlew.bat test` 성공을 확인했다.
- README와 Memory Bank를 최신 상태로 갱신했다.

### 현재 상태

백엔드는 Spring Initializr 기반 Gradle Wrapper 포함 구조이며, 테스트가 성공했다.

### 남은 작업

- 백엔드 더미 API 로컬 실행 확인
- 프론트엔드 입력/결과 UI 구현
- 프론트엔드와 백엔드 더미 API 연결
- OpenAI API 연동
- 전체 로컬 실행 검증
- 배포

### 다음 작업

백엔드 `.\gradlew.bat bootRun`과 프론트엔드 dev server를 실행해 더미 API 연결을 구현한다.

## 2026-06-05 19:45

### 완료한 작업

- 제품 목표를 Transcript 직접 입력에서 유튜브 영상 URL 입력 기반 요약으로 변경했다.
- `PRD.md`의 MVP 범위, 사용자 시나리오, 기능 요구사항, 화면 설계, API 설계, 성공 기준을 갱신했다.
- `memory-bank/design-document.md`에 변경된 제품 범위를 반영했다.
- `memory-bank/implementation-plan.md`에 유튜브 URL 검증과 자막 추출 흐름을 추가했다.
- `memory-bank/active-context.md`에 현재 코드와 변경된 PRD 사이의 열린 이슈를 기록했다.
- `README.md`의 프로젝트 설명을 유튜브 영상 URL 입력 기준으로 변경했다.

### 현재 상태

공식 요구사항 문서는 유튜브 영상 URL 입력 기반 요약 서비스로 변경되었다. 실제 코드는 아직 기존 Transcript 기반 요청 구조를 사용한다.

### 남은 작업

- 백엔드 요청 DTO와 더미 API를 `youtubeUrl` 기반으로 수정
- 유튜브 자막 추출 방식 결정
- 유튜브 자막 추출 구현
- 프론트엔드 URL 입력 UI 구현
- 프론트엔드와 백엔드 더미 API 연결
- OpenAI API 연동
- 전체 로컬 실행 검증
- 배포

### 다음 작업

백엔드 `SummarizeRequest`를 `youtubeUrl` 기반으로 변경하고, 프론트엔드 입력 UI도 유튜브 URL 입력 방식으로 수정한다.

## 2026-06-05 19:56

### 완료한 작업

- 백엔드 `SummarizeRequest`를 `transcript` 기반에서 `youtubeUrl` 기반으로 변경했다.
- 백엔드 유튜브 URL 검증 정규식을 추가했다.
- 백엔드 더미 응답 문구를 유튜브 URL 입력 흐름 기준으로 변경했다.
- 프론트엔드에 유튜브 URL 입력 폼, 클라이언트 검증, API 호출, 결과 표시 UI를 구현했다.
- Vite 환경변수 타입 선언 파일을 추가했다.
- 유효하지 않은 URL 거절과 정상 URL 응답 형식을 확인하는 백엔드 테스트를 추가했다.
- 백엔드 `.\gradlew.bat test` 성공을 확인했다.
- 프론트엔드 `npm run build` 성공을 확인했다.

### 현재 상태

프론트엔드와 백엔드는 `youtubeUrl` 요청 계약 기준으로 연결 가능한 상태이다. 백엔드는 아직 자막 추출과 OpenAI 연동 전 더미 응답을 반환한다.

### 남은 작업

- 유튜브 자막 추출 방식 결정
- `YouTubeTranscriptService` 구현
- OpenAI API 연동
- 전체 로컬 실행 및 브라우저 검증
- 배포

### 다음 작업

유튜브 자막 추출 방식을 결정하고, 영상 ID 추출 및 자막 조회 서비스를 구현한다.

## 2026-06-06 10:25

### 완료한 작업

- YouTube 자막 추출 방식을 비공식 공개 자막 추출 방식으로 결정했다.
- 이 결정을 `memory-bank/implementation-plan.md`와 `memory-bank/task-log/2026-06.md`에 기록했다.
- `YouTubeTranscriptService`를 추가했다.
- YouTube URL에서 video ID를 추출하는 로직을 구현했다.
- YouTube watch page에서 공개 자막 트랙을 찾고 json3 자막 텍스트를 구성하는 로직을 구현했다.
- 자막을 가져올 수 없는 경우 `TranscriptUnavailableException`을 발생시키도록 구현했다.
- 자막 추출 실패 시 `422 Unprocessable Entity`와 `이 영상의 자막을 가져올 수 없습니다.` 메시지를 반환하는 예외 핸들러를 추가했다.
- `SummaryService`가 `YouTubeTranscriptService`를 호출하도록 연결했다.
- video ID 추출, 자막 트랙 없음, 자막 텍스트 구성, API 실패 응답 테스트를 추가했다.
- 백엔드 `.\gradlew.bat test` 성공을 확인했다.
- 프론트엔드 `npm run build` 성공을 확인했다.

### 현재 상태

백엔드는 유튜브 URL에서 공개 자막을 추출한 뒤 더미 요약 응답을 반환한다. 아직 OpenAI API 연동은 구현되지 않았다.

### 남은 작업

- OpenAI API 연동
- 추출 자막을 실제 요약 프롬프트에 전달
- OpenAI JSON 응답 파싱
- OpenAI 실패 처리
- 백엔드/프론트엔드 전체 로컬 실행 검증
- 배포

### 다음 작업

`OPENAI_API_KEY` 환경변수를 사용하는 OpenAI 요약 클라이언트를 구현하고 `SummaryService`의 더미 응답을 실제 AI 요약 응답으로 교체한다.

## 2026-06-06 10:35

### 완료한 작업

- `AGENTS.md`에 작업 종료 시 Git 커밋과 GitHub push를 수행하는 규칙을 추가했다.
- 작업 절차와 작업 종료 체크리스트에 `git status`, 커밋, push 확인 항목을 추가했다.
- Git 관리 규칙, 커밋 메시지 원칙, 예외 상황, 보류 시 기록 규칙을 추가했다.
- `active-context.md`에 Git 커밋/push 운영 규칙과 현재 미커밋 상태를 기록했다.

### 현재 상태

프로젝트 운영 규칙에는 작업 완료 후 Git 커밋과 GitHub push가 포함되었다.

### 남은 작업

- 현재 미커밋 변경 사항 커밋 및 GitHub push
- OpenAI API 연동
- 추출 자막을 실제 요약 프롬프트에 전달
- 전체 로컬 실행 검증
- 배포

### 다음 작업

현재 변경 사항을 의미 있는 커밋 단위로 정리해 GitHub에 push한다.
