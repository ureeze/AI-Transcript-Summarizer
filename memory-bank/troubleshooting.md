# Troubleshooting

## 2026-06-05 - 프론트엔드 Vite 타입 오류

### 상태

해결

### 문제

프론트엔드 `npm run build` 실행 시 Vite와 React DOM 타입에서 `@types/node`, Node built-in module, DOM 타입 관련 TypeScript 오류가 발생했다.

### 원인

`vite.config.ts`를 검사하는 `tsconfig.node.json`에 Node 타입 선언이 없었고, Vite 타입에서 필요한 `ESNext`/DOM lib 범위가 부족했다.

### 해결

- `frontend/package.json`에 `@types/node`를 추가했다.
- `frontend/tsconfig.node.json`의 `target`과 `lib`를 `ESNext` 기준으로 조정했다.
- `types`에 `node`를 추가했다.

### 재발 방지

Vite + TypeScript 프로젝트 생성 시 `@types/node`와 Node용 tsconfig 설정을 함께 추가한다.

---

## 2026-06-05 - 백엔드 Gradle Wrapper 누락

### 상태

해결

### 문제

백엔드 빌드 검증을 실행할 수 없었다.

### 원인

초기 Gradle 구조에는 Gradle Wrapper가 없었고, 현재 로컬 PATH에 Gradle CLI(`gradle`)가 설치되어 있지 않았다.

### 해결

Spring Initializr에서 Gradle 프로젝트 ZIP을 받아 `gradlew`, `gradlew.bat`, `gradle/wrapper/`를 추가했다. 이후 `.\gradlew.bat test`를 실행해 백엔드 테스트 성공을 확인했다.

### 재발 방지

Spring Boot 백엔드는 Initializr 기반으로 생성하고 Gradle Wrapper를 프로젝트에 포함한다. 백엔드 검증은 로컬 Gradle CLI가 아니라 `.\gradlew.bat test`로 수행한다.

---

## 2026-06-05 - Vite 환경변수 타입 오류

### 상태

해결

### 문제

프론트엔드 `npm run build` 실행 시 `import.meta.env` 타입 오류가 발생했다.

### 원인

Vite 환경변수 타입을 제공하는 `vite/client` 참조 선언 파일이 없었다.

### 해결

`frontend/src/vite-env.d.ts`를 추가하고 `/// <reference types="vite/client" />`를 선언했다.

### 재발 방지

Vite + TypeScript 프로젝트에는 `src/vite-env.d.ts`를 포함해 `import.meta.env` 타입을 사용할 수 있게 한다.

---

## 2026-06-13 - OpenAiSummaryClient Bean 생성 실패

### 상태

해결

### 문제

백엔드 `bootRun` 실행 시 `OpenAiSummaryClient` Bean 생성에 실패해 애플리케이션이 기동되지 않았다.

### 원인

`OpenAiSummaryClient`에 운영용 public 생성자와 테스트용 package-private 생성자가 함께 있었지만, Spring이 사용할 생성자가 명시되어 있지 않았다. 이로 인해 Spring이 기본 생성자를 찾으려다 실패했다.

### 해결

운영용 public 생성자에 `@Autowired`를 추가해 Spring DI 생성자를 명시했다. 또한 `OpenAiSummaryClientBeanTests`를 추가해 실제 Spring Context에서 Bean 생성이 가능한지 검증한다.

### 재발 방지

Spring Bean에 생성자가 여러 개 필요한 경우 운영 DI 생성자에 `@Autowired`를 명시하고, Bean 생성 테스트를 유지한다.

---

## 2026-06-13 - YouTube timedtext 빈 자막 응답

### 상태

관찰 중

### 문제

공개 자막이 있는 YouTube 영상으로 `/api/summarize`를 호출했지만 422 응답과 `"이 영상의 자막을 가져올 수 없습니다."` 메시지가 반환되었다.

### 원인

YouTube watch page에는 `captionTracks`가 포함되어 있었지만, 추출한 `timedtext` URL 호출 결과가 200 응답과 빈 본문을 반환했다. 여러 공개 영상과 `type=list` 엔드포인트에서도 동일하게 빈 본문이 반환되어, 현재 비공식 공개 자막 추출 방식의 외부 응답 제약 또는 구조 변경 영향으로 판단한다.

### 해결

자막 트랙 후보를 한국어, 영어, 나머지 트랙 순서로 시도하도록 fallback 로직을 추가했다. 특정 트랙이 빈 본문 또는 파싱 실패를 반환하면 다음 후보 트랙을 시도한다.

### 재발 방지

비공식 YouTube 자막 추출 방식은 실패 가능성을 전제로 검증한다. 로컬/배포 검증 시 실제 자막 응답 본문이 비어 있는지 확인하고, 계속 실패하면 공식 API 또는 별도 자막 입력 fallback 같은 대안을 검토한다.
