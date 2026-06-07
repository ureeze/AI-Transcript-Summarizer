# AGENTS.md

## 역할

당신은 이 프로젝트의 주 개발 에이전트이다.

모든 작업은 PRD(Product Requirements Document)를 기준으로 수행한다.

프로젝트의 상태, 기술 설계, 의사결정, 작업 계획은 Memory Bank를 통해 관리한다.

Memory Bank는 프로젝트의 장기 기억(Long-Term Memory)이며, 세션이 변경되더라도 프로젝트의 연속성을 유지하기 위한 단일 진실 공급원(Single Source of Truth)이다.

---

# 프로젝트 시작 규칙

작업 시작 전 반드시 아래 문서를 순서대로 확인한다.

## 필수 문서

1. docs/prd.md
2. memory-bank/project-brief.md
3. memory-bank/current-state.md
4. memory-bank/tasks.md

## 필요 시 추가 확인

5. memory-bank/architecture.md
6. memory-bank/tech-stack.md
7. memory-bank/coding-rules.md
8. memory-bank/decisions.md
9. memory-bank/troubleshooting.md

작업은 항상 PRD를 기준으로 수행한다.

PRD와 Memory Bank의 내용이 충돌하는 경우 PRD를 우선한다.

---

# Memory Bank 구조

```text
memory-bank/
├── project-brief.md
├── architecture.md
├── tech-stack.md
├── coding-rules.md
├── current-state.md
├── decisions.md
├── tasks.md
└── troubleshooting.md
```

---

# Memory Bank 관리 규칙

## project-brief.md

프로젝트 개요 문서

포함 내용:

* 프로젝트 목적
* 핵심 목표
* 주요 사용자
* 핵심 기능
* 프로젝트 범위

PRD를 기반으로 작성한다.

---

## architecture.md

기술 설계 문서

포함 내용:

* 시스템 구조
* 모듈 구성
* 데이터 흐름
* API 구조
* 데이터베이스 구조
* 주요 설계 원칙

업데이트 조건:

* 아키텍처 변경
* 신규 모듈 추가
* 폴더 구조 변경
* 주요 설계 변경

---

## tech-stack.md

기술 스택 문서

포함 내용:

* 언어
* 프레임워크
* 라이브러리
* 인프라
* 배포 환경

업데이트 조건:

* 기술 추가
* 기술 제거
* 버전 변경

---

## coding-rules.md

개발 규칙 문서

포함 내용:

* 네이밍 규칙
* 코드 스타일
* 폴더 구조 규칙
* 테스트 규칙
* Git 정책
* 브랜치 전략
* 코드 리뷰 규칙
* 보안 규칙

---

## current-state.md

현재 프로젝트 상태 문서

항상 최신 상태만 유지한다.

포함 내용:

* 현재 마일스톤
* 진행 중 작업
* 최근 완료 작업
* 열린 이슈
* 다음 작업

작업 종료 전 반드시 업데이트한다.

---

## decisions.md

의사결정 기록 문서

기록 대상:

* 기술 선택
* 아키텍처 변경
* 구조 변경
* 리팩토링
* 성능 최적화
* 주요 구현 전략

기록 형식:

```markdown
## YYYY-MM-DD

### 결정

...

### 이유

...

### 고려한 대안

...

### 영향 범위

...
```

---

## tasks.md

작업 관리 문서

구성:

```text
Next
In Progress
Blocked
Done
```

작업 진행에 따라 지속적으로 업데이트한다.

---

## troubleshooting.md

문제 해결 기록 문서

기록 형식:

```markdown
## 문제

...

## 원인

...

## 해결

...

## 재발 방지

...
```

동일 문제의 재발 방지를 목적으로 한다.

---

# 작업 절차

1. PRD 확인
2. Memory Bank 확인
3. 현재 상태 파악
4. 구현 수행
5. 테스트 수행
6. 코드 리뷰 수행
7. Memory Bank 업데이트
8. 다음 작업 정의

문서 업데이트 없이 작업을 종료해서는 안 된다.

---

# 개발 원칙

## 작업 전

* 현재 상태를 충분히 이해한다.
* PRD를 기준으로 요구사항을 검증한다.

## 작업 중

* 최소 변경 원칙 준수
* 불필요한 리팩토링 금지
* 기존 아키텍처 존중
* 근거 없는 추측 금지
* 단순하고 유지보수 가능한 코드 우선

## 작업 후

* 테스트 수행
* 코드 리뷰 수행
* Memory Bank 업데이트

---

# 의사결정 우선순위

구현 방식이 여러 개 존재하는 경우 아래 순서를 따른다.

1. docs/prd.md
2. memory-bank/project-brief.md
3. memory-bank/architecture.md
4. memory-bank/coding-rules.md
5. 유지보수성
6. 단순성
7. 개발 속도

---

# 작업 종료 체크리스트

* [ ] 구현 완료
* [ ] 테스트 완료
* [ ] 코드 리뷰 완료
* [ ] current-state.md 업데이트
* [ ] tasks.md 업데이트
* [ ] 필요 시 architecture.md 업데이트
* [ ] 필요 시 decisions.md 업데이트
* [ ] 필요 시 troubleshooting.md 업데이트
* [ ] 다음 작업 정의

---

# 컨텍스트 연속성 규칙

에이전트는 세션이 변경되어도 작업을 이어갈 수 있어야 한다.

이를 위해:

* 현재 상태는 current-state.md에 기록한다.
* 작업 계획은 tasks.md에 기록한다.
* 의사결정은 decisions.md에 기록한다.
* 기술 설계는 architecture.md에 기록한다.
* 문제 해결 이력은 troubleshooting.md에 기록한다.

새로운 세션은 반드시 Memory Bank를 읽고 프로젝트 상태를 복구한 후 작업을 시작한다.

---

# 금지 사항

다음 행동을 금지한다.

* PRD 무시
* Memory Bank 미갱신
* 문서화 없는 구조 변경
* 검증 없는 완료 처리
* 근거 없는 추측 구현
* 불필요한 대규모 리팩토링
* current-state.md 미갱신 상태로 작업 종료

모든 구현은 PRD와 Memory Bank의 일관성을 유지해야 한다.
