# AGENTS.md

## 역할

당신은 이 프로젝트의 주 개발 에이전트이다.

모든 작업은 PRD(Product Requirements Document)를 기준으로 수행하며, 프로젝트 상태와 의사결정 이력은 Memory Bank를 통해 관리한다.

Memory Bank는 프로젝트의 장기 기억 저장소이며, 세션이 변경되더라도 프로젝트의 연속성을 유지하기 위한 단일 진실 공급원(Source of Truth)이다.

---

## 프로젝트 시작 규칙

작업 시작 전 반드시 아래 문서를 읽고 프로젝트 상태를 파악한다.

1. PRD.md
2. memory-bank/design-document.md
3. memory-bank/active-context.md
4. memory-bank/implementation-plan.md
5. memory-bank/progress.md
6. memory-bank/trouble-shooting.md

추가로 최근 작업 이력을 이해하기 위해 아래를 확인한다.

7. memory-bank/task-log/

   - 가장 최근 월 파일
   - 현재 작업과 관련된 과거 의사결정 기록

active-context.md를 기준으로 현재 상태를 파악하고, task-log를 통해 의사결정 배경을 이해한 후 작업을 시작한다.

---

## Memory Bank 구조

```text
memory-bank/
  design-document.md
  active-context.md
  implementation-plan.md
  progress.md
  trouble-shooting.md
  task-log/
    YYYY-MM.md
```

---

## 작업 절차

1. PRD 확인
2. Memory Bank 확인
3. 현재 상태 파악
4. 구현 수행
5. 테스트 수행
6. 문서 업데이트
7. Git 상태 확인
8. 커밋 생성
9. GitHub push
10. 다음 작업 정의

문서 업데이트 없이 작업을 종료해서는 안 된다.

Git 커밋과 GitHub push 없이 작업을 종료해서는 안 된다. 단, 사용자가 명시적으로 커밋 또는 push를 보류하라고 요청한 경우에는 보류 사유와 현재 Git 상태를 `active-context.md`와 최종 응답에 기록한다.

---

## Memory Bank 관리 규칙

### design-document.md

제품 관점의 요구사항을 기록한다.

업데이트 조건:

- 제품 목표 변경
- 요구사항 변경
- 핵심 기능 추가
- 핵심 기능 제거

구현 세부사항은 기록하지 않는다.

### active-context.md

현재 상태를 기록한다.

항상 최신 상태만 유지한다.

기록 항목:

- 현재 마일스톤
- 현재 작업
- 최근 결정 사항
- 열린 이슈
- 다음 작업

이 파일은 다음 세션이 작업을 이어가기 위한 책갈피 역할을 한다.

작업 종료 시 반드시 갱신한다.

### implementation-plan.md

기술적 설계와 구현 구조를 기록한다.

업데이트 조건:

- 아키텍처 변경
- 신규 모듈 추가
- 폴더 구조 변경
- 기술 스택 변경
- 디자인 패턴 변경
- 주요 기술 결정 발생

항상 실제 코드 구조와 일치해야 한다.

### progress.md

프로젝트 진행 상황을 기록한다.

목적:

```text
무엇을 완료했는가
```

기록 항목:

- 작업 일시
- 완료한 작업
- 현재 상태
- 남은 작업
- 다음 작업

시간 순으로 누적 기록한다.

결정 이유나 상세 분석은 기록하지 않는다.

### task-log/YYYY-MM.md

프로젝트의 의사결정 이력을 기록한다.

목적:

```text
왜 그렇게 했는가
```

다음 상황에서 반드시 기록한다.

- 기술 선택
- 구조 변경
- 아키텍처 변경
- 리팩토링
- 성능 최적화
- 주요 문제 해결
- 중요한 구현 전략 결정

현재 월 파일에 기록한다.

기록 형식:

```markdown
## YYYY-MM-DD HH:mm

### 작업

...

### 고려한 대안

...

### 최종 결정

...

### 결정 이유

...

### 영향 범위

...

### 후속 작업

...
```

task-log는 의사결정 배경을 기록하는 문서이다.

단순 작업 완료 내역은 기록하지 않는다.

### trouble-shooting.md

문제 해결 이력을 기록한다.

목적:

동일 문제의 재발 방지

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

---

## 개발 원칙

### 작업 전

- 기능 구현 시작 전, 반드시 PRD와 Memory Bank 문서를 읽고 현재 프로젝트 상태를 파악한다.

### 작업 중

- 최소 변경 원칙 준수
- 불필요한 리팩토링 금지
- 기존 아키텍처 존중
- 근거 없는 추측 금지

### 작업 후

- 테스트 수행
- 빌드 확인
- 문서 업데이트
- Git 커밋
- GitHub push
- 다음 작업 정의

---

## Git 관리 규칙

작업이 완료되면 반드시 Git 커밋을 생성하고 GitHub 원격 저장소에 push한다.

### 브랜치 정책

- `main` 브랜치는 배포 브랜치로 사용한다.
- `develop` 브랜치는 기본 개발 브랜치로 사용한다.
- 새로운 작업은 `develop`에서 `feature/*` 브랜치를 생성하여 진행한다.
- `feature/*` 브랜치는 `develop`으로 Pull Request를 생성한다.
- `develop` 검증 후 `main`으로 병합한다.
- 작업 시작 전 반드시 현재 브랜치를 확인한다.

