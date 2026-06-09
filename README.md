# AI Transcript Summarizer

유튜브 영상 URL을 입력하면 AI가 영상 자막을 분석해 3줄 요약, 핵심 포인트, 키워드를 생성하는 MVP 프로젝트입니다.

## 구조

```text
docs/         PRD 문서
frontend/     React + Vite + TypeScript + Tailwind CSS
backend/      Spring Boot 3 + Java 21 + Gradle
memory-bank/  프로젝트 장기 기억과 작업 상태
```

## 로컬 실행

### Frontend

```bash
cd frontend
npm install
npm run dev
```

### Backend

```bash
cd backend
$env:OPENAI_API_KEY="your_api_key"
.\gradlew.bat bootRun
```

현재 백엔드는 Spring Initializr 기반 Gradle 프로젝트이며 Gradle Wrapper를 포함한다.

## 환경변수

### Backend

```text
OPENAI_API_KEY=OpenAI API key
```

API Key는 백엔드 환경변수로만 설정하며 프론트엔드에 노출하지 않는다.

### Frontend

```text
VITE_API_BASE_URL=http://localhost:8080
```

## 다음 단계

1. 로컬에서 백엔드와 프론트엔드를 함께 실행해 전체 흐름을 검증한다.
2. Render와 Vercel 배포 환경변수를 설정한다.
3. 운영 URL에서 실제 유튜브 URL 요약 흐름을 확인한다.
