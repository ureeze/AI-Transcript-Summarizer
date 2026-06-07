# Current State

## 현재 마일스톤

Memory Bank 구조 개편

## 진행 중 작업

`AGENTS.md`를 새 Memory Bank 구조 기준으로 교체하고, PRD와 Memory Bank 문서를 새 경로와 파일명으로 마이그레이션했다. 백엔드 테스트, 프론트엔드 빌드, 변경사항 리뷰가 완료되었다.

## 최근 완료 작업

- PR #2 `AGENTS 작업 리뷰와 PR 정책 추가`가 `develop`에 병합되었다.
- PR #3 `PR 2 병합 상태 기록`이 `develop`에 병합되었다.
- 로컬 `develop`은 `origin/develop` 최신 상태로 동기화되었다.
- 현재 작업 브랜치는 `feature/restructure-memory-bank`이다.
- PRD는 `docs/prd.md`로 이동했다.
- Memory Bank는 역할별 8개 문서 구조로 개편되었다.
- 백엔드 `.\gradlew.bat test`가 성공했다.
- 프론트엔드 `npm run build`가 성공했다.
- 변경사항을 `git diff --cached` 기준으로 리뷰했다.

## 열린 이슈

- 로컬/배포 환경에는 실제 `OPENAI_API_KEY`를 설정해야 한다.
- 프론트엔드와 백엔드 배포 URL은 아직 확정되지 않았다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- `develop -> main` PR #1은 배포 준비 단계에서 다시 확인해야 한다.

## 다음 작업

1. 변경사항을 커밋하고 GitHub에 push한다.
2. `feature/restructure-memory-bank`에서 `develop`으로 Pull Request를 생성한다.
3. 이후 실제 `OPENAI_API_KEY`를 설정하고 로컬 전체 요약 흐름을 검증한다.
