# Current State

## 현재 마일스톤

Memory Bank 구조 개편

## 진행 중 작업

`AGENTS.md`의 `decisions.md` 섹션을 ADR 중심 기록 규칙으로 개편했다. `decisions.md`의 최신 운영 결정도 ADR-001, ADR-002 형식으로 정리했다. 백엔드 테스트, 프론트엔드 빌드, 변경사항 리뷰가 완료되었고 PR #4에 추가 커밋으로 반영한다.

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
- `decisions.md` 기록 형식에 사용할 상태 값은 `채택`, `폐기`, `대체됨`, `보류`로 정했다.
- 백엔드 `.\gradlew.bat test`가 성공했다.
- 프론트엔드 `npm run build`가 성공했다.
- 변경사항을 `git diff` 기준으로 리뷰했다.
- `AGENTS.md`의 `decisions.md` 섹션에 ADR 번호, 날짜, 상태 의미, 기록 규칙을 추가했다.
- `decisions.md`의 Memory Bank 구조 개편 결정을 `ADR-001`로 정리했다.
- `decisions.md`의 의사결정 기록 형식 변경 결정을 `ADR-002`로 정리했다.
- 백엔드 `.\gradlew.bat test`가 성공했다.
- 프론트엔드 `npm run build`가 성공했다.
- 변경사항을 `git diff` 기준으로 리뷰했다.

## 열린 이슈

- 로컬/배포 환경에는 실제 `OPENAI_API_KEY`를 설정해야 한다.
- 프론트엔드와 백엔드 배포 URL은 아직 확정되지 않았다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- `develop -> main` PR #1은 배포 준비 단계에서 다시 확인해야 한다.

## 다음 작업

1. PR #4 `Memory Bank 구조 개편`을 검토하고 필요 시 병합한다.
2. 실제 `OPENAI_API_KEY`를 설정하고 로컬 전체 요약 흐름을 검증한다.
3. 프론트엔드 에러 메시지를 API 응답 메시지 기반으로 개선한다.
