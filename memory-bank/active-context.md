# Active Context

## 현재 마일스톤

MVP v1 개발 운영 규칙 정비 완료 및 로컬 전체 흐름 검증 준비

## 현재 작업

PR #2 상태를 Memory Bank에 반영했다. 백엔드 테스트와 프론트엔드 빌드는 통과했다.

## 최근 결정 사항

- `AGENTS.md`에 코드 리뷰 수행, Pull Request 생성, PR 생성 전 확인 항목을 작업 완료 흐름으로 반영했다.
- `feature/update-agents-review-pr-policy` 브랜치는 PR #2로 `develop`에 병합되었다.
- PR #2 제목은 `AGENTS 작업 리뷰와 PR 정책 추가`이다.
- PR #2 URL은 `https://github.com/ureeze/AI-Transcript-Summarizer/pull/2`이다.
- PR #2 merge commit은 `850ef11b9141ffb2854ee58d12b9177e9aae5086`이다.
- 로컬 `develop`은 `origin/develop`의 PR #2 merge 상태로 fast-forward 업데이트되었다.
- 현재 작업 브랜치는 `feature/update-pr2-status`이다.

## 열린 이슈

- 로컬/배포 환경에는 실제 `OPENAI_API_KEY`를 설정해야 한다.
- 프론트엔드와 백엔드 배포 URL은 아직 확정되지 않았다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- PR #1(`develop` -> `main`)은 PR #2 merge 이후 상태를 기준으로 다시 확인해야 한다.

## 다음 작업

1. PR #2 상태 정리 변경사항을 커밋하고 GitHub에 push한다.
2. `feature/update-pr2-status`에서 `develop`으로 Pull Request를 생성한다.
3. PR #1(`develop` -> `main`) 상태를 확인하고 develop 최신 변경사항 반영 여부를 검토한다.
4. 실제 `OPENAI_API_KEY`를 설정하고 로컬 전체 요약 흐름을 검증한다.
