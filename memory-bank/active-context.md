# Active Context

## 현재 마일스톤

MVP v1 OpenAI 요약 연동 구현

## 현재 작업

`AGENTS.md`의 Git 관리 규칙을 브랜치 정책 중심으로 변경했다.

## 최근 결정 사항

- 프로젝트 공식 요구사항 문서는 `PRD.md`로 관리한다.
- 장기 컨텍스트는 `memory-bank/` 하위 문서로 관리한다.
- 의사결정 이력은 현재 월 기준 `memory-bank/task-log/2026-06.md`에 누적한다.
- MVP는 DB 없이 React + Vite 프론트엔드와 Spring Boot 백엔드로 분리해 구현한다.
- 백엔드는 Spring Initializr 기반 Gradle 프로젝트로 구성한다.
- Spring Boot 버전은 PRD의 Spring Boot 3 요구사항에 맞춰 `3.5.14`를 사용한다.
- Gradle Wrapper는 Initializr가 생성한 Gradle `8.14.5` 설정을 사용한다.
- MVP 입력은 Transcript 텍스트가 아니라 유튜브 영상 URL이다.
- 기존에 제외했던 유튜브 URL 분석과 자동 자막 추출은 MVP 포함 기능으로 변경했다.
- 백엔드 `/api/summarize`는 `youtubeUrl` 요청을 받아 자막 추출 후 요약하는 흐름으로 변경되어야 한다.
- 현재 `/api/summarize`는 `youtubeUrl`을 검증하고 더미 요약 응답을 반환한다.
- 프론트엔드는 유튜브 URL 입력, 클라이언트 검증, API 호출, 결과 표시를 구현했다.
- YouTube 자막 추출 방식은 비공식 공개 자막 추출 방식으로 결정했다.
- `YouTubeTranscriptService`는 video ID 추출, 공개 자막 트랙 조회, json3 자막 텍스트 구성을 담당한다.
- 자막을 가져올 수 없는 경우 `이 영상의 자막을 가져올 수 없습니다.` 메시지를 반환한다.
- 작업 완료 후에는 테스트/빌드, Memory Bank 업데이트, Git 커밋, GitHub push까지 수행한다.
- 커밋 메시지는 한국어로 작성한다.
- OpenAI 연동은 PRD의 `gpt-4o-mini` 요구사항에 맞춰 Chat Completions API를 사용한다.
- `OPENAI_API_KEY`는 백엔드 환경변수로만 관리한다.
- `SummaryService`는 자막 추출 후 `OpenAiSummaryClient`를 호출해 실제 요약 응답을 반환한다.
- `main` 브랜치는 배포 브랜치로 사용한다.
- `develop` 브랜치는 기본 개발 브랜치로 사용한다.
- 새로운 작업은 `develop`에서 `feature/*` 브랜치를 생성해 진행한다.
- `feature/*` 브랜치는 `develop`으로 Pull Request를 생성하고, `develop` 검증 후 `main`으로 병합한다.
- 현재 작업 브랜치는 `feature/update-git-workflow`이다.

## 열린 이슈

- 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- 로컬/배포 환경에 실제 `OPENAI_API_KEY`를 설정해야 한다.
- 프론트엔드와 백엔드 배포 URL은 아직 정해지지 않았다.

## 다음 작업

1. 로컬에서 `OPENAI_API_KEY`를 설정하고 백엔드/프론트엔드 전체 흐름을 검증한다.
2. 프론트엔드 에러 메시지 처리를 API 응답 메시지 기반으로 개선한다.
3. Render/Vercel 배포 설정을 진행한다.
