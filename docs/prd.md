# PRD - AI Transcript Summarizer (MVP v1)

## 1. 프로젝트 개요

### 프로젝트명

AI Transcript Summarizer

### 목표

사용자가 유튜브 영상을 입력하면 AI가 내용을 분석하여 핵심 내용을 요약해주는 웹 서비스

### 프로젝트 목적

- OpenAI API 활용 경험 확보
- Spring Boot API 개발 경험 확보
- React + Vite 프론트엔드 경험 확보
- 하루 안에 개발 및 배포 완료

---

## 2. MVP 범위

### 포함 기능

#### 입력

- 유튜브 영상 URL 입력

#### 출력

- 3줄 요약
- 핵심 포인트 5개
- 핵심 키워드

### 제외 기능

- 로그인
- 회원가입
- 결제
- 히스토리 저장
- DB 연동

---

## 3. 사용자 시나리오

1. 사용자는 요약하고 싶은 유튜브 영상을 찾는다.
2. 서비스에 접속한다.
3. 유튜브 영상 URL을 입력한다.
4. "요약하기" 버튼을 누른다.
5. 서비스가 영상의 자막을 가져온다.
6. AI가 결과를 생성한다.
7. 사용자는 핵심 내용을 확인한다.

---

## 4. 기능 요구사항

### F-001 유튜브 영상 URL 입력

#### 설명

사용자는 요약할 유튜브 영상 URL을 입력할 수 있다.

#### 입력 조건

- 유효한 유튜브 영상 URL이어야 한다.
- 지원 URL 예시:
  - `https://www.youtube.com/watch?v=VIDEO_ID`
  - `https://youtu.be/VIDEO_ID`

#### 예외 처리

유효하지 않은 URL 메시지:

```text
유효한 유튜브 영상 URL을 입력해주세요.
```

### F-002 자막 추출

#### 설명

입력된 유튜브 영상 URL에서 요약에 사용할 자막(Transcript)을 가져온다.

#### 처리 내용

- 유튜브 영상 ID 추출
- 사용 가능한 자막 조회
- 자막 텍스트 구성

#### 예외 처리

자막을 가져올 수 없는 경우 메시지:

```text
이 영상의 자막을 가져올 수 없습니다.
```

### F-003 AI 요약 요청

#### 설명

추출된 자막을 OpenAI API로 전달한다.

#### 처리 내용

- 전체 요약 생성
- 핵심 포인트 생성
- 키워드 생성

### F-004 결과 출력

#### 출력 항목

3줄 요약 예시:

- 이 영상은 GPT 에이전트의 개념을 설명한다.
- 에이전트 구조와 활용 사례를 소개한다.
- 향후 발전 방향을 전망한다.

핵심 포인트 예시:

- GPT Agent 정의
- Tool Calling 구조
- Memory 활용
- 실제 사례
- 한계점

키워드 예시:

- GPT
- Agent
- LLM
- Memory
- Automation

---

## 5. 화면 설계

### 메인 화면

- 유튜브 영상 URL 입력 필드
- 요약하기 버튼

### 결과 화면

#### 요약

3줄 요약을 표시한다.

#### 핵심 포인트

최대 5개의 핵심 포인트를 목록으로 표시한다.

#### 키워드

키워드를 해시태그 형태로 표시한다.

---

## 6. API 설계

### 요약 요청

```http
POST /api/summarize
```

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

---

## 7. AI 프롬프트

System Prompt:

```text
You are an expert content analyst.

Analyze the transcript extracted from a YouTube video and return JSON only.

Required:
1. summary (3 sentences)
2. keyPoints (maximum 5)
3. keywords (maximum 10)

Response Format:
{
  "summary": [],
  "keyPoints": [],
  "keywords": []
}

Transcript:
{{transcript}}
```

---

## 8. 기술 스택

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
- YouTube transcript extraction

### AI

- OpenAI API
- GPT-4o-mini

### Database

- 사용하지 않음

---

## 9. 비기능 요구사항

### 응답 시간

- 목표: 10초 이내
- 최대: 20초 이내

### 보안

- API Key는 서버 환경변수 관리
- 프론트엔드 노출 금지
- 유튜브 영상 URL은 요약 요청 처리에만 사용하며 DB에 저장하지 않음

---

## 10. 배포

### 배포 환경

- AWS Cloud Free Tier 기반 EC2

### 배포 방식

- Docker Compose 기반 단일 서버 배포
- Nginx Reverse Proxy
- GitHub Actions CI/CD
- GHCR(GitHub Container Registry) Docker 이미지 저장소

### 환경변수

- `OPENAI_API_KEY`
- GitHub Actions 및 서버 배포에 필요한 SSH/서버 접속 정보는 GitHub Secrets로 관리한다.

---

## 11. 성공 기준

- 사용자는 유튜브 영상 URL을 입력할 수 있다.
- 서비스는 입력된 유튜브 영상에서 자막을 가져올 수 있다.
- 사용자는 요약 결과를 받을 수 있다.
- 서비스가 실제 운영 URL에서 동작한다.
- OpenAI API 연동이 정상 동작한다.
- 배포까지 완료된다.
