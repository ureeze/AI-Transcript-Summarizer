# Architecture

## 시스템 구조

```text
frontend/
  사용자 입력과 결과 표시 담당
  backend POST /api/summarize 호출

backend/
  요청 검증, 자막 추출, 요약 요청 조율

external services/
  YouTube 자막 데이터
  AI 요약 API
```

## 배포 구조

```text
GitHub
  - GitHub Actions에서 frontend/backend Docker 이미지를 빌드한다.
  - 빌드된 이미지를 GHCR에 push한다.
  - AWS EC2 서버에 접속해 docker compose pull/up으로 배포한다.

AWS EC2
  - Nginx가 외부 HTTP 요청을 받는다.
  - / 요청은 frontend 컨테이너로 전달한다.
  - /api/* 요청은 backend 컨테이너로 전달한다.
  - backend 컨테이너는 YouTube 자막 데이터와 OpenAI API를 호출한다.
```

## 책임 경계

```text
Frontend
  - 유튜브 URL 입력을 받는다.
  - 기본 URL 형식을 검증한다.
  - 요약 요청 상태와 결과를 표시한다.

Backend
  - API 요청을 검증한다.
  - 유튜브 영상 ID와 자막을 가져온다.
  - 자막을 AI 요약 요청으로 변환한다.
  - 사용자 친화적인 성공/실패 응답을 반환한다.

External Services
  - YouTube는 자막 원천이다.
  - AI Provider는 요약 결과 생성 책임을 가진다.

Deployment
  - AWS EC2는 운영 서버 역할을 가진다.
  - Nginx는 reverse proxy와 라우팅 책임을 가진다.
  - Docker Compose는 서버 내 컨테이너 실행 구성을 관리한다.
  - GitHub Actions와 GHCR은 이미지 빌드, 저장, 배포 흐름을 담당한다.
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

- `summary/controller/SummaryController`
  - `POST /api/summarize` 엔드포인트 제공
- `summary/dto/SummarizeRequest`
  - `youtubeUrl` 검증
- `summary/dto/SummarizeResponse`
  - `summary`, `keyPoints`, `keywords` 응답
- `summary/service/SummaryService`
  - YouTube 자막 추출 후 OpenAI 요약 클라이언트 호출
- `summary/client/OpenAiSummaryClient`
  - AI 요약 API 호출
  - JSON 응답 파싱
- `summary/config/OpenAiProperties`
  - OpenAI API 설정 바인딩
- `summary/exception/SummaryExceptionHandler`
  - 검증 실패, 자막 추출 실패, 요약 실패 응답 처리
- `summary/exception/*Exception`
  - 요약 생성 실패와 자막 추출 실패 예외
- `youtube/service/YouTubeTranscriptService`
  - YouTube video ID 추출
  - 공개 자막 트랙 조회
  - json3 자막 텍스트 구성

## 데이터 흐름

1. 사용자가 프론트엔드에 유튜브 영상 URL을 입력한다.
2. 프론트엔드가 `POST /api/summarize`로 `youtubeUrl`을 전송한다.
3. 백엔드가 URL 형식을 검증한다.
4. `YouTubeTranscriptService`가 영상 ID를 추출하고 자막을 가져온다.
5. `OpenAiSummaryClient`가 자막을 AI 요약 API에 전달한다.
6. 백엔드가 요약 JSON을 `SummarizeResponse`로 반환한다.
7. 프론트엔드가 요약, 핵심 포인트, 키워드를 표시한다.

## 배포 데이터 흐름

1. `develop` 또는 배포 대상 브랜치의 변경사항이 GitHub에 push된다.
2. GitHub Actions가 frontend/backend Docker 이미지를 빌드한다.
3. GitHub Actions가 이미지를 GHCR에 push한다.
4. GitHub Actions가 AWS EC2에 SSH로 접속한다.
5. EC2에서 Docker Compose가 최신 이미지를 pull하고 컨테이너를 재시작한다.
6. 사용자는 Nginx를 통해 운영 서비스에 접속한다.

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
- 프론트엔드에 AI Provider API Key를 노출하지 않는다.
- 유튜브 영상 URL과 추출 자막은 저장하지 않는다.
- MVP v1에서는 공개 자막 또는 자동 자막이 제공되는 영상만 지원한다.
- YouTube 응답 구조 변경에 따른 실패 가능성을 예외 메시지로 처리한다.
