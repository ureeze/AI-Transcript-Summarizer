# Current State

## 현재 마일스톤

OpenAI API 연동 로컬 검증

## 진행 중 작업

없음

## 최근 완료 작업

- [T-068] PR #7 `docs: Git PR 운영 규칙 보강` develop 병합 및 로컬 develop 동기화 (done: 2026-06-11)
- [T-089] `AGENTS.md` PR 병합 상태 기록 커밋 금지 원칙 추가 (done: 2026-06-11)
- [T-090] `coding-rules.md` PR 병합 후 Memory Bank 업데이트 처리 기준 보강 (done: 2026-06-11)
- [T-091] ADR-012 PR 병합 상태 기록 커밋 금지 명확화 기록 (done: 2026-06-11)
- [T-092] PR 병합 기록 커밋 정책 변경사항 리뷰 완료 (done: 2026-06-11)
- [T-074] 실제 `OPENAI_API_KEY` 설정 후 OpenAI API 호출 검증 대기 해소 (done: 2026-06-13)
- [T-097] `OpenAiSummaryClient` Spring Bean 생성자 주입 오류 수정 (done: 2026-06-13)
- [T-098] OpenAI API 실제 호출과 서비스 프롬프트 JSON 배열 응답 검증 (done: 2026-06-13)
- [T-099] YouTube 자막 트랙 fallback 시도 로직과 테스트 추가 (done: 2026-06-13)

## 열린 이슈

- [T-069] YouTube `timedtext` 자막 엔드포인트가 공개 자막이 있는 영상에서도 200 응답과 빈 본문을 반환해 URL 입력부터 요약 결과까지의 전체 성공 흐름 검증이 보류되었다.
- [T-071/T-072] 프론트엔드와 백엔드 배포 URL은 아직 확정되지 않았다.
- YouTube 비공식 공개 자막 추출 방식은 YouTube 응답 구조 변경에 영향을 받을 수 있다.
- [T-073] `develop -> main` PR #1은 배포 준비 단계에서 다시 확인해야 한다.

## 다음 작업

1. [T-070] 프론트엔드 에러 메시지를 API 응답 메시지 기반으로 개선한다.
2. [T-071] Render 백엔드 배포 설정을 진행한다.
3. [T-072] Vercel 프론트엔드 배포 설정을 진행한다.
