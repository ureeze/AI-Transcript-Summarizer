# Project Brief

## 프로젝트 목적

AI Transcript Summarizer는 사용자가 유튜브 영상 URL을 입력하면 영상 자막을 기반으로 AI가 핵심 내용을 요약해주는 MVP 웹 서비스이다.

## 핵심 목표

- 사용자가 유튜브 영상 URL을 입력할 수 있다.
- 서비스가 유튜브 영상의 공개 자막 또는 자동 자막을 가져올 수 있다.
- OpenAI API를 사용해 3줄 요약, 핵심 포인트, 키워드를 생성한다.
- 프론트엔드와 백엔드가 운영 URL에서 동작한다.
- OpenAI API, Spring Boot, React + Vite 개발 경험을 확보한다.

## 주요 사용자

- 긴 유튜브 영상의 핵심 내용을 빠르게 파악하려는 사용자
- 영상을 보기 전에 요약을 확인하려는 사용자
- OpenAI 기반 요약 서비스 MVP 개발 흐름을 학습하려는 개발자

## 핵심 기능

- 유튜브 영상 URL 입력
- 유튜브 영상 ID 추출
- 유튜브 자막 추출
- AI 요약 생성
- 3줄 요약 출력
- 핵심 포인트 최대 5개 출력
- 키워드 최대 10개 출력

## 프로젝트 범위

### 포함

- React + Vite + TypeScript + Tailwind CSS 프론트엔드
- Java 21 + Spring Boot 3 백엔드
- YouTube 비공식 공개 자막 추출
- OpenAI `gpt-4o-mini` 요약 연동

### 제외

- 로그인
- 회원가입
- 결제
- 히스토리 저장
- DB 연동
