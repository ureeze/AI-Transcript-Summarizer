# Active Context

## 현재 마일스톤

MVP v1 YouTube 공개 자막 추출 구현

## 현재 작업

작업 종료 시 Git 커밋과 GitHub push를 수행하도록 `AGENTS.md` 운영 규칙을 추가했다.

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

## 열린 이슈

- 현재 백엔드 응답은 자막 추출 후 OpenAI 연동 전 더미 요약 응답이다.
- 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- OpenAI API Key와 배포 환경변수는 아직 설정되지 않았다.
- 프론트엔드와 백엔드 배포 URL은 아직 정해지지 않았다.
- 현재 작업 트리에는 YouTube 자막 추출 구현과 AGENTS.md Git 규칙 변경이 아직 커밋되지 않았다.

## 다음 작업

1. OpenAI API 연동 방식을 구현한다.
2. 추출된 자막을 `gpt-4o-mini` 요약 요청에 연결한다.
3. 로컬에서 백엔드/프론트엔드 전체 흐름을 검증한다.
