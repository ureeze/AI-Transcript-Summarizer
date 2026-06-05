# AI Transcript Summarizer

유튜브 영상 URL을 입력하면 AI가 영상 자막을 분석해 3줄 요약, 핵심 포인트, 키워드를 생성하는 MVP 프로젝트입니다.

## 구조

```text
frontend/     React + Vite + TypeScript + Tailwind CSS
backend/      Spring Boot 3 + Java 21 + Gradle
memory-bank/  프로젝트 상태와 의사결정 기록
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
.\gradlew.bat bootRun
```

현재 백엔드는 Spring Initializr 기반 Gradle 프로젝트이며 Gradle Wrapper를 포함한다.

## 다음 단계

1. 백엔드 더미 API를 실행한다.
2. 프론트엔드 화면과 API 연결을 구현한다.
3. OpenAI API 연동을 추가한다.
