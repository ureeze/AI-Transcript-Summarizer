# Current State

## 현재 마일스톤

[ATS-1] Prepare MVP for Production Deployment 완료

## 진행 중 작업

- 없음

## 최근 완료 작업

- [T-118] [ATS-5] AWS Console 확인 결과 backend/frontend ECR 저장소 모두 tagged image index가 3개씩 남아 있음을 확인했다. lifecycle policy는 아직 기대한 결과를 만들지 못했다. (done: 2026-06-16)
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
- [T-107] [ATS-4] PR #16 develop 병합 및 로컬 develop 동기화 (done: 2026-06-15)
- [T-072] [ATS-5] GitHub Actions + ECR 기반 CI/CD 구성 (done: 2026-06-15)
- [T-108] [ATS-5] GitHub Actions 이미지 저장소 구성을 GHCR에서 ECR로 변경 (done: 2026-06-16)
- [T-109] [ATS-5] PR #17 develop 병합 및 ECR/Secrets 설정 가능 여부 확인 (done: 2026-06-16)
- [T-106] [ATS-5] ECR repository와 GitHub Secrets 설정 후 GitHub Actions 배포 workflow 수동 실행 성공 (done: 2026-06-16)
- [T-071] [ATS-4] AWS EC2 서버에 Docker Compose 배포 적용 완료 (done: 2026-06-16)
- [T-075] [ATS-4/ATS-5] 배포된 서비스의 프론트엔드 화면, URL 입력, 백엔드 API 호출, 에러 메시지 흐름 검증 완료 (done: 2026-06-16)
- [T-073] [ATS-6] `develop -> main` PR #1 검토 및 병합 완료 (done: 2026-06-16)
- [T-110] [ATS-2] YouTube 자막 추출 실패 시 transcript 직접 입력 fallback 전략 결정 완료 (done: 2026-06-16)
- [T-111] [ATS-2] YouTube 자막 추출 실패 시 transcript 직접 입력 fallback UI/API 구현 및 로컬 검증 완료 (done: 2026-06-16)
- [T-112] [ATS-2] transcript 직접 입력 fallback 구현을 `develop` 배포 후 운영 환경에서 검증 완료 (done: 2026-06-16)
- [T-113] [ATS-2] 운영 환경 direct transcript 요약 실패 원인 확인 및 EC2 `OPENAI_API_KEY` 교정 완료 (done: 2026-06-16)
- [T-114] [ATS-5] GitHub repository `OPENAI_API_KEY` Secret 수정이 재배포에 반영되는지 검증 완료, Secret 값 정상 반영 확인 및 운영 direct transcript fallback 복구 완료 (done: 2026-06-16)
- [T-115] [ATS-5] 수정된 deploy workflow가 `develop` 배포에서도 nginx 502 재발을 막는지 검증 완료, 운영 URL 200 및 direct transcript fallback 200 확인 (done: 2026-06-16)
- [T-116] [ATS-6] 최신 `develop` 기준 운영 검증 결과를 바탕으로 `develop -> main` 배포 PR #26 검토 및 병합 완료 (done: 2026-06-16)

## 열린 이슈

- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- [T-117] EC2에서 `aws ecr describe-images` 실행 시 `Unable to locate credentials`가 발생해 ECR lifecycle policy 자동 삭제 결과를 직접 조회하지 못하고 있다. 배포 자체는 SHA `c887811e09fe96e21492e317eb2271eb7db8ac56` 기준으로 성공했다.
- [T-119] AWS Console 확인 결과 backend/frontend ECR 저장소 모두 최신 배포 후에도 tagged image index가 3개씩 남아 있었다. lifecycle policy 조건 또는 실행 시점 분석이 필요하다.

## 다음 작업

1. [T-119] [ATS-5] backend/frontend ECR lifecycle policy 설정을 다시 검토하고, 왜 tagged image index가 3개 유지되는지 원인을 분석한다.
2. [T-117] 필요하면 EC2에 ECR 조회 가능한 IAM Role 또는 AWS credential을 부여한 뒤 CLI로 lifecycle policy 결과를 재검증한다.
