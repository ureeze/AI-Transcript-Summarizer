# Current State

## 현재 마일스톤

[ATS-1] Prepare MVP for Production Deployment

## 진행 중 작업

- 없음

## 최근 완료 작업

- [T-068] PR #7 `docs: Git PR 운영 규칙 보강` develop 병합 및 로컬 develop 동기화 (done: 2026-06-11)
- [T-089] `AGENTS.md` PR 병합 상태 기록 커밋 금지 원칙 추가 (done: 2026-06-11)
- [T-090] `coding-rules.md` PR 병합 후 Memory Bank 업데이트 처리 기준 보강 (done: 2026-06-11)
- [T-091] ADR-012 PR 병합 상태 기록 커밋 금지 명확화 기록 (done: 2026-06-11)
- [T-092] PR 병합 기록 커밋 정책 변경사항 리뷰 완료 (done: 2026-06-11)
- [T-093] [ATS-7] Jira 에픽 `ATS-1`과 남은 작업 `ATS-2`~`ATS-6` 매핑 반영 (done: 2026-06-13)
- [T-094] [ATS-7] `coding-rules.md` Jira 이슈 키 기반 브랜치/커밋/PR 규칙 추가 (done: 2026-06-13)
- [T-095] [ATS-7] ADR-013 Jira 이슈 키 기반 작업 추적 도입 기록 (done: 2026-06-13)
- [T-096] [ATS-7] Jira 이슈 키 작업 흐름 변경사항 리뷰 완료 (done: 2026-06-13)
- [T-074] [ATS-2] 실제 `OPENAI_API_KEY` 설정 후 OpenAI API 호출 검증 대기 해소 (done: 2026-06-13)
- [T-097] [ATS-2] `OpenAiSummaryClient` Spring Bean 생성자 주입 오류 수정 (done: 2026-06-13)
- [T-098] [ATS-2] OpenAI API 실제 호출과 서비스 프롬프트 JSON 배열 응답 검증 (done: 2026-06-13)
- [T-099] [ATS-2] YouTube 자막 트랙 fallback 시도 로직과 테스트 추가 (done: 2026-06-13)
- [T-100] [ATS-8] 백엔드 패키지 구조를 도메인 기준 + 계층 하위 구조로 정리 (done: 2026-06-13)
- [T-070] [ATS-3] 프론트엔드 에러 메시지를 API 응답 메시지 기반으로 개선 (done: 2026-06-13)
- [T-101] [ATS-4] 배포 전략을 AWS Cloud Free Tier + Nginx + Docker Compose + GitHub Actions + GHCR로 변경 (done: 2026-06-14)
- [T-102] [ATS-4] Dockerfile, Docker Compose, Nginx 로컬 배포 구성 추가 및 검증 (done: 2026-06-14)
- [T-103] [ATS-4] EC2 Amazon Linux 2023 서버에 Docker, Git, Docker Compose, Buildx 설치 (done: 2026-06-15)
- [T-104] [ATS-4] PR #15 develop 병합 및 EC2 SSH 재확인 실패 기록 (done: 2026-06-15)
- [T-105] [ATS-4] EC2 SSH 접속 복구 확인 및 서버 저장소를 최신 develop으로 동기화 (done: 2026-06-15)

## 열린 이슈

- [T-069] [ATS-2] YouTube `timedtext` 자막 엔드포인트가 공개 자막이 있는 영상에서도 200 응답과 빈 본문을 반환해 URL 입력부터 요약 결과까지의 전체 성공 흐름 검증이 보류되었다.
- [T-071/T-072] [ATS-4/ATS-5] AWS EC2 서버, 운영 도메인, GitHub Actions 배포 Secrets는 아직 확정되지 않았다.
- [T-071] [ATS-4] EC2 SSH 접속은 복구되었고 서버 저장소는 최신 `develop`으로 동기화되었다. 다만 EC2 프리티어 서버에서 직접 Docker 이미지를 빌드하지 않기로 했으므로 실제 배포 적용은 [T-072] GitHub Actions + GHCR 구성 이후 진행한다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- [T-073] [ATS-6] `develop -> main` PR #1은 배포 준비 단계에서 다시 확인해야 한다.

## 다음 작업

1. [T-072] [ATS-5] 서버 직접 빌드 대신 GitHub Actions + GHCR 기반 이미지 빌드/배포 구성을 진행한다.
2. [T-071] [ATS-4] GHCR 이미지가 준비된 뒤 EC2에서 `docker compose pull`과 `docker compose up -d`로 배포를 적용한다.
3. [T-073] [ATS-6] 운영 배포 검증 후 `develop -> main` PR #1을 검토한다.
