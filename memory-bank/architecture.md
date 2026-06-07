# Architecture

## 시스템 구조

```text
frontend/
  React + Vite + TypeScript + Tailwind CSS
  POST /api/summarize 호출

backend/
  Spring Boot 3 + Java 21
  YouTube URL 검증
  YouTube 자막 추출
  OpenAI API 호출
```

## 모듈 구성

### Frontend

- `src/App.tsx`
  - 유튜브 URL 입력
  - 클라이언트 검증
  - API 호출
  - 로딩/에러/결과 상태 표시
- `src/main.tsx`
  - React 애플리케이션 엔트리
- `src/styles.css`
  - Tailwind CSS 기반 스타일

### Backend

- `SummaryController`
  - `POST /api/summarize` 엔드포인트 제공
- `SummarizeRequest`
  - `youtubeUrl` 검증
- `SummarizeResponse`
  - `summary`, `keyPoints`, `keywords` 응답
- `SummaryService`
  - YouTube 자막 추출 후 OpenAI 요약 클라이언트 호출
- `YouTubeTranscriptService`
  - YouTube video ID 추출
  - 공개 자막 트랙 조회
  - json3 자막 텍스트 구성
- `OpenAiSummaryClient`
  - OpenAI Chat Completions API 호출
  - JSON 응답 파싱
- `SummaryExceptionHandler`
  - 검증 실패, 자막 추출 실패, 요약 실패 응답 처리

## 데이터 흐름

1. 사용자가 프론트엔드에 유튜브 영상 URL을 입력한다.
2. 프론트엔드가 `POST /api/summarize`로 `youtubeUrl`을 전송한다.
3. 백엔드가 URL 형식을 검증한다.
4. `YouTubeTranscriptService`가 영상 ID를 추출하고 자막을 가져온다.
5. `OpenAiSummaryClient`가 자막을 OpenAI API에 전달한다.
6. 백엔드가 요약 JSON을 `SummarizeResponse`로 반환한다.
7. 프론트엔드가 요약, 핵심 포인트, 키워드를 표시한다.

## API 구조

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

## 데이터베이스 구조

DB는 사용하지 않는다.

## 주요 설계 원칙

- API Key는 백엔드 환경변수로만 관리한다.
- 프론트엔드에 OpenAI API Key를 노출하지 않는다.
- 유튜브 영상 URL과 추출 자막은 저장하지 않는다.
- MVP v1에서는 공개 자막 또는 자동 자막이 제공되는 영상만 지원한다.
- YouTube 응답 구조 변경에 따른 실패 가능성을 예외 메시지로 처리한다.
