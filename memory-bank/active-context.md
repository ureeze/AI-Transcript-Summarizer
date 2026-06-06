# Active Context

## 현재 마일스톤

MVP v1 개발 운영 규칙 정비

## 현재 작업

`AGENTS.md`에 작업 절차, 코드 리뷰 규칙, Pull Request 정책을 추가하고 작업 완료 기준을 강화했다. 백엔드 테스트와 프론트엔드 빌드는 통과했다.

## 최근 결정 사항

- 작업 절차에 코드 리뷰 수행, Pull Request 생성 단계를 추가했다.
- 문서 업데이트 없이 작업을 종료하지 않는 기존 원칙을 유지한다.
- Git 커밋과 GitHub push 없이 작업을 종료하지 않는 원칙을 유지한다.
- 커밋 또는 push 보류는 사용자가 명시적으로 요청하거나 테스트/빌드 실패, 인증/네트워크/권한 문제, 민감 정보 확인 필요 등 명확한 사유가 있을 때만 허용한다.
- 보류 시 보류 사유와 현재 Git 상태를 `active-context.md`, `progress.md`, 최종 응답에 기록한다.
- 커밋 전 `git diff` 기준 코드 리뷰를 수행하고, 버그 가능성, 예외 처리, 보안, 테스트, 성능, 불필요한 코드, 아키텍처 위반, 문서 업데이트 필요 여부를 확인한다.
- `feature/*` 브랜치 작업 완료 후 Pull Request를 생성한다.
- PR 생성 전 테스트 성공, 빌드 성공, Memory Bank 업데이트, 코드 리뷰 완료, `git status` 확인을 완료한다.
- PR 생성 후 변경 파일과 변경 내용을 최종 검토한다.

## 열린 이슈

- 로컬/배포 환경에는 실제 `OPENAI_API_KEY`를 설정해야 한다.
- 프론트엔드와 백엔드 배포 URL은 아직 확정되지 않았다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- 현재 작업 브랜치는 `feature/update-agents-review-pr-policy`이다.

## 다음 작업

1. 변경된 `AGENTS.md` 정책을 diff 기준으로 최종 리뷰한다.
2. 문서 변경 사항을 커밋하고 GitHub에 push한다.
3. `feature/update-agents-review-pr-policy`에서 `develop`으로 Pull Request를 생성한다.
4. 이후 실제 `OPENAI_API_KEY`를 설정하고 로컬 전체 요약 흐름을 검증한다.
