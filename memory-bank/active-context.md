# Active Context

## 현재 마일스톤

MVP v1 YouTube URL 입력 흐름 구현

## 현재 작업

변경된 PRD에 맞춰 백엔드 요청 DTO와 프론트엔드 입력 UI를 `youtubeUrl` 기반으로 수정했다.

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

## 열린 이슈

- 유튜브 자막 추출 방식과 사용할 라이브러리 또는 API를 결정해야 한다.
- 현재 백엔드 응답은 자막 추출/OpenAI 연동 전 더미 응답이다.
- OpenAI API Key와 배포 환경변수는 아직 설정되지 않았다.
- 프론트엔드와 백엔드 배포 URL은 아직 정해지지 않았다.

## 다음 작업

1. 유튜브 자막 추출 방식을 결정하고 구현 계획에 반영한다.
2. `YouTubeTranscriptService`를 추가해 영상 ID 추출과 자막 조회를 구현한다.
3. 추출된 자막을 OpenAI 요약 요청에 연결한다.
