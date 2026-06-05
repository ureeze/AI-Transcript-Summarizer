# Trouble Shooting

## 문제

프론트엔드 `npm run build` 실행 시 Vite와 React DOM 타입에서 `@types/node`, Node built-in module, DOM 타입 관련 TypeScript 오류가 발생했다.

## 원인

`vite.config.ts`를 검사하는 `tsconfig.node.json`에 Node 타입 선언이 없었고, Vite 타입에서 필요한 `ESNext`/DOM lib 범위가 부족했다.

## 해결

- `frontend/package.json`에 `@types/node`를 추가했다.
- `frontend/tsconfig.node.json`의 `target`과 `lib`를 `ESNext` 기준으로 조정했다.
- `types`에 `node`를 추가했다.

## 재발 방지

Vite + TypeScript 프로젝트 생성 시 `@types/node`와 Node용 tsconfig 설정을 함께 추가한다.

---

## 문제

백엔드 빌드 검증을 실행할 수 없었다.

## 원인

초기 Gradle 구조에는 Gradle Wrapper가 없었고, 현재 로컬 PATH에 Gradle CLI(`gradle`)가 설치되어 있지 않았다.

## 해결

Spring Initializr에서 Gradle 프로젝트 ZIP을 받아 `gradlew`, `gradlew.bat`, `gradle/wrapper/`를 추가했다. 이후 `.\gradlew.bat test`를 실행해 백엔드 테스트 성공을 확인했다.

## 재발 방지

Spring Boot 백엔드는 Initializr 기반으로 생성하고 Gradle Wrapper를 프로젝트에 포함한다. 백엔드 검증은 로컬 Gradle CLI가 아니라 `.\gradlew.bat test`로 수행한다.

---

## 문제

프론트엔드 `npm run build` 실행 시 `import.meta.env` 타입 오류가 발생했다.

## 원인

Vite 환경변수 타입을 제공하는 `vite/client` 참조 선언 파일이 없었다.

## 해결

`frontend/src/vite-env.d.ts`를 추가하고 `/// <reference types="vite/client" />`를 선언했다.

## 재발 방지

Vite + TypeScript 프로젝트에는 `src/vite-env.d.ts`를 포함해 `import.meta.env` 타입을 사용할 수 있게 한다.
