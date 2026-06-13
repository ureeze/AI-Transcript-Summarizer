# AI Transcript Summarizer

유튜브 영상 URL을 입력하면 AI가 영상 자막을 분석해 3줄 요약, 핵심 포인트, 키워드를 생성하는 MVP 프로젝트입니다.

## 구조

```text
docs/         PRD 문서
frontend/     React + Vite + TypeScript + Tailwind CSS
backend/      Spring Boot 3 + Java 21 + Gradle
deploy/       Docker Compose + Nginx 배포 구성
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

Docker Compose 배포에서는 프론트엔드를 같은 origin에서 제공하므로 `VITE_API_BASE_URL`을 빈 값으로 빌드해 `/api` 경로를 사용한다.

## Docker Compose 실행

```bash
cd deploy
Copy-Item .env.example .env
```

`deploy/.env`의 `OPENAI_API_KEY` 값을 설정한 뒤 실행한다.

```bash
docker compose up --build
```

서비스는 Nginx를 통해 `http://localhost`에서 접근한다.

`docker compose config`는 환경변수 값을 출력할 수 있으므로 실제 API Key가 설정된 터미널에서는 실행 결과를 공유하지 않는다.

## 다음 단계

1. Docker Compose 기반 로컬 컨테이너 실행 흐름을 검증한다.
2. AWS EC2 서버에 Docker와 Docker Compose 실행 환경을 준비한다.
3. GitHub Actions와 GHCR 기반 배포 자동화를 구성한다.
