# Implementation Plan

## 기술 스택

### Frontend

- React
- Vite
- TypeScript
- Tailwind CSS

### Backend

- Java 21
- Spring Boot 3
- Spring Web
- Spring Validation

### AI

- OpenAI API
- GPT-4o-mini

### Database

- 사용하지 않음

## 목표 아키텍처

```text
frontend/
  React + Vite + TypeScript + Tailwind CSS application
  calls backend POST /api/summarize

backend/
  Spring Initializr-based Gradle Spring Boot application
  exposes POST /api/summarize
  validates YouTube video URL input
  extracts transcript from YouTube video
  calls OpenAI API using OPENAI_API_KEY
```

## 현재 폴더 구조

```text
frontend/
  package.json
  index.html
  vite.config.ts
  tailwind.config.ts
  postcss.config.js
  tsconfig.json
  tsconfig.app.json
  tsconfig.node.json
  src/
    main.tsx
    App.tsx
    styles.css
    vite-env.d.ts

backend/
  settings.gradle
  build.gradle
  gradlew
  gradlew.bat
  gradle/wrapper/
    gradle-wrapper.jar
    gradle-wrapper.properties
  HELP.md
  src/main/java/com/example/aitranscriptsummarizer/
    AiTranscriptSummarizerApplication.java
    summary/
      ApiErrorResponse.java
      SummarizeRequest.java
      SummarizeResponse.java
      SummaryController.java
      SummaryExceptionHandler.java
      SummaryService.java
      TranscriptUnavailableException.java
      YouTubeTranscriptService.java
  src/main/resources/
    application.yml
  src/test/java/com/example/aitranscriptsummarizer/
    AiTranscriptSummarizerApplicationTests.java
    summary/
      YouTubeTranscriptServiceTests.java
```

## API 계약

### POST /api/summarize

Request:

```json
{
  "youtubeUrl": "https://www.youtube.com/watch?v=VIDEO_ID"
}
```

Response:

```json
{
  "summary": ["...", "...", "..."],
  "keyPoints": ["...", "...", "..."],
  "keywords": ["GPT", "Agent", "LLM"]
}
```

## 백엔드 구현 계획

- `SummarizeRequest`
  - `youtubeUrl`: required, valid YouTube video URL
- `SummarizeResponse`
  - `summary`: list of strings
  - `keyPoints`: list of strings
  - `keywords`: list of strings
- `YouTubeTranscriptService`
  - MVP v1에서는 비공식 공개 자막 추출 방식을 사용
  - 공식 YouTube Captions API는 OAuth/소유권 권한 제약 때문에 사용하지 않음
  - 공개 자막 또는 자동 자막이 제공되는 영상만 지원
  - YouTube URL에서 video ID 추출
  - 사용 가능한 자막 조회
  - 자막 텍스트 구성
  - 자막이 없거나 가져올 수 없으면 `이 영상의 자막을 가져올 수 없습니다.` 예외 응답 반환
- `SummaryController`
  - `POST /api/summarize`
  - local Vite dev server CORS 허용
- `SummaryService`
  - 현재는 자막 추출 후 더미 요약 응답 반환
  - 이후 OpenAI API 연동으로 교체
  - YouTube 자막 추출 결과를 입력으로 사용
  - prompt 구성
  - OpenAI API 호출
  - JSON 응답 파싱
- `SummaryExceptionHandler`
  - 유효성 검증 실패 시 사용자 메시지 반환
  - 자막 추출 실패 시 `422 Unprocessable Entity`와 `이 영상의 자막을 가져올 수 없습니다.` 반환
- 환경변수
  - `OPENAI_API_KEY`

## 프론트엔드 구현 계획

- 메인 화면
  - 유튜브 영상 URL 입력 필드
  - 요약하기 버튼
- 상태
  - 입력값
  - 로딩
  - 에러
  - 결과
- 클라이언트 검증
  - 비어 있는 URL 입력 차단
  - 유효하지 않은 유튜브 URL 입력 차단
- 결과 출력
  - 3줄 요약
  - 핵심 포인트
  - 키워드 해시태그

## 배포 계획

- Frontend: Vercel
- Backend: Render
- Backend environment variable: `OPENAI_API_KEY`
- Frontend environment variable: backend API base URL

## 검증 계획

- 백엔드 단위 또는 통합 테스트
  - 유효하지 않은 YouTube URL 검증 실패
  - 자막 추출 실패 처리 검증
  - 정상 요청 응답 형식 검증
- 프론트엔드 빌드 확인
- 백엔드 빌드 확인
- 로컬 실행 후 브라우저에서 입력-요약 흐름 확인

## 현재 검증 상태

- 프론트엔드 `npm install`: 성공
- 프론트엔드 `npm run build`: 성공
- 백엔드 `.\gradlew.bat test`: 성공
- 백엔드 URL 검증 테스트: 성공
- 백엔드 video ID 추출 테스트: 성공
- 백엔드 자막 추출 실패 처리 테스트: 성공
- 프론트엔드 `npm run build`: 성공