```bash
git branch --show-current
```

- 현재 브랜치가 `main`인 경우 코드 수정, 파일 생성, 커밋 작업을 시작하지 않는다.
- 현재 브랜치가 `main`인 경우 사용자에게 브랜치 생성 또는 전환 여부를 확인한다.
- 새로운 작업 시작 시 다음 순서를 우선 권장한다.

```bash
git checkout develop
git pull origin develop
git checkout -b feature/기능명
```

예시:

```bash
git checkout -b feature/login
git checkout -b feature/user-api
```

- 모든 커밋은 작업 브랜치(`feature/*`)에서 수행하는 것을 원칙으로 한다.
- `develop` 브랜치에는 Pull Request 또는 검증된 머지 작업만 반영한다.
- `main` 브랜치에는 `develop` 검증 후 Pull Request 또는 검증된 머지 작업만 반영한다.

### 기본 원칙

- 커밋 전 반드시 `git status`로 변경 파일을 확인한다.
- 커밋에는 현재 작업과 관련된 변경만 포함한다.
- 불필요한 빌드 산출물, 임시 파일, 로컬 환경 파일은 커밋하지 않는다.
- 커밋 전 테스트와 빌드 확인을 완료한다.
- Java/Gradle 프로젝트인 경우 가능하면 다음 명령으로 테스트를 수행한다.

```bash
./gradlew test
```

또는 Windows 환경:

```bash
.\gradlew.bat test
```

- 테스트가 성공한 경우에만 커밋을 진행한다.
- 커밋 후 `git push`로 GitHub 원격 저장소에 반영한다.
- push 후 `git status --short --branch`로 로컬과 원격 상태를 확인한다.

### 커밋 메시지

- 커밋 메시지는 한국어로 작성한다.
- 커밋 메시지는 작업 단위가 드러나도록 짧고 명확하게 작성한다.

예시:

- `유튜브 자막 추출 서비스 구현`
- `OpenAI 요약 API 연동`
- `프론트엔드 에러 상태 추가`
- `Memory Bank 작업 흐름 업데이트`

### 작업 완료 기준

다음 조건을 모두 만족해야 작업 완료로 간주한다.

1. 코드 수정 완료
2. 테스트 및 빌드 확인 완료
3. Git 커밋 완료
4. GitHub 원격 저장소 push 완료
5. 로컬과 원격 저장소 동기화 상태 확인 완료

### 예외 상황

다음 상황에서는 커밋 또는 push를 보류할 수 있다.

- 사용자가 명시적으로 커밋 또는 push 보류를 요청한 경우
- 테스트 또는 빌드가 실패했고, 사용자가 실패 상태 커밋을 요청하지 않은 경우
- GitHub 인증, 네트워크, 권한 문제로 push가 불가능한 경우
- 변경 사항 중 사용자 확인이 필요한 민감 정보 또는 의도 불명 파일이 포함된 경우

### 보류 처리

커밋 또는 push를 보류하는 경우 다음을 수행한다.

- `active-context.md`에 보류 사유를 기록한다.
- `progress.md`에 완료한 작업과 남은 Git 작업을 기록한다.
- 최종 응답에 커밋/push 미완료 사유와 다음 조치를 명확히 알린다.

---

## 의사결정 우선순위

구현 방식이 여러 개 존재할 경우 아래 순서를 따른다.

1. PRD
2. design-document.md
3. implementation-plan.md
4. 유지보수성
5. 단순성
6. 개발 속도

---

## 작업 종료 체크리스트

작업 종료 전 반드시 확인한다.

- [ ] 구현 완료
- [ ] 테스트 완료
- [ ] 빌드 성공 확인
- [ ] active-context.md 업데이트
- [ ] progress.md 업데이트
- [ ] 필요 시 implementation-plan.md 업데이트
- [ ] 필요 시 task-log/YYYY-MM.md 업데이트
- [ ] 필요 시 trouble-shooting.md 업데이트
- [ ] git status 확인
- [ ] Git 커밋 생성
- [ ] GitHub push 완료
- [ ] 다음 작업 정의

위 항목 중 하나라도 완료되지 않았다면 작업을 종료하지 않는다.

---

## 컨텍스트 연속성 규칙

에이전트는 세션이 변경되어도 작업을 이어갈 수 있어야 한다.

이를 위해:

- 현재 상태는 active-context.md에 기록한다.
- 완료 내역은 progress.md에 기록한다.
- 의사결정은 task-log/YYYY-MM.md에 기록한다.
- 기술 설계는 implementation-plan.md에 기록한다.
- 문제 해결 이력은 trouble-shooting.md에 기록한다.

새로운 세션은 반드시 위 문서를 읽고 프로젝트 상태를 복구한 후 작업을 시작한다.

---

## 금지 사항

다음 행동을 금지한다.

- PRD 무시
- Memory Bank 미갱신
- 문서화 없는 구조 변경
- 검증 없는 완료 처리
- 작업 완료 후 Git 커밋/push 누락
- 근거 없는 추측 구현
- 대규모 불필요 리팩토링
- active-context.md 미갱신 상태로 작업 종료

Memory Bank는 프로젝트의 공식 기록이며 모든 작업은 Memory Bank와 일관성을 유지해야 한다.
